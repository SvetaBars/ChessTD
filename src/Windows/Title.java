package Windows;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Title extends JFrame {
    public Title() {
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        JLabel header = new JLabel("Chess TD", SwingConstants.CENTER);
        header.setVerticalAlignment(JLabel.TOP);
        header.setForeground(Color.RED);
        header.setFont(new Font("Serif", Font.PLAIN, 24));
        panel.add(header);
        JButton start = new JButton("Start");
        start.setVerticalAlignment(JButton.BOTTOM);
        start.setFont(new Font("Serif", Font.PLAIN, 24));

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Windows.Board board = new Windows.Board();
            }
        });

        panel.add(start);
        add(panel);
        setVisible(true);
    }
}
