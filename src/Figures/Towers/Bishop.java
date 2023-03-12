package Figures.Towers;

import Windows.Board;

public class Bishop extends Tower{
    public Bishop(Board board, int x, int y) {
        super(board, x, y);
        cooldown = 9;
        cost = 3;
        attack_matrix = new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        can_move_further=true;
    }
}
