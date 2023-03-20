package main;

import javax.swing.JFrame;
import java.io.Serial;

public class Window {

    @Serial
    public static final long serialVersionUID = -7404041211323748023L;

    public Window(String title, GamePanel gamePanel) {
        JFrame frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(gamePanel);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}