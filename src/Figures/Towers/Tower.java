package Figures.Towers;

import Cells.Empty;
import Cells.Road;
import Cells.Spawn;
import Figures.Enemies.Enemy;
import Figures.Figure;
import Windows.Board;

import java.awt.*;

public class Tower extends Figure {
    int cooldown;
    int current_cooldown;
    public int cost;
    Enemy attacked_enemy;
    protected int[][] attack_matrix;
    public Tower(Board board, int x, int y){
        super(board, x, y);
    }

    @Override
    public String GetImageName() {
        return "white_"+getClass().getSimpleName().toLowerCase();
    }
    public Point FindEnemy(int x, int y, int[] fixed){
        Point result = null;
        int current_distance = 239239;
        for (int[] xy : attack_matrix){
            if ((fixed!=null) && (fixed[0]!=xy[0] || fixed[1]!=xy[1])){
                continue;
            }
            int new_x = x + xy[0];
            int new_y = y + xy[1];
            if (board.CellIsValid(new_x, new_y)) {
                if (board.cells[new_x][new_y].figure != null && board.cells[new_x][new_y] instanceof Road && !(board.cells[new_x][new_y] instanceof Spawn) && !((Road) board.cells[new_x][new_y]).attacked && ((Road) board.cells[new_x][new_y]).distance < current_distance) {
                    result = new Point(new_x, new_y);
                    current_distance = ((Road) board.cells[new_x][new_y]).distance;
                }
                else if (can_move_further && (board.cells[new_x][new_y] instanceof Empty || (board.cells[new_x][new_y] instanceof Road && !(board.cells[new_x][new_y] instanceof Spawn)))) {
                    Point f = FindEnemy(new_x, new_y, xy);
                    if (f != null && ((Road) board.cells[f.x][f.y]).distance < current_distance) {
                        result = f;
                        current_distance = ((Road) board.cells[f.x][f.y]).distance;
                    }
                }
            }
        }
        return result;
    }
    public void Attack(){
        if(current_cooldown > 0) {
            current_cooldown--;
            return;
        }
        Point point = FindEnemy(x, y, null);
        if(point != null) {
            ((Road)board.cells[point.x][point.y]).attacked = true;
            MoveTo(point.x,point.y,false);
            attacked_enemy = (Enemy)board.cells[point.x][point.y].figure;
            current_cooldown = cooldown;
        }
    }
    public void FallBack(){
        if(attacked_enemy != null) {
            ((Road)board.cells[attacked_enemy.x][attacked_enemy.y]).attacked = false;
            MoveTo(x, y,false);
            board.KillEnemy(attacked_enemy);
            attacked_enemy = null;
        }
    }
}
