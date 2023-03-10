package Cells;

import Figures.Towers.Tower;
import Windows.Board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Empty extends Cell implements MouseListener {
    public Empty(Board board, int _x, int _y) {
        super(board,_x, _y);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if(board.adding != null) {
            board.adding.MoveTo(x, y, true);
            board.cells[x][y].figure = board.adding;
            board.towers.add(board.adding);
            board.adding = null;
        }
        else if(this.figure!=null)
            board.ShowTowerProperties((Tower)this.figure);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        if(board.adding != null) {
            board.adding.setLocation(getLocation());
            board.adding.setVisible(true);
        }
        else if(this.figure!=null){
            ArrayList<Cell>new_can_attack = ((Tower)this.figure).CanAttack((Tower)this.figure);
            for(Cell c : new_can_attack){
                c.highlight();
            }
        }
    }

    public void mouseExited(MouseEvent e) {
        if(board.adding != null)
            board.adding.setVisible(false);
    }
}
