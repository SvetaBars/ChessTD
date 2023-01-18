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
    protected boolean can_move_futher;
    public Figure(Board board, int x, int y, boolean can_move_futher) {
        this.board = board;
        this.x = x;
        this.y = y;
        this.can_move_futher = can_move_futher;
        setBounds(x*Cells.Cell.size,y*Cells.Cell.size,Cells.Cell.size,Cells.Cell.size);
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        try {
            BufferedImage img = ImageIO.read(new File("images/"+ GetImageName()+".png"));
            JLabel pic = new JLabel(new ImageIcon(img));
            pic.setBounds(0,0,Cells.Cell.size,Cells.Cell.size);
            add(pic);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void MoveTo(int _x, int _y) {
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
                x = _x;
                y = _y;
                ((Timer)e.getSource()).stop();
                setLocation(x*Cells.Cell.size,y*Cells.Cell.size);
            }
        });
        t.start();
    }

    public String GetImageName() {
        return "";
    }
}
