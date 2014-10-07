/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.application;

import javax.swing.*;
import java.awt.*;

public class StatusBar extends JPanel {

    public final static int HEIGHT = 30;
    JLabel label;

    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(RayTracer.WIDTH, HEIGHT));
        this.label = new JLabel("Starting");
        this.add(this.label);
    }

    public void setMessage(final String message) {
        this.label.setText(message);
    }

}
