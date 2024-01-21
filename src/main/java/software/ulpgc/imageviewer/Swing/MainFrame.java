package software.ulpgc.imageviewer.Swing;

import software.ulpgc.imageviewer.Command;
import software.ulpgc.imageviewer.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;



    public MainFrame() {
        this.commands = new HashMap<>();
        setTitle("Image Viewer");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(createImageDisplay());
        add(createButton("<"), BorderLayout.WEST);
        add(createButton(">"),BorderLayout.EAST);
    }

    private Component createButton(String label) {
        JButton button = new JButton(label);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setBorder(BorderFactory.createLineBorder(Color.black));
        button.setFont(new Font(null,Font.BOLD, 30));
        button.addActionListener(e -> commands.get(label).execute());
        return button;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }


    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}
