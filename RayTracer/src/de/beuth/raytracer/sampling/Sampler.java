/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.sampling;

import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.material.Tracer;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

import java.util.ArrayList;
import java.util.List;

public class Sampler {

    public static Color colorFor(final World world, final Hit hit, final Ray r) {
        Color sum = World.BACKGROUND_COLOR;
        final List<Ray> rays = getRays(r, hit.t, 0.1);
        for (Ray ray : rays) {
            Hit hitted = world.hit(ray);
            final Color hitColor = hit.geo.material.colorFor(hitted, world, new Tracer(world, 1));
            sum = sum.add(hitColor.mul(1F/rays.size()));
        }
        return sum;
    }

    private static List<Ray> getRays(final Ray source, final double t, final double off) {
        List<Ray> rays = new ArrayList<Ray>();
        rays.add(source);
        Point3 point = source.at(t);
        rays.add(new Ray(new Point3(source.o.x, source.o.y, source.o.z), new Vector3(point.x + off, point.y, point.z)));
        rays.add(new Ray(new Point3(source.o.x, source.o.y, source.o.z), new Vector3(point.x - off, point.y, point.z)));
        rays.add(new Ray(new Point3(source.o.x, source.o.y, source.o.z), new Vector3(point.x, point.y + off, point.z)));
        rays.add(new Ray(new Point3(source.o.x, source.o.y, source.o.z), new Vector3(point.x, point.y - off, point.z)));
        rays.add(new Ray(new Point3(source.o.x, source.o.y, source.o.z), new Vector3(point.x, point.y, point.z + off)));
        rays.add(new Ray(new Point3(source.o.x, source.o.y, source.o.z), new Vector3(point.x, point.y, point.z - off)));
        return rays;
    }
}
