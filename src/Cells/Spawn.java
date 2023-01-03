package Cells;

import java.awt.*;

public class Spawn extends Cell{
    public Spawn(int x, int y){
        super(x, y);
    }
    @Override
    public Color getBlack(){
        return new Color(25,85,28);
    }
    @Override
    public Color getWhite(){
        return new Color(110,250,185);
    }
}
