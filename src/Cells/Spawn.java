package Cells;

import Windows.Board;

import java.awt.*;

public class Spawn extends Road {
    public Spawn(Board board, int x, int y){
        super(board,x, y);
    }
    @Override
    public Color getBlack(){
        return new Color(4, 126, 12);
    }
    @Override
    public Color getWhite(){
        return new Color(34, 192, 21);
    }
}
