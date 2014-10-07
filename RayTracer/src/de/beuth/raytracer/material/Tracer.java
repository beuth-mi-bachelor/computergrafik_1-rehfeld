/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.material;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.world.World;

/**
 * class describes a tracer for the colorFor method of material
 */
public class Tracer {

    /**
     * the world working on
     */
    public final World world;

    /**
     * an internal refraction index
     */
    public int refractionIndex;

    /**
     * instanciates a tracer
     * @param world the world working on
     * @param refractionIndex an internal refraction index
     */
    public Tracer(final World world, final int refractionIndex) {
        super();
        this.world = world;
        this.refractionIndex = refractionIndex;
    }

    /**
     * color for method of the ray
     * @param r the ray
     * @return the calculated color
     */
    public Color colorFor (final Ray r) {
        /*this.refractionIndex--;

        if (this.refractionIndex > 0) {

            Hit hit = world.hit(r);

            if (hit != null) {
                Material material = hit.geo.material;
                Color color = material.colorFor(hit, world, this);
                this.refractionIndex++;
                return color;
            }
        }
        
        this.refractionIndex++;

        return World.BACKGROUND_COLOR;*/
        if (r == null) {
            throw new IllegalArgumentException("The ray cannot be null!");
        }
        if(refractionIndex <= 0){
            return world.BACKGROUND_COLOR;
        }else{
            Hit hit = world.hit(r);
            if(hit != null ){
                return hit.geo.material.colorFor(hit, world, new Tracer(world, refractionIndex-1));
            }else{
                return world.BACKGROUND_COLOR;
            }
        }
    }

    /**
     * getter for refraction index
     * @return the current refractionIndex
     */
    public int getRefractionIndex (){
        return this.refractionIndex;
    }

    /**
     * setter for refraction index
     * @param refractionIndex the index which to set depth to
     */
    public void setRefractionIndex (final int refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Tracer tracer = (Tracer) o;

        if (refractionIndex != tracer.refractionIndex) {
            return false;
        }
        if (world != null ? !world.equals(tracer.world) : tracer.world != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = world != null ? world.hashCode() : 0;
        result = 31 * result + refractionIndex;
        return result;
    }

    @Override
    public String toString() {
        return "Tracer{" +
                "world=" + world +
                ", refractionIndex=" + refractionIndex +
                '}';
    }
}
