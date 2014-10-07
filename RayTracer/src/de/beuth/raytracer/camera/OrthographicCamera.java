/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.camera;

import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;

public class OrthographicCamera extends Camera {

    /**
     * scale factor s
     */
    public final double s;

    /**
     * initializing e, g, t and s
     * @param e eye position
     * @param g gaze vector
     * @param t up vector
     * @param s scale factor
     */
    public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s) {

        super(e, g, t);
        this.s = s;
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

        Vector3 d = this.w.mul(-1);

        double x = (double) x1;
        double y = (double) y1;

        double w = (double) w1;
        double h = (double) h1;

        double a = w/h;

        double first = a*(s*((x-((w-1)/2))/(w-1)));
        Vector3 ufirst = u.mul(first);
        double second = s*((y-((h-1)/2))/(h-1));
        Vector3 vsecond = v.mul(second );
        Point3 o = e.add(ufirst).add(vsecond);
        return new Ray(o,d);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrthographicCamera that = (OrthographicCamera) o;

        if (Double.compare(that.s, s) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(s);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "OrthographicCamera{" +
                "s=" + s +
                '}';
    }
}