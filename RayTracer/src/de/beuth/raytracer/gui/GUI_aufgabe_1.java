/*
 * Copyright (c) 2014 by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.gui;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI_aufgabe_1 extends JFrame {

    /**
     * creates a new JFrame which shows an image opened from the harddrive.
     */

    public GUI_aufgabe_1 (){
        BufferedImage loadedImage = loadImage();
        if (loadedImage == null) {
            System.out.println("You must choose a file");
        } else {
            ImageIcon ico = new ImageIcon(loadedImage);
            JLabel label = new JLabel(ico);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(ico.getIconWidth(), ico.getIconHeight());
            getContentPane().add(label);
            pack();
            setVisible(true);
        }
    }

    /**
     * the method opens an image from the harddrive.
     * @return the method returns an image, only jpg and png
     */

    public BufferedImage loadImage () {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPEG file", "jpg", "jpeg", "png");
            chooser.setFileFilter(filter);
            chooser.setMultiSelectionEnabled(false);
            int result = chooser.showOpenDialog(null);

            BufferedImage a = null;

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = (chooser.getSelectedFile());
                this.setTitle(chooser.getName(file));

                try {
                    a = ImageIO.read(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return a;
        }

    /**
     * main method for starting the swing GUI  (exercise 1 part 1)
     * @param args array of arguments passed to main-method
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI_aufgabe_1();
            }
        });
    }

}
