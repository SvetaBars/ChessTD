package Figures.Towers;

import Windows.Board;

public class Rook extends Tower{
    public Rook(Board board, int x, int y, boolean can_move_futher) {
        super(board,x,y,can_move_futher);
        attack_matrix=new int[][] {{0, 1}, {0, -1}, {1, -0}, {-1, -0}};
    }
}