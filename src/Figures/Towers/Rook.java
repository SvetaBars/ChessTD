package Figures.Towers;

import Windows.Board;

public class Rook extends Tower{
    public Rook(Board board, int x, int y) {
        super(board, x, y);
        cooldown = 5;
        cost = 5;
        attack_matrix=new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        can_move_further=true;
    }
}