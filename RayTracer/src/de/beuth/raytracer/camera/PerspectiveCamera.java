/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.camera;

import de.beuth.raytracer.camera.interfaces.IPerspectiveCamera;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;

public class PerspectiveCamera extends Camera implements IPerspectiveCamera {

    /**
     * angle factor angle
     */
    public final double angle;

    /**
     * initializing e, g, t and angle
     * @param e eye position
     * @param g gaze vector
     * @param t up vector
     * @param angle angle factor
     */
    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
        super(e, g, t);
        this.angle = angle;
    }

    /**
     * ray for one pixel will be reflected
     * @param w1 width of the picture
     * @param h1 height of the picture
     * @param x1 coordinate of the pixel
     * @param y1 coordinate of the pixel
     * @return a generated ray of one pixel
     */
    @Override
    public Ray rayFor(final int w1, final int h1, final int x1, final int y1) {

        double w = (double) w1;
        double h = (double) h1;
        double x = (double) x1;
        double y = (double) y1;

        Point3 o = e;

        Vector3 r = this.w.mul(-1).mul((h/2)/(Math.tan(angle/2))).add(u.mul(x-((w-1)/2))).add(v.mul(y-((h-1)/2)));
        Vector3 d = r.normalized();
        return new Ray(o,d);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PerspectiveCamera that = (PerspectiveCamera) o;

        if (Double.compare(that.angle, angle) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PerspectiveCamera{" +
                "angle=" + angle +
                '}';
    }
}
