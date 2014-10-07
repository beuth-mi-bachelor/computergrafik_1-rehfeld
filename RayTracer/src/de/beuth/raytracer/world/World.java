/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.world;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Geometry;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.light.Light;
import de.beuth.raytracer.mathlibrary.Ray;

import java.util.ArrayList;

/**
 * describes the world
 */
public class World {

    /**
     * background color
     */
    public static final Color BACKGROUND_COLOR = new Color(0, 0, 0);

    /**
     * refraction index
     */
    public final double refractionIndex;

    /**
     * list of geometries to be displayed
     */
    public final ArrayList<Geometry> geoList;

    /**
     * list of all lights
     */
    public final ArrayList<Light> ambientLights;

    public final Color ambientLight;

    /**
     * creates a new instance of world
     * @param listOfGeometry ArrayList der Geometry
     */
    public World(final ArrayList<Geometry> listOfGeometry, final ArrayList<Light> lights, Color ambientLight, final double refractionIndex) {
        this.geoList = listOfGeometry;
        this.ambientLights = lights;
        this.ambientLight = ambientLight;
        this.refractionIndex = refractionIndex;
    }

    /**
     * checks all rays and geometries of hitpoints
     * @param r ray which hit is tested with
     * @return minimal t as point of hit
     */
    public Hit hit(final Ray r) {
        ArrayList<Hit> hitted = new ArrayList<Hit>();
        Hit hit = null;
        double minimalHit = Double.MAX_VALUE;
        for (Geometry geometry : geoList) {
            hit = geometry.hit(r);
            if (hit != null)
                hitted.add(hit);
        }

        for (Hit h : hitted) {
            if (h.t < minimalHit) {
                minimalHit = h.t;

                if (minimalHit < Geometry.EPSILON) {
                    minimalHit = Geometry.EPSILON;
                }
                hit = h;
            }
        }
        return hit;
    }

    @Override
    public String toString() {
        return "World{" +
                "geoList=" + geoList +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        if (geoList != null ? !geoList.equals(world.geoList) : world.geoList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return geoList != null ? geoList.hashCode() : 0;
    }
}

