package Figures.Enemies;

import Windows.Board;

public class Knight extends Enemy{
    public Knight(Board board, int x, int y) {
        super(board, x, y);
        cost = 1;
        move_matrix = new int[][] {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
    }
}