/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.testing;

import de.beuth.raytracer.application.RayTracer;
import de.beuth.raytracer.camera.PerspectiveCamera;
import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.geometry.*;
import de.beuth.raytracer.light.DirectionalLight;
import de.beuth.raytracer.light.Light;
import de.beuth.raytracer.light.PointLight;
import de.beuth.raytracer.light.SpotLight;
import de.beuth.raytracer.material.*;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.world.World;

import javax.swing.*;
import java.util.ArrayList;

/**
 * this is the test-class
 */
public class Exercise4Testing {

    public static final Color ambientLight = new Color(0.25, 0.25, 0.25);
    public static final PerspectiveCamera camera = new PerspectiveCamera(new Point3(8, 8, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    public static void all() {
        runExample1();
        runExample2();
        runExample3();
    }

    public static void runExample1() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        Material reflective_material_plane = new ReflectiveMaterial(new Color(0.1, 0.1, 0.1), new Color(0, 0, 0), 64, new Color(0.5, 0.5, 0.5));
        Plane plane = new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), reflective_material_plane);

        Material reflective_material_sphere1 = new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5));
        Sphere sphere1 = new Sphere(new Point3(-3, 1, 0), 1, reflective_material_sphere1);

        Material reflective_material_sphere2 = new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5));
        Sphere sphere2 = new Sphere(new Point3(0, 1, 0), 1, reflective_material_sphere2);

        Material reflective_material_sphere3 = new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5));
        Sphere sphere3 = new Sphere(new Point3(3, 1, 0), 1, reflective_material_sphere3);

        geoList.add(plane);
        geoList.add(sphere1);
        geoList.add(sphere2);
        geoList.add(sphere3);

        PointLight light = new PointLight(new Color(1, 1, 1), new Point3(8, 8, 8), true);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);

        World world = new World(geoList,lights, Exercise4Testing.ambientLight, 0);

        new RayTracer(world, Exercise4Testing.camera);
    }

    public static void runExample2() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();

        Material lambert_material_plane = new LambertMaterial(new Color(0.8, 0.8, 0.8));
        Plane plane = new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), lambert_material_plane);

        Material lambert_material_box = new LambertMaterial(new Color(1, 0, 0));
        AxisAlignedBox box = new AxisAlignedBox(new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), lambert_material_box);

        geoList.add(plane);
        geoList.add(box);

        PointLight light = new PointLight(new Color(1, 1, 1), new Point3(8, 8, 0), true);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);

        World world = new World(geoList,lights, Exercise4Testing.ambientLight, 0);

        new RayTracer(world, Exercise4Testing.camera);
    }

    public static void runExample3() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        Material reflective_material_plane = new ReflectiveMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 10, new Color(1, 1, 1));
        Plane plane = new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), reflective_material_plane);

        double r = 0.5;
        Color specColor = new Color(1, 1, 1);
        int exp = 10;
        Color reflection = new Color(1, 0.5, 0.5);

        Sphere sphere1 = new Sphere(new Point3(0, 1, 0), r, new ReflectiveMaterial(new Color(1, 0, 0), specColor, exp, reflection));
        Sphere sphere2 = new Sphere(new Point3(-1.5, 1, 0), r, new ReflectiveMaterial(new Color(0, 1, 0), specColor, exp, reflection));
        Sphere sphere3= new Sphere(new Point3(1.5, 1, 0), r, new ReflectiveMaterial(new Color(0, 0, 1), specColor, exp, reflection));
        Sphere sphere4 = new Sphere(new Point3(0, 1, -1.5), r, new ReflectiveMaterial(new Color(0, 1, 1), specColor, exp, reflection));
        Sphere sphere5 = new Sphere(new Point3(-1.5, 1, -1.5), r, new ReflectiveMaterial(new Color(1, 0, 1), specColor, exp, reflection));
        Sphere sphere6 = new Sphere(new Point3(1.5, 1, -1.5), r, new ReflectiveMaterial(new Color(1, 1, 0), specColor, exp, reflection));

        Sphere sphere7 = new Sphere(new Point3(0, 2, 1.5), r, new TransparentMaterial(1.33));
        Sphere sphere8 = new Sphere(new Point3(-1.5, 2, 1.5), r, new TransparentMaterial(1.33));
        Sphere sphere9 = new Sphere(new Point3(1.5, 2, 1.5), r, new TransparentMaterial(1.33));

        AxisAlignedBox box = new AxisAlignedBox(new Point3(-0.5, 0, 3), new Point3(0.5, 1, 4), new TransparentMaterial(1.33));

        Material phongMaterial = new PhongMaterial(new Color(0, 1, 0), new Color(0, 1, 0), 20);
        Triangle triangle = new Triangle(new Point3(0.7, 0.5, 3), new Point3(1.3, 0.5, 3), new Point3(0.7, 0.5, 4), new Normal3(0, 1, 0), new Normal3(0, 1, 0), new Normal3(0, 1, 0), phongMaterial);

        geoList.add(plane);
        geoList.add(sphere1);
        geoList.add(sphere2);
        geoList.add(sphere3);
        geoList.add(sphere4);
        geoList.add(sphere5);
        geoList.add(sphere6);
        geoList.add(sphere7);
        geoList.add(sphere8);
        geoList.add(sphere9);
        geoList.add(box);
        geoList.add(triangle);

        Light light1 = new PointLight(new Color(0.3, 0.3, 0.3), new Point3(5, 5, -10), true);
        Light light2 = new SpotLight(new Color(0.3, 0.3, 0.3), new Point3(0, 5, -10), new Vector3(0, -1, 0), Math.PI/8, true);
        Light light3 = new DirectionalLight(new Color(0.3, 0.3, 0.3), (new Vector3(1, -1, 0)).normalized(), true);

        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light1);
        lights.add(light2);
        lights.add(light3);

        World world = new World(geoList,lights, new Color(0.1, 0.1, 0.1), 1);

        new RayTracer(world, Exercise4Testing.camera);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Exercise4Testing.all();
            }
        });
    }
}
