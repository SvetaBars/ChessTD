package Figures.Enemies;

import Cells.Road;
import Figures.Figure;
import Windows.Board;

import java.awt.*;

public class Enemy extends Figure {
    protected int[][] move_matrix;
    public int cost;
    public Enemy(Board board, int x, int y){
        super(board, x, y);
    }

    @Override
    public String GetImageName() {
        return "black_"+getClass().getSimpleName().toLowerCase();
    }
    public void Step(){
        Point point = FindMove(x, y, null);
        if(point != null)
            MoveTo(point.x,point.y,true);
    }
    public Point FindMove(int x, int y, int[] fixed){
        int current_distance = 239239;
        Point result = null;

        for(int[] xy : move_matrix) {
            if(fixed != null && (fixed[0]!=xy[0] || fixed[1]!=xy[1]))
                continue;

            int new_x = x + xy[0];
            int new_y = y + xy[1];

            if(board.CellIsValid(new_x, new_y) && board.cells[new_x][new_y] instanceof Road && board.cells[new_x][new_y].figure == null) {
                if(((Road)board.cells[new_x][new_y]).distance <= current_distance) {
                    current_distance = ((Road)board.cells[new_x][new_y]).distance;
                    result = new Point(new_x, new_y);

                    if(can_move_further) {
                        Point f = FindMove(new_x, new_y, xy);
                        if(f != null) {
                            if(board.cells[f.x][f.y] instanceof Road && ((Road) board.cells[f.x][f.y]).distance < current_distance) {
                                current_distance = ((Road) board.cells[f.x][f.y]).distance;
                                result = f;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
