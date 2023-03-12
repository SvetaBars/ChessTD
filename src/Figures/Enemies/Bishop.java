package Figures.Enemies;

import Windows.Board;

public class Bishop extends Enemy{
    public Bishop(Board board, int x, int y) {
        super(board,x,y);
        cost = 1;
        can_move_further = true;
        move_matrix = new int[][] {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    }
}
