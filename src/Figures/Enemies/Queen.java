package Figures.Enemies;

import Windows.Board;

public class Queen extends Enemy{
    public Queen(Board board, int x, int y, boolean can_move_futher) {
        super(board,x,y, can_move_futher);

        move_matrix = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    }
}