/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;

import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Transform;

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

    private Node right;
    private Node top;
    private Node front;
    private Node left;
    private Node bottom;
    private Node back;

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

        ArrayList<Geometry> geos = new ArrayList<Geometry>();
        geos.add(new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), material));
        left = new Node(new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateZ(Math.PI/2), geos, this.material);
        bottom =  new Node(new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateX(Math.PI), geos, this.material);
        back = new Node(new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateZ(Math.PI).rotateX(-Math.PI/2), geos, this.material);
        right = new Node(new Transform().translate(this.run.x, this.run.y, this.run.z).rotateZ(-Math.PI/2), geos, this.material);
        top =  new Node(new Transform().translate(this.run.x, this.run.y, this.run.z), geos, this.material);
        front = new Node(new Transform().translate(this.run.x, this.run.y, this.run.z).rotateZ(Math.PI).rotateX(Math.PI/2), geos, this.material);

    }

    /**
     * defines a axis aligned box
     * @param material material
     */
    public AxisAlignedBox(final Material material) {
        super(material);
        this.lbf = new Point3(-0.5, -0.5, -0.5);
        this.run = new Point3(0.5, 0.5, 0.5);

        ArrayList<Geometry> geos = new ArrayList<Geometry>();
        geos.add(new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), material));
        left = new Node(new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateZ(Math.PI/2), geos, this.material);
        bottom =  new Node(new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateX(Math.PI), geos, this.material);
        back = new Node(new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateZ(Math.PI).rotateX(-Math.PI/2), geos, this.material);
        right = new Node(new Transform().translate(this.run.x, this.run.y, this.run.z).rotateZ(-Math.PI/2), geos, this.material);
        top =  new Node(new Transform().translate(this.run.x, this.run.y, this.run.z), geos, this.material);
        front = new Node(new Transform().translate(this.run.x, this.run.y, this.run.z).rotateZ(Math.PI).rotateX(Math.PI/2), geos, this.material);

    }


    /**
     * looks for a hit between a ray and a axis aligned box
     * @param r the ray to test the hit with
     * @return a Hit object or null if they dont hit
     */
    @Override
    public Hit hit(final Ray r) {

        // proof if planes are hit by ray
        final ArrayList<Hit> hits = new ArrayList<Hit>();
        final Hit[] planeHits1 = new Hit[] {left.hit(r), right.hit(r)};
        final Hit[] planeHits2 = new Hit[] {top.hit(r), bottom.hit(r)};
        final Hit[] planeHits3 = new Hit[] {front.hit(r), back.hit(r)};

        for (Hit hit : planeHits1) {
            if (hit != null) {
                Point3 p = r.at(hit.t);
                if (p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
                    hits.add(hit);
                }
            }
        }
        for (Hit hit : planeHits2) {
            if (hit != null) {
                Point3 p = r.at(hit.t);
                if (p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) {
                    hits.add(hit);
                }
            }
        }
        for (Hit hit : planeHits3) {
            if (hit != null) {
                Point3 p = r.at(hit.t);
                if (p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) {
                    hits.add(hit);
                }
            }
        }
        Hit hit = null;
        for (final Hit h : hits) {
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
        return new AxisAlignedBox(this.material.convertToSingelColorMaterial());
    }

    /**
     * converts any Material from a geometry into a celShadingMaterial
     *
     * @return new geometry with a celShadingMaterial
     */
    @Override
    public Geometry convertToCelShadingMaterial() {
        return new AxisAlignedBox(this.material.convertToCelShadingMaterial());
    }

    @Override
    public String toString() {
        return "AxisAlignedBox{" +
                "lbf=" + lbf +
                ", run=" + run +
                ", right=" + right +
                ", top=" + top +
                ", front=" + front +
                ", left=" + left +
                ", bottom=" + bottom +
                ", back=" + back +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        AxisAlignedBox that = (AxisAlignedBox) o;

        if (back != null ? !back.equals(that.back) : that.back != null) {
            return false;
        }
        if (bottom != null ? !bottom.equals(that.bottom) : that.bottom != null) {
            return false;
        }
        if (front != null ? !front.equals(that.front) : that.front != null) {
            return false;
        }
        if (lbf != null ? !lbf.equals(that.lbf) : that.lbf != null) {
            return false;
        }
        if (left != null ? !left.equals(that.left) : that.left != null) {
            return false;
        }
        if (right != null ? !right.equals(that.right) : that.right != null) {
            return false;
        }
        if (run != null ? !run.equals(that.run) : that.run != null) {
            return false;
        }
        if (top != null ? !top.equals(that.top) : that.top != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (lbf != null ? lbf.hashCode() : 0);
        result = 31 * result + (run != null ? run.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        result = 31 * result + (top != null ? top.hashCode() : 0);
        result = 31 * result + (front != null ? front.hashCode() : 0);
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (bottom != null ? bottom.hashCode() : 0);
        result = 31 * result + (back != null ? back.hashCode() : 0);
        return result;
    }
}
