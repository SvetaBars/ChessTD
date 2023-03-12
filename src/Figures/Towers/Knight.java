package Figures.Towers;

import Windows.Board;

public class Knight extends Tower{
    public Knight(Board board, int x, int y) {
        super(board, x, y);
        cooldown = 9;
        cost = 3;
        attack_matrix=new int[][] {{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};
    }
}