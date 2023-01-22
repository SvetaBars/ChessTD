package Figures.Towers;

import Windows.Board;

public class Pawn extends Tower{
    public Pawn(Board board, int x, int y) {
        super(board,x,y);
        cooldown = 9;
        cost = 1;
        attack_matrix=new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
    }
}
