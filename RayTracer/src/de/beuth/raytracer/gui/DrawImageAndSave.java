/*
* Copyright (c) 2014. by Angelina Staeck und Michael Duve
*/
package de.beuth.raytracer.gui;
import de.beuth.raytracer.gui.content.ContentFrame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/**
 * describes the image saver
 */
public class DrawImageAndSave extends JFrame implements ActionListener {

    /**
     * static Width and Height declaration
     */
    public final static int WIDTH = 640;
    public final static int HEIGHT = 480;

    /**
     * The Frame inside containing the image
     */
    private ContentFrame imageContainer;

    public DrawImageAndSave() {
        /**
         * Destroy instance and exit on close of frame
         */
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        /**
         * Set width and height of window
         */
        this.setSize(new Dimension(WIDTH, HEIGHT));

        /**
         * Creates the menü
         */
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("Datei");
        JMenuItem saveItem = new JMenuItem("Speichern");
        saveItem.addActionListener(this);
        file.add (saveItem);
        menu.add(file);
        this.setJMenuBar(menu);

        /**
         * Creates a new Frame with the image painted inside
         */
        this.imageContainer = new ContentFrame();
        /**
         * Add this frame to the window
         */
        this.add(this.imageContainer);

        /**
         * Set window to visible
         */
        setVisible(true);
    }

    /**
     * Handles the button in the menubar
     * @param e which action is performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * If save is clicked
         */
        if (e.getActionCommand().equals("Speichern")) {
            /**
             * Show save dialog
             */
            JFileChooser fileDialog = new JFileChooser();
            int choice = fileDialog.showSaveDialog(this);
            /**
             * If save option is chosen
             */
            if (choice == JFileChooser.APPROVE_OPTION) {
                File saveFile = fileDialog.getSelectedFile();

                /**
                 * Store the filepath in a var
                 */
                String whatToSave = saveFile.getName().toLowerCase();

                /**
                 * if jpg set extension to jpg else to png
                 * if there is no extension typed in, throw error
                 */
                if (whatToSave.endsWith(".jpg")) {
                    whatToSave = "jpg";
                } else if (whatToSave.endsWith(".png")) {
                    whatToSave = "png";
                } else {
                    throw new IllegalArgumentException("Bitte Dateiendung .jpg oder .png angeben");
                }

                /**
                 * try to save the image to location with chosen extension
                 */
                try {
                    ImageIO.write(this.imageContainer.image, whatToSave, saveFile);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Main Method to start that exercise
     * @param args start arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrawImageAndSave();
            }
        });
    }
}