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
import de.beuth.raytracer.material.*;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Transform;
import de.beuth.raytracer.mathlibrary.Vector3;
import de.beuth.raytracer.texture.SingleColorTexture;
import de.beuth.raytracer.world.World;

import javax.swing.*;
import java.util.ArrayList;

public class Exercise5Testing {

    public static final Color ambientLight = new Color(0.25, 0.25, 0.25);
    public static final String baseURL = "src/de/beuth/raytracer/models/";

    public static final String bunny = baseURL + "bunny.obj";
    public static final String cube_v = baseURL + "cube-v.obj";
    public static final String cube_v_blocks = baseURL + "cube-v-blocks.obj";
    public static final String cube_v_blocks_weird_indices = baseURL + "cube-v-blocks-weird-indices.obj";
    public static final String cube_v_vn = baseURL + "cube-v-vn.obj";
    public static final String cube_v_vt = baseURL + "cube-v-vt.obj";
    public static final String cube_v_vt_vn = baseURL + "cube-v-vt-vn.obj";
    public static final String cube_v_vt_vn_comments = baseURL + "cube-v-vt-vn-comments.obj";
    public static final String teddy = baseURL + "teddy.obj";

    public static void all() {
        //runExample1();
        //runExample2();
        //runExample3(bunny);
        runExample3(cube_v);
        runExample3(cube_v_blocks);
        runExample3(cube_v_blocks_weird_indices);
        runExample3(cube_v_vn);
        runExample3(cube_v_vt);
        runExample3(cube_v_vt_vn);
        runExample3(cube_v_vt_vn_comments);
       // runExample3(teddy);
    }

    public static void renderScenesOnly() {
        runExample1();
        runExample2();
    }

    public static void renderCubicsOnly() {
        runExample3(cube_v);
        runExample3(cube_v_blocks);
        runExample3(cube_v_blocks_weird_indices);
        runExample3(cube_v_vn);
        runExample3(cube_v_vt);
        runExample3(cube_v_vt_vn);
        runExample3(cube_v_vt_vn_comments);
    }

    public static void runExample1() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        PerspectiveCamera cam = new PerspectiveCamera(new Point3(15, 15, 15), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), 45.0);
        PointLight pointLight = new PointLight(new Color(.8, .8, .8), new Point3(10, 10, 10), true);
        ArrayList<Light> lights = new ArrayList<Light>();

        lights.add(pointLight);
        int exponent = 64;
        ReflectiveMaterial reflectiveMaterial = new ReflectiveMaterial(new SingleColorTexture(new Color(1, 0, 0)), new SingleColorTexture(new Color(.8, .8, .8)), exponent, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
        Sphere sphere = new Sphere(new Point3(0, 0, 0), 1.0, reflectiveMaterial);
        geoList.add(sphere);

        Point3 p1 = new Point3(3.6, 3, 0.8);
        Point3 p2 = new Point3(0, 1, -4);
        Transform transform = new Transform();

        SingleColorMaterial singleColorMaterial = new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0)));
        Node kugelNode = new Node(transform.rotateZ(-Math.PI / 2).rotateX(Math.PI / 3).rotateY(Math.PI / 3).scale(p1.x, p1.y, p1.z).translate(p2.x, p2.y, p2.z), geoList, singleColorMaterial);

        ArrayList<Geometry> toRendern = new ArrayList<Geometry>();
        toRendern.add(kugelNode);

        World world = new World(toRendern, lights, Exercise5Testing.ambientLight, 1.33);
        new RayTracer(world, cam);
    }

    public static void runExample2() {
        ArrayList<Geometry> objects = new ArrayList<Geometry>();
        PerspectiveCamera perspCam = new PerspectiveCamera(new Point3(10, 10, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);

        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(10, 15, 10), true);
        ArrayList<Light> lights = new ArrayList<Light>();

        lights.add(pointLight);

        Material boxM = new BlinnPhongMaterial(new SingleColorTexture(new Color(0.75, 0.75, 0)), new SingleColorTexture(new Color(1, 1, 1)), 64);
        AxisAlignedBox box = new AxisAlignedBox(boxM);
        objects.add(box);

        Point3 p1 = new Point3(3, 0.8, 10);
        Transform translation_1 = new Transform();
        SingleColorMaterial singleColorMaterial = new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0)));
        Node boxNode = new Node(translation_1.rotateX(-Math.PI / 8).rotateZ(-Math.PI / 8).rotateY(-Math.PI / 24).scale(p1.x, p1.y, p1.z), objects, singleColorMaterial);

        ArrayList<Geometry> toRendern = new ArrayList<Geometry>();
        toRendern.add(boxNode);

        World worldUeb4_1 = new World(toRendern, lights, Exercise5Testing.ambientLight, 1.33);
        new RayTracer(worldUeb4_1, perspCam);
    }

    public static void runExample3(String toRender) {

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Light> lights = new ArrayList<Light>();

        ShapeFromFile test = new ShapeFromFile(toRender, new PhongMaterial(new SingleColorTexture(new Color(1, 1, 1)), new SingleColorTexture(new Color(1, 1, 1)), 64));
        Node testnode = test.OBJLoader();
        PerspectiveCamera cam;
        Light light;

        if (toRender.equals(bunny)) {
            cam = new PerspectiveCamera(new Point3(10, 15, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        } else {
            cam = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI/4);
        }
        if (toRender.equals(bunny) || toRender.equals(teddy)) {
            light = new DirectionalLight(new Color(1, 1, 1), new Vector3(0.5, 1, 1), false);
        } else {
            light = new PointLight(new Color(1, 1, 1), new Point3(0, 0, 0), false);
        }

        lights.add(light);
        geoList.add(testnode);
        World world;

        if (toRender.equals(bunny) || toRender.equals(teddy)) {
            world = new World(geoList, lights, new Color(0, 0, 0), 1);
        } else {
            world = new World(geoList, lights, new Color(0, 0, 0), 1);
        }
        new RayTracer(world, cam);
    }

    public static void runExample4() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Light> lights = new ArrayList<Light>();
        World world = new World(geoList, lights, new Color(0.25, 0.25, 0.25), 1);

        PerspectiveCamera cam = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4.0);
        Sphere sphere1 = new Sphere(new Point3(-3, 1, 0), 1, new OrenNayarMaterial(new SingleColorTexture(new Color(1, 0, 0)), new SingleColorTexture(new Color(1, 0, 0)), 64));
        Sphere sphere2 = new Sphere(new Point3(0, 1, 0), 1, new OrenNayarMaterial(new SingleColorTexture(new Color(0, 1, 0)), new SingleColorTexture(new Color(1, 0, 0)), 64));
        Sphere sphere3 = new Sphere(new Point3(3, 1, 0), 1, new OrenNayarMaterial(new SingleColorTexture(new Color(0, 0, 1)), new SingleColorTexture(new Color(1, 0, 0)), 64));
        Material reflective_material_plane = new ReflectiveMaterial(new SingleColorTexture(new Color(1, 1, 1)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 1, 1)));
        Plane refplane = new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), reflective_material_plane);

        geoList.add(refplane);
        geoList.add(sphere1);
        geoList.add(sphere2);
        geoList.add(sphere3);

        lights.add(new PointLight(new Color(1, 1, 1), new Point3(4, 2, 4), true));

        new RayTracer(world, cam);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Exercise5Testing.all();
            }
        });
    }

}