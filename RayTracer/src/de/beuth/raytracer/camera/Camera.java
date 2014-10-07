/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.camera;

import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;

public abstract class Camera {

    /**
     * eye position point
     */
    public final Point3 e;
    /**
     * gaze vector
     */
    public final Vector3 g;
    /**
     * up vector
     */
    public final Vector3 t;
    /**
     * new axis u
     */
    public Vector3 u;
    /**
     * new axis v
     */
    public Vector3 v;
    /**
     * new axis w
     */
    public Vector3 w;

    /**
     * initializing e, g, t and calculate w, u, v
     * @param e eye position
     * @param g gaze vector
     * @param t up vector
     */

    public Camera(final Point3 e, final Vector3 g, final Vector3 t) {

        this.e = e;
        this.g = g;
        this.t = t;

        this.w = g.normalized().mul(-1);
        this.u = t.x(w).normalized();
        this.v = w.x(u);
    }

    /**
     * ray for one pixel will be reflected
     * @param w width of the picture
     * @param h height of the picture
     * @param x coordinate of the pixel
     * @param y coordinate of the pixel
     * @return a generated ray of one pixel
     */
    public Ray rayFor(final int w, final int h, final int x, final int y) {

        Vector3 d = this.w.mul(-1);
        Point3 o = e.add(u.mul(x-((w-1)/2)).add(v.mul(y-((h-1)/2))));
        return new Ray(o,d);
    }

    @Override
    public String toString() {
        return "Camera{" +
                "e=" + e +
                ", g=" + g +
                ", t=" + t +
                ", u=" + u +
                ", v=" + v +
                ", w=" + w +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Camera camera = (Camera) o;

        if (e != null ? !e.equals(camera.e) : camera.e != null) return false;
        if (g != null ? !g.equals(camera.g) : camera.g != null) return false;
        if (t != null ? !t.equals(camera.t) : camera.t != null) return false;
        if (u != null ? !u.equals(camera.u) : camera.u != null) return false;
        if (v != null ? !v.equals(camera.v) : camera.v != null) return false;
        if (w != null ? !w.equals(camera.w) : camera.w != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = e != null ? e.hashCode() : 0;
        result = 31 * result + (g != null ? g.hashCode() : 0);
        result = 31 * result + (t != null ? t.hashCode() : 0);
        result = 31 * result + (u != null ? u.hashCode() : 0);
        result = 31 * result + (v != null ? v.hashCode() : 0);
        result = 31 * result + (w != null ? w.hashCode() : 0);
        return result;
    }
}
