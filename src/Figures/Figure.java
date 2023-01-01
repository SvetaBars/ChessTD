package Figures;

import Cells.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Figure extends JPanel {
    int x,y;

    public Figure(int _x, int _y) {
        x = _x;
        y = _y;
        setBounds(x*Cells.Cell.size,y*Cells.Cell.size,Cells.Cell.size,Cells.Cell.size);
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        try {
            BufferedImage img = ImageIO.read(new File("images/white_pawn.png"));
            JLabel pic = new JLabel(new ImageIcon(img));
            pic.setBounds(0,0,Cells.Cell.size,Cells.Cell.size);
            add(pic);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveTo(int _x, int _y) {
        Timer t = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cx = getX();
                int cy = getY();
                if(_x!=0) cx += cx<_x*Cells.Cell.size?1:-1;
                if(_y!=0) cy += cy<_y*Cells.Cell.size?1:-1;

                setBounds(cx,cy,Cells.Cell.size,Cells.Cell.size);

                if(cx==_x*Cells.Cell.size && cy==_y*Cells.Cell.size) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        t.start();
    }
}
