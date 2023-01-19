package Figures.Towers;

import Windows.Board;

public class Queen extends Tower{
    public Queen(Board board, int x, int y, boolean can_move_futher) {
        super(board,x,y, can_move_futher);
        attack_matrix=new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        can_move_futher=true;
    }
}