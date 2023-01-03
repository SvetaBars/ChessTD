package Windows;

import javax.swing.*;
import java.awt.*;

public class Title extends JFrame {
    public Title() {
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        JLabel header = new JLabel("Chess TD", SwingConstants.CENTER);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setForeground(Color.RED);
        header.setFont(new Font("Serif", Font.PLAIN, 24));
        panel.add(header);
        JButton start = new JButton("Start");
        start.setVerticalAlignment(JButton.BOTTOM);
        start.setFont(new Font("Serif", Font.PLAIN, 24));
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        start.addActionListener(e -> {
            setVisible(false);
            new Board();
        });

        panel.add(start);
        add(panel);
        setVisible(true);
    }
}
