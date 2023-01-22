package Cells;

import Windows.Board;

import java.awt.*;

public class Road extends Cell{
    public int distance;
    public boolean attacked;
    public Road(Board board, int x, int y){
        super(board,x, y);
        this.distance=239239;
    }
    @Override
    public Color getBlack(){
        return new Color(35, 103, 162);
    }
    @Override
    public Color getWhite(){
        return new Color(42, 185, 229);
    }
}
