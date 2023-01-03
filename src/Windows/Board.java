package Windows;

import Figures.Enemies.Enemy;
import Figures.Towers.Tower;

import javax.swing.*;
import java.util.ArrayList;

public class Board extends JFrame {
    Cells.Cell[][] cells;
    ArrayList<Enemy> enemies;
    ArrayList<Tower> towers;
    JLayeredPane pane;
    public Board() {
        enemies = new ArrayList<Enemy>();
        towers = new ArrayList<Tower>();
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pane = new JLayeredPane();
        pane.setBounds(0,0,640, 480);
        pane.setLayout(null);
        add(pane);
        build(10,10);
        setVisible(true);
        run();
    }

    public void build(int sx, int sy) {
        cells = new Cells.Cell[sx][sy];
        for(int x=0;x<sx;x++)
            for(int y=0;y<sy;y++) {
                if(x==8 && y==8 || x==7 && y==8)
                    cells[x][y] = new Cells.Spawn(x,y);
                else if(x==8 && y<8 || x==7 && y<8)
                    cells[x][y] = new Cells.Road(x,y, y);
                else
                    cells[x][y] = new Cells.Cell(x,y);
                pane.add(cells[x][y]);
            }

        Tower f = new Figures.Towers.Pawn(6,0);
        pane.add(f,1);
        towers.add(f);
        Enemy g = new Figures.Enemies.Pawn(7,8);
        pane.add(g,1);
        enemies.add(g);
    }

    public void run() {
        new Timer(1000, e -> {
            MoveEnemies();
            FireTowers();
        }).start();
    }
    public void MoveEnemies() {
        for(Enemy e: enemies) {
            e.Step(cells);
        }
    }
    public void FireTowers() {

    }
}
