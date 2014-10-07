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
import java.text.DateFormat;
import java.util.*;


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

    public StatusBar statusBar;

    private Long started;
    private Long ended;

    public RayTracer() {
        this.doPreWindowOperations(null);
        this.doPostWindowOperations();
    }

    public RayTracer(World world, Camera camera) {
        this.doPreWindowOperations(null);
        this.imageContainer = new ImageMaker(world, camera);
        this.doPostWindowOperations();
    }

    public RayTracer(World world, Camera camera, final Integer processors) {
        this.doPreWindowOperations(processors);
        this.imageContainer = new ImageMaker(world, camera, processors);
        this.doPostWindowOperations();
    }

    private void doPreWindowOperations(final Integer processors) {
        /**
         * Set width and height of window
         */
        //this.setSize(new Dimension(WIDTH, HEIGHT));
        this.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT+StatusBar.HEIGHT+MenuBar.HEIGHT));
        this.pack();
        this.centerWindow();

        if (processors != null) {
            this.setTitle("RayTracer | processors in use: " + processors);
        } else {
            this.setTitle("RayTracer");
        }

        /**
         * Creates the menü
         */
        final MenuBar menu = new MenuBar(this);
        this.setJMenuBar(menu);

        this.statusBar = new StatusBar();
        this.add(this.statusBar, java.awt.BorderLayout.SOUTH);
    }

    private void doPostWindowOperations() {
        /**
         * Add this frame to the window
         */
        if (this.imageContainer != null) {
            this.add(this.imageContainer);
        }
        this.addWindowListener(listener);

        this.started = System.nanoTime();
        DateFormat df;
        df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        this.statusBar.setMessage("started at: " + df.format(new Date(started)) + " | calculating...");
        this.setVisible(true);

        this.paint(this.getGraphics());

        if (this.imageContainer != null) {
            this.imageContainer.makeImage();
        }

        this.ended = System.nanoTime();

        Long diff = (this.ended - this.started) / 1000000000;

        int minutes = Math.round(diff / 60);
        int seconds = Math.round(diff % 60);

        this.statusBar.setMessage("calculation time: " + df.format(new Date(ended)) + " | finished in " + fillWithZeros(minutes, 2) + ":" + fillWithZeros(seconds, 2) + " minutes");

        this.repaint();
    }

    public String fillWithZeros(int val, int len) {
        String str = Integer.toString(val);
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
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
        this.setLocationRelativeTo(null);
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