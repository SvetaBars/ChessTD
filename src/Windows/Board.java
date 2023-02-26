package Windows;

import Cells.Castle;
import Cells.Cell;
import Cells.Road;
import Cells.Spawn;
import Figures.Enemies.Enemy;
import Figures.Towers.Tower;

import javax.imageio.ImageIO;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Board extends JFrame {
    Title title;
    JPanel toolbar;
    JsonObject level;
    public Tower adding;
    public Cells.Cell[][] cells;
    ArrayList<Enemy> enemies;
    public ArrayList<Tower> towers;
    ArrayList<Spawn> spawners;
    int current_spawner;
    int current_wave;
    int current_wave_enemy;
    int lives;
    int money;
    JLabel lives_label;
    JLabel money_label;
    JLabel wave_label;
    JLayeredPane pane;
    static final Integer CELLS_LAYER = 0;
    static final Integer ENEMIES_LAYER = 1;
    static final Integer TOWERS_LAYER = 2;
    static final Integer PROPS_LAYER = 3;
    int phase;
    boolean paused;
    public Board(Title title, JsonObject level) {
        this.title = title;
        this.level = level;
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        spawners = new ArrayList<>();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(null);
        toolbar = new JPanel();

        lives_label = new JLabel("Lives: ");
        toolbar.add(lives_label);
        money_label = new JLabel("Money: ");
        toolbar.add(money_label);
        wave_label = new JLabel("Wave: ");
        toolbar.add(wave_label);
        JButton start = new JButton("Start");
        toolbar.add(start);
        start.addActionListener(e -> {
            paused = !paused;
            start.setText(paused?"Start":"Pause");
        });
        Stream.of("Pawn","Knight","Bishop","Rook","Queen").forEach(name->{
            try {
                BufferedImage img = ImageIO.read(new File("images/white_"+name+".png"));
                JButton add_it = new JButton(new ImageIcon(img));
                add_it.setMargin(new Insets(0,0,0,0));
                toolbar.add(add_it);
                add_it.addActionListener(e->{
                    adding = switch (name) {
                        case "Pawn" -> new Figures.Towers.Pawn(this,0,0);
                        case "Knight" -> new Figures.Towers.Knight(this,0,0);
                        case "Bishop" -> new Figures.Towers.Bishop(this,0,0);
                        case "Rook" -> new Figures.Towers.Rook(this,0,0);
                        case "Queen" -> new Figures.Towers.Queen(this,0,0);
                        default -> null;
                    };
                    if(adding!=null) {
                        adding.setVisible(false);
                        pane.add(adding,TOWERS_LAYER);
                    }
                });
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
        add(toolbar);
        pane = new JLayeredPane();
        pane.setLayout(null);
        add(pane);
        Build(level);
        setVisible(true);
        paused = true;
        UpdateUI();
        Run();
    }

    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            title.setVisible(true);
            dispose();
        }
    }

    public void Build(JsonObject level) {
        boolean king_added = false;
        JsonArray map = level.getJsonArray("map");
        int sy = map.size();
        int sx = map.getString(0).length() ;
        lives = level.getInt("lives");
        money = level.getInt("money");

        int window_width = sx * Cell.size+20;
        if(window_width < 800) window_width = 800;
        toolbar.setBounds(0,0,window_width, 60);
        setSize(window_width, toolbar.getHeight()+sy * Cell.size+40);
        if(getWidth() < 640) setSize(640, getHeight());
        pane.setBounds(0, toolbar.getHeight(), sx * Cell.size+20, sy * Cell.size+40);

        cells = new Cells.Cell[sx][sy];
        for(int x=0;x<sx;x++)
            for(int y=0;y<sy;y++) {
                switch (map.getString(y).charAt(x)) {
                    case 'C' -> cells[x][y] = new Castle(this, x, y);
                    case '.' -> cells[x][y] = new Cells.Road(this, x, y);
                    case 'S' -> {
                        cells[x][y] = new Cells.Spawn(this, x, y);
                        spawners.add((Spawn)cells[x][y]);
                    }
                    default -> cells[x][y] = new Cells.Empty(this, x, y);
                }
                pane.add(cells[x][y],CELLS_LAYER);
                if(!king_added && cells[x][y] instanceof Castle) {
                    try {
                        BufferedImage img = ImageIO.read(new File("images/white_king.png"));
                        JLabel pic = new JLabel(new ImageIcon(img));
                        pic.setBounds(cells[x][y].getX(),cells[x][y].getY(),2*Cells.Cell.size,2*Cells.Cell.size);
                        pane.add(pic, TOWERS_LAYER);
                    }
                    catch(IOException e) {
                        System.out.println(e.getMessage());
                    }
                    king_added = true;
                }
            }

        boolean road_found = true;
        while(road_found) {
            road_found = false;
            for (int x = 0; x < sx; x++)
                for (int y = 0; y < sy; y++) {
                    if(cells[x][y] instanceof Road) {
                        for(int dx=-1;dx<=1;dx++)
                            for(int dy=-1;dy<=1;dy++)
                                if((dx==0 || dy==0) && CellIsValid(x+dx,y+dy)) {
                                    if(cells[x+dx][y+dy] instanceof Road && ((Road)cells[x][y]).distance>((Road)cells[x+dx][y+dy]).distance+1) {
                                        ((Road)cells[x][y]).distance = ((Road)cells[x+dx][y+dy]).distance+1;
                                        road_found = true;
                                    }
                                }
                    }
                }
        }

    }

    public void Run() {
        phase = 0;
        new Timer(500, e -> {
            UpdateUI();
            if(paused) return;
            if(phase == 0) {
                FallBackTowers();
                MoveEnemies();
                SpawnEnemies();
            }
            else
                FireTowers();
            phase = (phase+1)%2;
        }).start();
    }

    public void SpawnEnemies() {
        if(current_wave>=level.getJsonArray("waves").size())
            return;
        if(current_wave_enemy>=level.getJsonArray("waves").getJsonObject(current_wave).getString("enemies").length())
            return;
        Spawn spawner = spawners.get(current_spawner);
        if(spawner.figure != null) return;
        Enemy e = switch (level.getJsonArray("waves").getJsonObject(current_wave).getString("enemies").charAt(current_wave_enemy)) {
            case 'P' -> new Figures.Enemies.Pawn(this,spawner.x,spawner.y);
            case 'K' -> new Figures.Enemies.Knight(this,spawner.x,spawner.y);
            case 'B' -> new Figures.Enemies.Bishop(this,spawner.x,spawner.y);
            case 'R' -> new Figures.Enemies.Rook(this,spawner.x,spawner.y);
            case 'Q' -> new Figures.Enemies.Queen(this,spawner.x,spawner.y);
            default -> null;
        };
        if(e!=null) {
            current_spawner = (current_spawner + 1) % spawners.size();
            current_wave_enemy++;
            pane.add(e,ENEMIES_LAYER);
            e.MoveTo(e.x,e.y,true);
            enemies.add(e);
        }
    }

    public void MoveEnemies() {
        for(Enemy e: enemies) {
            if(cells[e.x][e.y] instanceof Castle)
                ReachedCastle(e);
        }

        enemies.removeIf(enemy->cells[enemy.x][enemy.y] instanceof Castle);

        for(Enemy e: enemies) {
            e.Step();
        }
    }
    public void FireTowers() {
        for(Tower t: towers) {
            t.Attack();
        }
    }
    public void FallBackTowers() {
        for(Tower t: towers) {
            t.FallBack();
        }
    }
    public void KillEnemy(Enemy e) {
        cells[e.x][e.y].figure = null;
        pane.remove(e);
        enemies.remove(e);
        money+=e.cost;
    }

    public Boolean CellIsValid(int x, int y) {
        return x>=0 && y>=0 && x<cells.length && y<cells[0].length;
    }

    public void ReachedCastle(Enemy e) {
        cells[e.x][e.y].figure = null;
        pane.remove(e);
        cells[e.x][e.y].revalidate();
        lives--;
    }

    public void UpdateUI() {
        lives_label.setText(String.format("Lives: %d", lives));
        money_label.setText(String.format("Money: %d", money));
        wave_label.setText(String.format("Wave: %d/%d", current_wave+1, level.getJsonArray("waves").size()));
    }
    public void ShowTowerProperties(Tower t){
        JPanel props = new JPanel();
        props.setSize(150, 50);
        JLabel props_label = new JLabel();
        JButton props_button = new JButton();
        props.add(props_label);
        props.add(props_button);
        pane.add(props, PROPS_LAYER);
        props_button.addActionListener(e -> {
            towers.remove(t);
            pane.remove(t);
            cells[t.x][t.y].figure = null;
            money+=t.cost;
        });
    }
}
