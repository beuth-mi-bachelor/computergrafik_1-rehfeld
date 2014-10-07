/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.application;

import de.beuth.raytracer.testing.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MenuBar extends JMenuBar implements ActionListener {

    public final static int HEIGHT = 30;
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
                    "Meilenstein 4",
                    "Meilenstein 5",
                    "Meilenstein 6"
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
                    "MS2 Demo-Szene 6"
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
            },
            {
                    "MS5 Alle",
                    "MS5 Demo-Szene 1 & 2",
                    "MS5 Alle Cubics",
                    "MS5 Demo-Szene 1",
                    "MS5 Demo-Szene 2",
                    "MS5 Demo-Szene Teddy",
                    "MS5 Demo-Szene Bunny",
                    "MS5 Demo-Szene Cube v",
                    "MS5 Demo-Szene Cube v blocks",
                    "MS5 Demo-Szene Cube v blocks weird indices",
                    "MS5 Demo-Szene Cube v vn",
                    "MS5 Demo-Szene Cube v vt",
                    "MS5 Demo-Szene Cube v vn vt",
                    "MS5 Demo-Szene Cube v vn vt comments",
                    "MS5 Demo-Szene Oren-Nayar Material"
            },
            {
                    "MS6 Alle",
                    "MS6 Earth Day ImageTexture",
                    "MS6 Earth Night ImageTexture",
                    "MS6 Earth Day InterpolatedImageTexture",
                    "MS6 Earth Night InterpolatedImageTexture",
                    "MS6 Own Demo Scene",
                    "MS6 Changing Material"
            }
    };


    public MenuBar(RayTracer raytracer) {
        this.raytracer = raytracer;
        this.setSize(RayTracer.WIDTH, HEIGHT);
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
        if (e.getActionCommand().equals(subSubMenuItemNames[4][0])) {
            Exercise5Testing.all();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[5][0])) {
            Exercise6Testing.all();
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

        if (e.getActionCommand().equals(subSubMenuItemNames[4][1])) {
            Exercise5Testing.renderScenesOnly();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][2])) {
            Exercise5Testing.renderCubicsOnly();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][3])) {
            Exercise5Testing.runExample1();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][4])) {
            Exercise5Testing.runExample2();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][5])) {
            Exercise5Testing.runExample3(Exercise5Testing.teddy);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][6])) {
            Exercise5Testing.runExample3(Exercise5Testing.bunny);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][7])) {
            Exercise5Testing.runExample3(Exercise5Testing.cube_v);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][8])) {
            Exercise5Testing.runExample3(Exercise5Testing.cube_v_blocks);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][9])) {
            Exercise5Testing.runExample3(Exercise5Testing.cube_v_blocks_weird_indices);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][10])) {
            Exercise5Testing.runExample3(Exercise5Testing.cube_v_vn);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][11])) {
            Exercise5Testing.runExample3(Exercise5Testing.cube_v_vt);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][12])) {
            Exercise5Testing.runExample3(Exercise5Testing.cube_v_vt_vn);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][13])) {
            Exercise5Testing.runExample3(Exercise5Testing.cube_v_vt_vn_comments);
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[4][14])) {
            Exercise5Testing.runExample4();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[5][1])) {
            Exercise6Testing.runEasyEarthDay();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[5][2])) {
            Exercise6Testing.runEasyEarthNight();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[5][3])) {
            Exercise6Testing.runInterpolatedEarthDay();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[5][4])) {
            Exercise6Testing.runInterpolatedEarthNight();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[5][5])) {
            Exercise6Testing.runOwnDemoScene();
        }
        if (e.getActionCommand().equals(subSubMenuItemNames[5][6])) {
            Exercise6Testing.runChangingMaterial();
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
