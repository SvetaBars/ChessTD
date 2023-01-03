package Figures.Enemies;

import Cells.Cell;
import Cells.Road;
import Figures.Figure;
import java.awt.Point;

public class Enemy extends Figure {
    protected int[][] move_matrix;
    public Enemy(int x, int y){
        super(x, y);
    }

    @Override
    public String GetImageName() {
        return "black_"+getClass().getSimpleName().toLowerCase();
    }
    public void Step(Cell[][] cells){
        Point point = FindMove(cells, x, y, new Point(0,0));
        if(point.x >= 0)
            MoveTo(point.x,point.y);
    }
    public Point FindMove(Cell[][] cells, int x, int y, Point fixed){
        int current_distance = 239;
        Point result = new Point(-1,-1);

        for(int[] xy : move_matrix) {
            int new_x = x + xy[0];
            int new_y = y + xy[1];

            if(cells[new_x][new_y] instanceof Road) {
                if(((Road)cells[new_x][new_y]).distance < current_distance) {
                    current_distance = ((Road)cells[new_x][new_y]).distance;
                    result.x = new_x;
                    result.y = new_y;
                }
            }
        }
        return result;
    }
}
