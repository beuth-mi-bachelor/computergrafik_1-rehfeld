/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.camera.interfaces;

import de.beuth.raytracer.mathlibrary.Ray;

public abstract interface ICamera {

    public Ray rayFor(final int w, final int h, final int x, final int y);

}
