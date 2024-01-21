package software.ulpgc.imageviewer.Swing;

import software.ulpgc.imageviewer.Image;
import software.ulpgc.imageviewer.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private software.ulpgc.imageviewer.Image image;
    private BufferedImage bitmap;

    @Override
    public void show(software.ulpgc.imageviewer.Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
        Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));
        int x = (this.getWidth() - bitmap.getWidth()) / 2;
        int y = (bitmap.getHeight() - bitmap.getHeight()) / 2;
        g.drawImage(bitmap, x, y, null);
    }

    public static class Resizer {
        private final Dimension dimension;

        public Resizer(Dimension dimension) {
            this.dimension = dimension;
        }

        public Dimension resize(Dimension dimension) {
            if (isSmallerOrEqual(dimension, this.dimension)) {
                return dimension;
            } else {
                double aspectRatio = calculateAspectRatio(dimension);
                double panelAspectRatio = calculateAspectRatio(this.dimension);

                if (aspectRatio > panelAspectRatio) {
                    int newHeight = (int) (this.dimension.width / aspectRatio);
                    return new Dimension(this.dimension.width, newHeight);
                } else {
                    int newWidth = (int) (this.dimension.height * aspectRatio);
                    return new Dimension(newWidth, this.dimension.height);
                }
            }
        }

        private boolean isSmallerOrEqual(Dimension dimension1, Dimension dimension2) {
            return dimension1.width <= dimension2.width && dimension1.height <= dimension2.height;
        }

        private double calculateAspectRatio(Dimension dimension) {
            return (double) dimension.width / dimension.height;
        }

    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
