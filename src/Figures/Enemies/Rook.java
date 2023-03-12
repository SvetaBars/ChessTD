package Figures.Enemies;

import Windows.Board;

public class Rook extends Enemy{
    public Rook(Board board, int x, int y) {
        super(board, x ,y);
        can_move_further = true;
        cost = 1;
        move_matrix = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    }
}