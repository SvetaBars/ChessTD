package Figures.Towers;

import Figures.Figure;

public class Tower extends Figure {
    public Tower(int x, int y){
        super(x, y);
    }

    @Override
    public String GetImageName() {
        return "white_"+getClass().getSimpleName().toLowerCase();
    }
}
