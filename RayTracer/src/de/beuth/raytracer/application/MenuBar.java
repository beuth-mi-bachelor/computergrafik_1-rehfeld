/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.application;

import de.beuth.raytracer.testing.Exercise1Testing;
import de.beuth.raytracer.testing.Exercise2Testing;
import de.beuth.raytracer.testing.Exercise3Testing;
import de.beuth.raytracer.testing.Exercise4Testing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuBar extends JMenuBar implements ActionListener {

    private RayTracer raytracer;

    public static String[] mainMenuItemNames = {
            "Datei",
            "Tests",
            "Optionen"
    };

    public static String[][] subMenuItemNames = {
            {
                    "Speichern",
                    "Beenden"
            },
            {
                    "Meilenstein 1",
                    "Meilenstein 2",
                    "Meilenstein 3",
                    "Meilenstein 4"
            },
            {
                    "Kantendetektion",
                    "Normalenbild",
                    "Cel-Shading"
            }
    };

    public static String[][] subSubMenuItemNames = {
            {
                    "MS1 Alle",
                    "MS1 Aufgabe 1"
            },
            {
                    "MS2 Alle",
                    "MS2 Demo-Szene 1",
                    "MS2 Demo-Szene 2",
                    "MS2 Demo-Szene 3",
                    "MS2 Demo-Szene 4",
                    "MS2 Demo-Szene 5",
                    "MS2 Demo-Szene 6",
                    "MS2 Demo-Szene 7"
            },
            {
                    "MS3 Alle",
                    "MS3 Demo-Szene 3",
                    "MS3 Demo-Szene 4",
                    "MS3 Demo-Szene 5",
                    "MS3 Demo-Szene 6",
                    "MS3 Demo-Szene 7",
                    "MS3 Demo-Szene 8",
                    "MS3 Demo-Szene 9",
                    "MS3 Eigene Demo-Szene"
            },
            {
                    "MS4 Alle",
                    "MS4 Demo-Szene 1",
                    "MS4 Demo-Szene 2",
                    "MS4 Demo-Szene 3"
            }
    };


    public MenuBar(RayTracer raytracer) {
        this.raytracer = raytracer;
        for (int i = 0; i < mainMenuItemNames.length; i++) {
            JMenu menu = new JMenu(mainMenuItemNames[i]);
            for (int j = 0; j < subMenuItemNames[i].length; j++) {
                JMenuItem item;
                if (i == 2) {
                    item = createToggleItem(subMenuItemNames[i][j]);
                } else if (i == 1) {
                    item = createDropdownItem(subMenuItemNames[i][j]);
                } else {
                    item = createItem(subMenuItemNames[i][j]);
                }
                if (i == 1) {
                    for (int k = 0; k < subSubMenuItemNames[j].length; k++) {
                        JMenuItem subItem = createItem(subSubMenuItemNames[j][k]);
                        subItem.addActionListener(this);
                        item.add(subItem);
                    }
                }
                if (item != null) {
                    item.addActionListener(this);
                    menu.add(item);
                }
            }
            this.add(menu);
        }
    }

    private JMenuItem createToggleItem(String name) {
        return new JCheckBoxMenuItem(name);
    }
    private JMenuItem createItem(String name) {
        return new JMenuItem(name);
    }
    private JMenu createDropdownItem(String name) {
        return new JMenu(name);
    }


    /**
     * Handles the button in the menubar
     * @param e which action is performed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(subMenuItemNames[2][0])) {
            this.raytracer.toggleEdgeDetection();
        }
        if (e.getActionCommand().equals(subMenuItemNames[2][1])) {
            this.raytracer.toggleNormalImage();
        }
        if (e.getActionCommand().equals(subMenuItemNames[2][2])) {
            this.raytracer.toggleCelShading();
        }

        if (e.getActionCommand().equals(subSubMenuItemNames[0][0])) {
            Exercise1Testing.all();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[1][0])) {
            Exercise2Testing.all();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][0])) {
            Exercise3Testing.all();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[3][0])) {
            Exercise4Testing.all();
        }

        if (e.getActionCommand().equals(subSubMenuItemNames[0][1])) {
            Exercise1Testing.runExample1();
        }

        if (e.getActionCommand().equals(subSubMenuItemNames[1][1])) {
            Exercise2Testing.runEx1();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[1][2])) {
            Exercise2Testing.runEx2();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[1][3])) {
            Exercise2Testing.runEx3();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[1][4])) {
            Exercise2Testing.runEx4();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[1][5])) {
            Exercise2Testing.runEx5();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[1][6])) {
            Exercise2Testing.runEx6();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[1][7])) {
            Exercise2Testing.runEx7();
        }

        if (e.getActionCommand().equals(subSubMenuItemNames[2][1])) {
            Exercise3Testing.runExample3();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][2])) {
            Exercise3Testing.runExample4();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][3])) {
            Exercise3Testing.runExample5();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][4])) {
            Exercise3Testing.runExample6();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][5])) {
            Exercise3Testing.runExample7();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][6])) {
            Exercise3Testing.runExample8();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][7])) {
            Exercise3Testing.runExample9();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[2][8])) {
            Exercise3Testing.ownExample();
        }

        if (e.getActionCommand().equals(subSubMenuItemNames[3][1])) {
            Exercise4Testing.runExample1();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[3][2])) {
            Exercise4Testing.runExample2();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[3][3])) {
            Exercise4Testing.runExample3();
        }


        /**
         * If save is clicked
         */
        if (e.getActionCommand().equals(subMenuItemNames[0][0])) {
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
                    ImageIO.write(this.raytracer.getImageContainer().getImage(), whatToSave, saveFile);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (e.getActionCommand().equals(subMenuItemNames[0][1])) {
            System.exit(0);
        }
    }
}
