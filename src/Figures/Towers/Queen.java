package Figures.Towers;

import Windows.Board;

public class Queen extends Tower{
    public Queen(Board board, int x, int y) {
        super(board,x,y);
        cooldown = 3;
        cost = 9;
        attack_matrix=new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
        can_move_further=true;
    }
}