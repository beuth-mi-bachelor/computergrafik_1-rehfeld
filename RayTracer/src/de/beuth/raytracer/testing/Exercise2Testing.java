/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.testing;

import de.beuth.raytracer.application.RayTracer;
import de.beuth.raytracer.camera.OrthographicCamera;
import de.beuth.raytracer.camera.PerspectiveCamera;
import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.*;
import de.beuth.raytracer.light.Light;
import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.material.SingleColorMaterial;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

import javax.swing.*;
import java.util.ArrayList;

/**
 * this is the test-class
 */
public class Exercise2Testing {



    // exercise 1
    public static final Material ex1_mat = new SingleColorMaterial(new Color(0, 1, 0));
    public static final Plane ex1_plane = new Plane(new Point3(0, -1, 0), new Normal3(0, 1, 0), ex1_mat);
    public static final PerspectiveCamera ex1_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    // exercise 2
    public static final Material ex2_mat = new SingleColorMaterial(new Color(1, 0, 0));
    public static final Sphere ex2_sphere = new Sphere(new Point3(0, 0, -3), 0.5,  ex2_mat);
    public static final PerspectiveCamera ex2_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));


    //exercise 3
    public static final Material ex3_mat = new SingleColorMaterial(new Color(0, 0, 1));
    public static final AxisAlignedBox ex3_box = new AxisAlignedBox(new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), ex3_mat);
    public static final PerspectiveCamera ex3_camera = new PerspectiveCamera(new Point3(3, 3, 3), new Vector3(-3, -3, -3), new Vector3(0, 1, 0), (Math.PI / 4));

    //exercise 4
    public static final Material ex4_mat = new SingleColorMaterial(new Color(1, 0, 1));
    public static final Triangle ex4_triangle = new Triangle(new Point3(-0.5, 0.5, -3), new Point3(0.5, 0.5, -3), new Point3(0.5, -0.5, -3), new Normal3(0, 0, 1), new Normal3(0, 0, 1), new Normal3(0, 0, 1), ex4_mat);
    public static final PerspectiveCamera ex4_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    // exercise 5
    public static final Material ex5_mat = new SingleColorMaterial(new Color(1, 0, 0));
    public static final Sphere ex5_sphere_1 = new Sphere(new Point3(-1, 0, -3), 0.5,  ex5_mat);
    public static final Sphere ex5_sphere_2 = new Sphere(new Point3(1, 0, -6), 0.5,  ex5_mat);
    public static final PerspectiveCamera ex5_camera = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    // exercise 6
    public static final Material ex6_mat = new SingleColorMaterial(new Color(1, 0, 0));
    public static final Sphere ex6_sphere_1 = new Sphere(new Point3(-1, 0, -3), 0.5,  ex6_mat);
    public static final Sphere ex6_sphere_2 = new Sphere(new Point3(1, 0, -6), 0.5,  ex6_mat);
    public static final OrthographicCamera ex6_camera = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);

    // exercise 7
    public static final Material ex7_mat = new SingleColorMaterial(new Color(1, 0, 0));
    public static final Cylinder ex7_cyl = new Cylinder(new Vector3(1, 0, 1), new Vector3(0.5, 1, 0.5), 1, 2, ex7_mat);
    public static final PerspectiveCamera ex7_camera = new PerspectiveCamera(new Point3(1, 1, 20), new Vector3(0, 0, -10), new Vector3(1, 1, 1), (Math.PI / 4));

    public static void runEx1() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex1_plane);
        ArrayList<Light> lights = new ArrayList<Light>();
        Color ambientLight = new Color(0, 0, 0);
        World world = new World(geoList, lights, ambientLight, 0);
        new RayTracer(world, Exercise2Testing.ex1_camera);
    }

    public static void runEx2() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex2_sphere);
        ArrayList<Light> lights = new ArrayList<Light>();
        Color ambientLight = new Color(0, 0, 0);
        World world = new World(geoList, lights, ambientLight, 0);
        new RayTracer(world, Exercise2Testing.ex2_camera);
    }

    public static void runEx3() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex3_box);
        ArrayList<Light> lights = new ArrayList<Light>();
        Color ambientLight = new Color(0, 0, 0);
        World world = new World(geoList, lights, ambientLight, 0);
        new RayTracer(world, Exercise2Testing.ex3_camera);
    }

    public static void runEx4() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex4_triangle);
        ArrayList<Light> lights = new ArrayList<Light>();
        Color ambientLight = new Color(0, 0, 0);
        World world = new World(geoList, lights, ambientLight, 0);
        new RayTracer(world, Exercise2Testing.ex4_camera);
    }

    public static void runEx5() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex5_sphere_1);
        geoList.add(Exercise2Testing.ex5_sphere_2);
        ArrayList<Light> lights = new ArrayList<Light>();
        Color ambientLight = new Color(0, 0, 0);
        World world = new World(geoList, lights, ambientLight, 0);
        new RayTracer(world, Exercise2Testing.ex5_camera);
    }

    public static void runEx6() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex6_sphere_1);
        geoList.add(Exercise2Testing.ex6_sphere_2);
        ArrayList<Light> lights = new ArrayList<Light>();
        Color ambientLight = new Color(0, 0, 0);
        World world = new World(geoList, lights, ambientLight, 0);
        new RayTracer(world, Exercise2Testing.ex6_camera);
    }

    public static void runEx7() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise2Testing.ex7_cyl);
        ArrayList<Light> lights = new ArrayList<Light>();
        Color ambientLight = new Color(0, 0, 0);
        World world = new World(geoList, lights, ambientLight, 0);
        new RayTracer(world, Exercise2Testing.ex7_camera);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Exercise2Testing.runEx1();
                Exercise2Testing.runEx2();
                Exercise2Testing.runEx3();
                Exercise2Testing.runEx4();
                Exercise2Testing.runEx5();
                Exercise2Testing.runEx6();
                Exercise2Testing.runEx7();
            }
        });
    }

    public static void all() {
        Exercise2Testing.main(new String[] {});
    }

}
