package Cells;

import Figures.Figure;
import Windows.Board;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    Board board;
    public int x,y;
    public static int size = 40;
    public Figure figure;

    public Cell(Board board, int _x, int _y) {
        x = _x;
        y = _y;
        this.board = board;

        setBackground(is_black() ? getBlack() : getWhite());
        setBounds(x*size,y*size,size,size);
    }
    public Color getBlack(){
        return new Color(241, 153, 49);
    }
    public Color getWhite(){
        return new Color(248, 179, 97);
    }

    protected boolean is_black() {
        return (x+y)%2!=0;
    }
}
