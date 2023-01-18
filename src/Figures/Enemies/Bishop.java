package Figures.Enemies;

import Windows.Board;

public class Bishop extends Enemy{
    public Bishop(Board board, int x, int y, boolean can_move_futher) {
        super(board,x,y, can_move_futher);
        can_move_futher = true;
        move_matrix = new int[][] {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    }
}
