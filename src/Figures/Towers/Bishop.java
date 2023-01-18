package Figures.Towers;

import Windows.Board;

public class Bishop extends Tower{
    public Bishop(Board board, int x, int y, boolean can_move_futher) {
        super(board,x,y, can_move_futher);
        attack_matrix=new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    }
}
