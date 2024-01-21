package software.ulpgc.imageviewer.Swing;

import software.ulpgc.imageviewer.*;

import javax.swing.*;
import java.io.File;

public class Main {
    ///public static final String root = "C:\\Users\\enyiz\\OneDrive\\Escritorio\\memes";

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int selection=fileChooser.showOpenDialog(frame);
        if (selection == JFileChooser.APPROVE_OPTION){
            File file =fileChooser.getSelectedFile();
            Image image = new FileImageLoader(file).load();
            frame.add("<", new PreviousImageCommand(frame.imageDisplay()));
            frame.imageDisplay().show(image);
            frame.add(">", new NextImageCommand(frame.imageDisplay()));
        } else if (selection == JFileChooser.CANCEL_OPTION) {
            System.exit(0);
        }

        frame.setVisible(true);
    }}
