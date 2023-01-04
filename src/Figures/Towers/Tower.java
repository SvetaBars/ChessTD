package Figures.Towers;

import Cells.Cell;
import Cells.Road;
import Figures.Enemies.Enemy;
import Figures.Figure;
import Windows.Board;

import java.awt.*;

public class Tower extends Figure {
    Enemy attacked_enemy;
    protected int[][] attack_matrix;
    public Tower(Board board, int x, int y){
        super(board, x, y);
    }

    @Override
    public String GetImageName() {
        return "white_"+getClass().getSimpleName().toLowerCase();
    }
    public Point FindEnemy(int x, int y, Point fixed){
        for (int[] xy : attack_matrix){
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
            MoveTo(point.x,point.y);
            attacked_enemy = (Enemy)board.cells[point.x][point.y].figure;
        }
    }
    public void FallBack(){
        if(attacked_enemy != null) {
            MoveTo(x, y);
            board.KillEnemy(attacked_enemy);
            attacked_enemy = null;
        }
    }
}
