package Windows;

import javax.swing.*;

public class Board extends JFrame {
    Cells.Cell[][] cells;
    JLayeredPane pane;
    public Board() {
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pane = new JLayeredPane();
        pane.setBounds(0,0,640, 480);
        pane.setLayout(null);
        add(pane);
        build(10,10);
        setVisible(true);
    }

    public void build(int sx, int sy) {
        cells = new Cells.Cell[sx][sy];
        for(int x=0;x<sx;x++)
            for(int y=0;y<sy;y++) {
                cells[x][y] = new Cells.Cell(x,y);
                pane.add(cells[x][y]);
            }
        Figures.Figure pawns[] = new Figures.Figure[10];
        for(var i=0;i<10;i++) {
            pawns[i] = new Figures.Figure(i,0);
            pane.add(pawns[i],1);
        }
        for(var i=0;i<10;i++)
            pawns[i].moveTo(i,5);
    }
}
