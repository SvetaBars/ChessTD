package Windows;

import javax.swing.*;
import java.awt.*;

public class Board extends JFrame {
    Cells.Cell[][] cells;
    JPanel chess;
    public Board() {
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        chess = new JPanel();
        chess.setBounds(0,0,640, 480);
        chess.setLayout(null);
        build(10,10);
        add(chess);
        setVisible(true);
    }

    public void build(int sx, int sy) {
        Figures.Figure pawn = new Figures.Figure(2,2);
        chess.add(pawn);
        cells = new Cells.Cell[sx][sy];
        for(int x=0;x<sx;x++)
            for(int y=0;y<sy;y++) {
                cells[x][y] = new Cells.Cell(x,y);
                chess.add(cells[x][y]);
            }
        pawn.moveTo(5,5);
    }
}
