/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.testing;

import de.beuth.raytracer.application.RayTracer;
import de.beuth.raytracer.camera.OrthographicCamera;
import de.beuth.raytracer.camera.PerspectiveCamera;
import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.*;
import de.beuth.raytracer.geometry.Cylinder;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

import java.util.ArrayList;

/**
 * this is the test-class
 */
public class Exercise2Testing {



    // exercise 1
    public static final Plane ex1_plane = new Plane(new Point3(0, -1, 0), new Normal3(0, 1, 0), new Color(0, 1, 0));
    public static final PerspectiveCamera ex1_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    // exercise 2
    public static final Sphere ex2_sphere = new Sphere(new Point3(0, 0, -3), 0.5,  new Color(1, 0, 0));
    public static final PerspectiveCamera ex2_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));


    //exercise 3
    public static final AxisAlignedBox ex3_box = new AxisAlignedBox(new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), new Color (0, 0, 1));
    public static final PerspectiveCamera ex3_camera = new PerspectiveCamera(new Point3(3, 3, 3), new Vector3(-3, -3, -3), new Vector3(0, 1, 0), (Math.PI / 4));

    //exercise 4
    public static final Triangle ex4_triangle = new Triangle(new Point3(-0.5, 0.5, -3), new Point3(0.5, 0.5, -3), new Point3(0.5, -0.5, -3), new Color (1, 0, 1));
    public static final PerspectiveCamera ex4_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    // exercise 5
    public static final Sphere ex5_sphere_1 = new Sphere(new Point3(-1, 0, -3), 0.5,  new Color(1, 0, 0));
    public static final Sphere ex5_sphere_2 = new Sphere(new Point3(1, 0, -6), 0.5,  new Color(1, 0, 0));
    public static final PerspectiveCamera ex5_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    // exercise 6
    public static final Sphere ex6_sphere_1 = new Sphere(new Point3(-1, 0, -3), 0.5,  new Color(1, 0, 0));
    public static final Sphere ex6_sphere_2 = new Sphere(new Point3(1, 0, -6), 0.5,  new Color(1, 0, 0));
    public static final OrthographicCamera ex6_camera = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);

    // exercise 7
    public static final Cylinder ex7_cyl = new Cylinder(new Vector3(1, 0, 1), new Vector3(0.5, 1, 0.5), 1, 2, new Color(1, 0, 0));
    public static final PerspectiveCamera ex7_camera = new PerspectiveCamera(new Point3(1, 1, 20), new Vector3(0, 0, -10), new Vector3(1, 1, 1), (Math.PI / 4));

    public static void runEx1() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex1_plane);
        World world = new World(geoList);
        new RayTracer(world, Exercise2Testing.ex1_camera);
    }

    public static void runEx2() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex2_sphere);
        World world = new World(geoList);
        new RayTracer(world, Exercise2Testing.ex2_camera);
    }

    public static void runEx3() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex3_box);
        World world = new World(geoList);
        new RayTracer(world, Exercise2Testing.ex3_camera);
    }

    public static void runEx4() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex4_triangle);
        World world = new World(geoList);
        new RayTracer(world, Exercise2Testing.ex4_camera);
    }

    public static void runEx5() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex5_sphere_1);
        geoList.add(Exercise2Testing.ex5_sphere_2);
        World world = new World(geoList);
        new RayTracer(world, Exercise2Testing.ex5_camera);
    }

    public static void runEx6() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex6_sphere_1);
        geoList.add(Exercise2Testing.ex6_sphere_2);
        World world = new World(geoList);
        new RayTracer(world, Exercise2Testing.ex6_camera);
    }

    public static void runEx7() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex7_cyl);
        World world = new World(geoList);
        new RayTracer(world, Exercise2Testing.ex7_camera);
    }

}
