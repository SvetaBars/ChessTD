package Cells;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    int x,y;
    public static int size = 40;

    public Cell(int _x, int _y) {
        x = _x;
        y = _y;

        setBackground(is_black() ? getBlack() : getWhite());
        setBounds(x*size,y*size,size,size);
    }
    public Color getBlack(){
        return new Color(55,55,88);
    }
    public Color getWhite(){
        return new Color(180,220,255);
    }

    protected boolean is_black() {
        return (x+y)%2!=0;
    }
}
