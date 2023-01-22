package Windows;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

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
        File[] files = new File("levels").listFiles((dir, name) -> name.endsWith(".json"));

        if(files!=null)
            for(File file: files) {
                try (FileInputStream s = new FileInputStream("levels/"+file.getName())) {
                    JsonReader reader = Json.createReader(s);
                    JsonObject level = reader.readObject();

                    JButton start = new JButton(level.getString("name"));
                    start.setVerticalAlignment(JButton.BOTTOM);
                    start.setFont(new Font("Serif", Font.PLAIN, 24));
                    start.setAlignmentX(Component.CENTER_ALIGNMENT);

                    start.addActionListener(e -> {
                        setVisible(false);
                        new Board(this, level);
                    });

                    panel.add(start);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        add(panel);
        setVisible(true);
    }
}
