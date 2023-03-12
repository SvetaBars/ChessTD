package Windows;

import javax.json.JsonObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameOver extends JFrame {
    Title title;
    public GameOver(boolean win, Title title, JsonObject level) {
        this.title = title;
        setSize(320, 200);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        JLabel header = new JLabel(win?"Gratz you win!":"Sorry you lost", SwingConstants.CENTER);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setForeground(win?Color.GREEN:Color.RED);
        header.setFont(new Font("Serif", Font.PLAIN, 48));
        panel.add(header);
        JButton menu = new JButton("Menu");
        menu.setFont(new Font("Serif", Font.PLAIN, 24));
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.addActionListener(e->{
            title.setVisible(true);
            dispose();
        });
        panel.add(menu);
        JButton replay = new JButton("Replay");
        replay.setFont(new Font("Serif", Font.PLAIN, 24));
        replay.setAlignmentX(Component.CENTER_ALIGNMENT);
        replay.addActionListener(e->{
            new Board(title, level);
            dispose();
        });
        panel.add(replay);
        add(panel);
        setVisible(true);
    }
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            title.setVisible(true);
            dispose();
        }
    }
}
