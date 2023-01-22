package Cells;

import Windows.Board;

import java.awt.*;

public class Castle extends Road {
    public Castle(Board board, int _x, int _y) {
        super(board,_x, _y);
        distance = 0;
    }

    @Override
    public Color getBlack(){
        return new Color(211, 6, 13);
    }
    @Override
    public Color getWhite(){
        return new Color(253, 25, 25);
    }
}
