package Figures.Towers;

import Windows.Board;

public class Knight extends Tower{
    public Knight(Board board, int x, int y, boolean can_move_futher) {
        super(board,x,y, can_move_futher);
        attack_matrix=new int[][] {{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
    }
}