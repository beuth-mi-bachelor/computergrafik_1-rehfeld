/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.material.SingleColorMaterial;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;

import java.util.ArrayList;

/**
 * class represents a box
 */
public class AxisAlignedBox extends Geometry {

    /**
     * left bottom point
     */
    public final Point3 lbf;

    /**
     * right upper point
     */
    public final Point3 run;

    /**
     * defines a axis aligned box
     * @param lbf point
     * @param run point
     * @param material material
     */
    public AxisAlignedBox(final Point3 lbf, final Point3 run, final Material material) {
        super(material);
        this.lbf = lbf;
        this.run = run;
    }

    /**
     * looks for a hit between a ray and a axis aligned box
     * @param r the ray to test the hit with
     * @return a Hit object or null if they dont hit
     */
    @Override
    public Hit hit(final Ray r) {

        // new planes as surfaces of box
        final Plane left = new Plane(lbf, new Normal3(-1, 0, 0), new SingleColorMaterial(new Color(0, 0, 1)));
        final Plane bottom = new Plane(lbf, new Normal3(0, -1, 0), new SingleColorMaterial(new Color(0, 1, 0)));
        final Plane back = new Plane(lbf, new Normal3(0, 0, -1), new SingleColorMaterial(new Color(0, 0, 1)));
        final Plane right = new Plane(run, new Normal3(1, 0, 0), new SingleColorMaterial(new Color(0, 1, 0)));
        final Plane top = new Plane(run, new Normal3(0, 1, 0), new SingleColorMaterial(new Color(0, 0, 1)));
        final Plane front = new Plane(run, new Normal3(0, 0, 1), new SingleColorMaterial(new Color(1, 0, 0)));

        // proof if planes are hit by ray
        final ArrayList<Hit> hits = new ArrayList<Hit>();
            if (left.hit(r) != null) {
                hits.add(left.hit(r));
            }
        if (bottom.hit(r) != null) {
            hits.add(bottom.hit(r));
        }
        if (back.hit(r) != null) {
            hits.add(back.hit(r));
        }
        if (right.hit(r) != null) {
            hits.add(right.hit(r));
        }
        if (top.hit(r) != null) {
            hits.add(top.hit(r));
        }
        if (front.hit(r) != null) {
            hits.add(front.hit(r));
        }

        // proof which hits are in the box
        final ArrayList<Hit> hitsInside = new ArrayList<Hit>();
        for (final Hit h : hits) {
            final double x = h.r.at(h.t).x;
            final double y = h.r.at(h.t).y;
            final double z = h.r.at(h.t).z;
            final Normal3 normal = h.n;
            if (h.geo.equals(left) || h.geo.equals(right)) {
                if (lbf.y <= y && y <= run.y && lbf.z <= z && z <= run.z) {
                    hitsInside.add(new Hit(h.t, r, this, normal));
                }
            }
            if (h.geo.equals(top) || h.geo.equals(bottom)) {
                if (lbf.x <= x && x <= run.x && lbf.z <= z && z <= run.z) {
                    hitsInside.add(new Hit(h.t, r, this, normal));
                }
            }
            if (h.geo.equals(front) || h.geo.equals(back)) {
                if (lbf.x <= x && x <= run.x && lbf.y <= y && y <= run.y) {
                    hitsInside.add(new Hit(h.t, r, this, normal));
                }
            }
        }
        Hit hit = null;
        for (final Hit h : hitsInside) {
            if (hit == null || h.t < hit.t) {
                hit = h;
            }
        }
        return hit;
    }

    /**
     * converts any Material from a geometry into a singleColorMaterial
     *
     * @return new geometry with a singleColorMaterial
     */
    @Override
    public Geometry convertToSingleColorMaterial() {
        return new AxisAlignedBox(this.lbf, this.run, this.material.convertToSingelColorMaterial());
    }

    /**
     * converts any Material from a geometry into a celShadingMaterial
     *
     * @return new geometry with a celShadingMaterial
     */
    @Override
    public Geometry convertToCelShadingMaterial() {
        return new AxisAlignedBox(this.lbf, this.run, this.material.convertToCelShadingMaterial());
    }

    private Normal3 getNormalOfSite(final int inBetween) {
        switch (inBetween) {
            case 0:
                return (new Normal3(1.0, 0.0, 0.0));
            case 1:
                return (new Normal3(0.0, 1.0, 0.0));
            case 2:
                return (new Normal3(0.0, 0.0, 1.0));
            case 3:
                return (new Normal3(1.0, 0.0, 0.0));
            case 4:
                return (new Normal3(0.0, 1.0, 0.0));
            case 5:
                return (new Normal3(0.0, 0.0, 1.0));
            default:
                return (null);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AxisAlignedBox that = (AxisAlignedBox) o;

        if (lbf != null ? !lbf.equals(that.lbf) : that.lbf != null) return false;
        if (run != null ? !run.equals(that.run) : that.run != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lbf != null ? lbf.hashCode() : 0;
        result = 31 * result + (run != null ? run.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AxisAlignedBox{" +
                "lbf=" + lbf +
                ", run=" + run +
                '}';
    }
}
