package Cells;

import java.awt.*;

public class Road extends Cell{
    public int distance;
    public Road(int x, int y, int distance){
        super(x, y);
        this.distance=distance;
    }
    @Override
    public Color getBlack(){
        return new Color(55,95,128);
    }
    @Override
    public Color getWhite(){
        return new Color(80,255,255);
    }
}
