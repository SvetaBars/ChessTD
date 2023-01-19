package Figures.Towers;

import Cells.Road;
import Figures.Enemies.Enemy;
import Figures.Figure;
import Windows.Board;

import java.awt.*;

public class Tower extends Figure {
    Enemy attacked_enemy;
    int saved_x, saved_y;
    protected int[][] attack_matrix;
    public Tower(Board board, int x, int y, boolean can_move_futher){super(board, x, y, can_move_futher);}

    @Override
    public String GetImageName() {
        return "white_"+getClass().getSimpleName().toLowerCase();
    }
    public Point FindEnemy(int x, int y, int[] fixed){
        for (int[] xy : attack_matrix){
            if ((fixed!=null) && (fixed[0]!=xy[0] || fixed[1]!=xy[1])){
                continue;
            }
            int new_x = x + xy[0];
            int new_y = y + xy[1];
            if (new_x>=0 && new_y>=0 && new_x<board.cells.length && new_y<board.cells[0].length && board.cells[new_x][new_y].figure != null && board.cells[new_x][new_y] instanceof Road) {
                return new Point(new_x, new_y);
            }
        }
        return null;
    }
    public void Attack(){
        Point point = FindEnemy(x, y, null);
        if(point != null) {
            saved_x = x;
            saved_y = y;
            MoveTo(point.x,point.y);
            attacked_enemy = (Enemy)board.cells[point.x][point.y].figure;
        }
    }
    public void FallBack(){
        if(attacked_enemy != null) {
            MoveTo(saved_x, saved_y);
            board.KillEnemy(attacked_enemy);
            attacked_enemy = null;
        }
    }
}
