package Figures;

import Windows.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Figure extends JPanel {
    public int x,y;
    protected Board board;
    protected boolean can_move_further;
    protected JPanel indicator;
    public Figure(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.can_move_further = false;
        setBounds(x*Cells.Cell.size,y*Cells.Cell.size,Cells.Cell.size,Cells.Cell.size);
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        setOpaque(false);
        try {
            BufferedImage img = ImageIO.read(new File("images/"+ GetImageName()+".png"));
            JLabel pic = new JLabel(new ImageIcon(img));
            pic.setBounds(0,0,Cells.Cell.size,Cells.Cell.size);
            add(pic);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        indicator = new JPanel();
        indicator.setBackground(new Color(0,0,255));
        ShowProgress(0,1);
        add(indicator);
    }

    public void MoveTo(int _x, int _y, boolean real_move) {
        if(real_move) {
            if(board.cells[x][y].figure == this)
                board.cells[x][y].figure = null;
            x = _x;
            y = _y;
            board.cells[x][y].figure = this;
        }

        final int animationTime = 100;
        int framesPerSecond = 30;
        int delay = 1000 / framesPerSecond;
        final long start = System.currentTimeMillis();
        int cx = getX();
        int cy = getY();
        int dx = (_x*Cells.Cell.size-cx);
        int dy = (_y*Cells.Cell.size-cy);
        Timer t = new Timer(delay, e -> {
            final long now = System.currentTimeMillis();
            final long elapsed = now - start;
            float progress = (float) elapsed / animationTime;
            setLocation( (int)(cx+dx*progress),(int)(cy+dy*progress));

            if(progress>=1) {
                ((Timer)e.getSource()).stop();
                setLocation(_x*Cells.Cell.size,_y*Cells.Cell.size);
            }
        });
        t.start();
    }

    public String GetImageName() {
        return "";
    }

    public void ShowProgress(int current, int max) {
        indicator.setBounds(Cells.Cell.size-5,Cells.Cell.size*current/max,Cells.Cell.size,Cells.Cell.size);
    }
}
