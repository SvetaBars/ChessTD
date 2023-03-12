package Cells;

import Figures.Towers.Tower;
import Windows.Board;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Empty extends Cell implements MouseListener {
    ArrayList<Road> highlighted_cells;
    public Empty(Board board, int _x, int _y) {
        super(board,_x, _y);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void highlight(boolean on) {
        if(on) {
            Tower tower = (Tower)figure;
            if(tower == null) tower = board.adding;
            highlighted_cells = tower.CanAttack(x, y, null);
            highlighted_cells.forEach(c->c.highlight(true));
        }
        else if(highlighted_cells != null){
            highlighted_cells.forEach(c->c.highlight(false));
            highlighted_cells = null;
        }
    }

    public void mousePressed(MouseEvent e) {
        if(board.adding != null) {
            board.BuyTower(this);
            highlight(false);
        }
        else if(this.figure!=null) {
            board.ShowTowerProperties((Tower)this.figure);
            highlight(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
        if(board.adding != null) {
            board.adding.setLocation(getLocation());
            board.adding.setVisible(true);
            highlight(true);
        }
    }

    public void mouseExited(MouseEvent e) {
        if(board.adding != null) {
            board.adding.setVisible(false);
            highlight(false);
        }
    }
}
