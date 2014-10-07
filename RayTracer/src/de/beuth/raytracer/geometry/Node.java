/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.geometry;


import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Transform;

import java.util.ArrayList;

/**
 * A scene graph node that combines several geometries under one node and applies a transformation on the geometries
 */
public class Node extends Geometry {

    /**
     * The transformation of the objects
     */
    public final Transform transform;

    /**
     * The geometries of the node
     */
    public final ArrayList<Geometry> geoList;

    /**
     * creates a new node object having a Transform object, a list og geometries and a material as param
     * @param transform transformation object
     * @param geoList ArrayList of geometries
     */
    public Node(final Transform transform, final ArrayList<Geometry> geoList, Material material) {
        super(material);
        this.transform = transform;
        this.geoList = geoList;
    }

    /**
     * looks for a hit between a transformed ray and the geometries
     * @param r ray which is used for calculating the hits
     * @return an new hit object
     */
    @Override
    public Hit hit(final Ray r) {
        Ray tr = transform.mul(r);
        Hit minimalHit = null;
        double test = Double.MAX_VALUE;

        for (Geometry geo : geoList) {
            Hit hit = geo.hit(tr);
            if (hit == null) continue;

            if (hit == null) continue;
            if (hit.t < test && hit.t > Geometry.EPSILON) {

                test = hit.t;
                minimalHit = hit;
            }
        }
        if (minimalHit != null) {
            return new Hit(minimalHit.t, r, minimalHit.geo, transform.mul(minimalHit.n), minimalHit.tc);
        }
        return null;
    }

    @Override
    public Geometry convertToSingleColorMaterial() {
        return new Node(this.transform, this.geoList, this.material);
    }

    @Override
    public Geometry convertToCelShadingMaterial() {
        return new Node(this.transform, this.geoList, this.material);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Node node = (Node) o;

        if (geoList != null ? !geoList.equals(node.geoList) : node.geoList != null) {
            return false;
        }
        if (transform != null ? !transform.equals(node.transform) : node.transform != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (transform != null ? transform.hashCode() : 0);
        result = 31 * result + (geoList != null ? geoList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "transform=" + transform +
                ", geoList=" + geoList +
                '}';
    }
}
