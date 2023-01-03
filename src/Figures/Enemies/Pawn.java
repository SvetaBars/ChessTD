package Figures.Enemies;

public class Pawn extends Enemy{
    public Pawn(int x, int y) {
        super(x,y);
        move_matrix = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    }
}
