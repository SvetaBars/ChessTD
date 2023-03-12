package Figures.Enemies;

import Windows.Board;

public class Queen extends Enemy{
    public Queen(Board board, int x, int y) {
        super(board, x, y);
        can_move_further = true;
        cost = 1;
        move_matrix = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    }
}