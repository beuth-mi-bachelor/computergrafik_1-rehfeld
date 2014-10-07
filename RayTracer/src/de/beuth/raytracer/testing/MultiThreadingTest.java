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
import de.beuth.raytracer.material.Material;
import de.beuth.raytracer.material.PhongMaterial;
import de.beuth.raytracer.material.ReflectiveMaterial;
import de.beuth.raytracer.material.TransparentMaterial;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.texture.SingleColorTexture;
import de.beuth.raytracer.world.World;

import javax.swing.*;
import java.util.ArrayList;

public class MultiThreadingTest {

    public static final String baseURL = "src/de/beuth/raytracer/models/";
    public static final String teddy = baseURL + "teddy.obj";

    public static void runExample1(final Integer procs) {

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        Material reflective_material_plane = new ReflectiveMaterial(new SingleColorTexture(new Color(1, 1, 1)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 1, 1)));
        Plane plane = new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), reflective_material_plane);

        double r = 0.5;
        SingleColorTexture specColor = new SingleColorTexture(new Color(1, 1, 1));
        int exp = 10;
        SingleColorTexture reflection = new SingleColorTexture(new Color(1, 0.5, 0.5));

        Sphere sphere1 = new Sphere(new Point3(0, 1, 0), r, new ReflectiveMaterial(new SingleColorTexture(new Color(1, 0, 0)), specColor, exp, reflection));
        Sphere sphere2 = new Sphere(new Point3(-1.5, 1, 0), r, new ReflectiveMaterial(new SingleColorTexture(new Color(0, 1, 0)), specColor, exp, reflection));
        Sphere sphere3= new Sphere(new Point3(1.5, 1, 0), r, new ReflectiveMaterial(new SingleColorTexture(new Color(0, 0, 1)), specColor, exp, reflection));
        Sphere sphere4 = new Sphere(new Point3(0, 1, -1.5), r, new ReflectiveMaterial(new SingleColorTexture(new Color(0, 1, 1)), specColor, exp, reflection));
        Sphere sphere5 = new Sphere(new Point3(-1.5, 1, -1.5), r, new ReflectiveMaterial(new SingleColorTexture(new Color(1, 0, 1)), specColor, exp, reflection));
        Sphere sphere6 = new Sphere(new Point3(1.5, 1, -1.5), r, new ReflectiveMaterial(new SingleColorTexture(new Color(1, 1, 0)), specColor, exp, reflection));

        Sphere sphere7 = new Sphere(new Point3(0, 2, 1.5), r, new TransparentMaterial(1.33));
        Sphere sphere8 = new Sphere(new Point3(-1.5, 2, 1.5), r, new TransparentMaterial(1.33));
        Sphere sphere9 = new Sphere(new Point3(1.5, 2, 1.5), r, new TransparentMaterial(1.33));

        AxisAlignedBox box = new AxisAlignedBox(new Point3(-0.5, 0, 3), new Point3(0.5, 1, 4), new TransparentMaterial(1.33));

        Material phongMaterial = new PhongMaterial(new SingleColorTexture(new Color(0, 1, 0)), new SingleColorTexture(new Color(0, 1, 0)), 20);
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

        new RayTracer(world, Exercise4Testing.camera, procs);


    }

    public static void runExample2(final Integer procs) {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane);
        geoList.add(Exercise3Testing.sphere);
        geoList.add(Exercise3Testing.box);
        geoList.add(Exercise3Testing.triangle);
        PointLight light = new PointLight(new Color(1,1,1), new Point3(4,4,4), false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLight, 0);
        new RayTracer(world, Exercise3Testing.camera, procs);
    }

    public static void runExample3(final Integer procs) {
        String toRender = teddy;
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Light> lights = new ArrayList<Light>();

        ShapeFromFile test = new ShapeFromFile(toRender, new PhongMaterial(new SingleColorTexture(new Color(1, 1, 1)), new SingleColorTexture(new Color(1, 1, 1)), 64));
        Node testnode = test.OBJLoader();
        PerspectiveCamera cam;
        Light light;

        cam = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);

        light = new DirectionalLight(new Color(1, 1, 1), new Vector3(0.5, 1, 1), false);

        lights.add(light);
        geoList.add(testnode);
        World world;

        world = new World(geoList, lights, new Color(0, 0, 0), 1);

        new RayTracer(world, cam, procs);
    }


    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
                    MultiThreadingTest.runExample1(i);
                    MultiThreadingTest.runExample2(i);
                    MultiThreadingTest.runExample3(i);
                }

            }
        });
    }
}
