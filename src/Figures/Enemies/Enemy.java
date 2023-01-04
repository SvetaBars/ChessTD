package Figures.Enemies;

import Cells.Road;
import Figures.Figure;
import Windows.Board;

import java.awt.Point;

public class Enemy extends Figure {
    protected int[][] move_matrix;
    public Enemy(Board board, int x, int y){
        super(board, x, y);
    }

    @Override
    public String GetImageName() {
        return "black_"+getClass().getSimpleName().toLowerCase();
    }
    public void Step(){
        Point point = FindMove(x, y, null);
        if(point != null) {
            board.cells[x][y].figure = null;
            board.cells[point.x][point.y].figure = this;
            MoveTo(point.x,point.y);
        }
    }
    public Point FindMove(int x, int y, Point fixed){
        int current_distance = 239;
        Point result = null;

        for(int[] xy : move_matrix) {
            int new_x = x + xy[0];
            int new_y = y + xy[1];

            if(new_x>=0 && new_y>=0 && new_x<board.cells.length && new_y<board.cells[0].length && board.cells[new_x][new_y] instanceof Road) {
                if(((Road)board.cells[new_x][new_y]).distance < current_distance) {
                    current_distance = ((Road)board.cells[new_x][new_y]).distance;
                    result = new Point(new_x,new_y);
                }
            }
        }
        return result;
    }
}
