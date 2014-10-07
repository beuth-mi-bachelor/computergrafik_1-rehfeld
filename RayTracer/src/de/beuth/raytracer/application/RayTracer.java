/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */
package de.beuth.raytracer.application;

import de.beuth.raytracer.camera.Camera;
import de.beuth.raytracer.camera.OrthographicCamera;
import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Geometry;
import de.beuth.raytracer.light.Light;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class RayTracer extends JFrame {

    public static int OPEN_WINDOWS = 0;

    /**
     * static Width and Height declaration
     */
    final static int WIDTH = 640;
    final static int HEIGHT = 480;

    /**
     * The Frame inside containing the image
     */
    private ImageMaker imageContainer;

    public RayTracer(World world, Camera camera) {
        this.doPreWindowOperations();
        this.imageContainer = new ImageMaker(this, world, camera);
        this.doPostWindowOperations();
    }

    public RayTracer() {
        this.doPreWindowOperations();
        final OrthographicCamera camera = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, 0), new Vector3(0, 0, 0), 0);
        final World world = new World(new ArrayList<Geometry>(), new ArrayList<Light>(), new Color(0, 0, 0), 0);
        this.imageContainer = new ImageMaker(this, world, camera);
        this.doPostWindowOperations();
    }

    private void doPreWindowOperations() {
        /**
         * Set width and height of window
         */
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.centerWindow();

        /**
         * Creates the menü
         */
        MenuBar menu = new MenuBar(this);
        this.setJMenuBar(menu);
    }

    private void doPostWindowOperations() {
        /**
         * Add this frame to the window
         */
        this.add(this.imageContainer);
        this.addWindowListener(listener);
        this.setVisible(true);

    }

    private WindowListener listener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {
            OPEN_WINDOWS++;
        }
        @Override
        public void windowClosing(WindowEvent e) {
            OPEN_WINDOWS--;
            if (OPEN_WINDOWS == 0) {
                System.exit(0);
            }
        }
        @Override
        public void windowClosed(WindowEvent e) {}
        @Override
        public void windowIconified(WindowEvent e) {}
        @Override
        public void windowDeiconified(WindowEvent e) {}
        @Override
        public void windowActivated(WindowEvent e) {}
        @Override
        public void windowDeactivated(WindowEvent e) {}
    };

    /**
     * centers the window on screen
     */
    private void centerWindow() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int X = (screen.width / 2) - (RayTracer.WIDTH / 2);
        int Y = (screen.height / 2) - (RayTracer.HEIGHT / 2);
        this.setBounds(X, Y, RayTracer.WIDTH, RayTracer.HEIGHT);
    }

    public ImageMaker getImageContainer() {
        return this.imageContainer;
    }

    public void toggleEdgeDetection() {
        this.imageContainer.toggleEdgeDetection();
    }

    public void toggleCelShading() {
        this.imageContainer.toggleCelShading();
    }

    public void toggleNormalImage() {
        this.imageContainer.toggleNormalImage();
    }

    public void toggleNormalAndEdge() {
        this.imageContainer.toggleNormalAndEdge();
    }

    public static void main(String[] args) {
        new RayTracer();
    }

}