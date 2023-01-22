package Figures.Enemies;

import Windows.Board;

public class Pawn extends Enemy{
    public Pawn(Board board, int x, int y) {
        super(board,x,y);
        cost = 1;
        move_matrix = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    }
}
