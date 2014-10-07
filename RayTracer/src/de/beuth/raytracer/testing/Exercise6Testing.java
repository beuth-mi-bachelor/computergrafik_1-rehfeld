/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.testing;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import de.beuth.raytracer.application.RayTracer;
import de.beuth.raytracer.camera.OrthographicCamera;
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
import de.beuth.raytracer.texture.ImageTexture;
import de.beuth.raytracer.texture.InterpolatedImageTexture;
import de.beuth.raytracer.texture.SingleColorTexture;
import de.beuth.raytracer.world.World;

import javax.swing.*;
import java.util.ArrayList;

/**
 * this is the test-class
 */
public class Exercise6Testing {

    public static void runEasyEarthDay() {

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(10, 10, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

        final Material worldTexture = new SingleColorMaterial(new ImageTexture(new Color(1, 1, 1), "earth_day.jpg"));
        Sphere sphere = new Sphere(worldTexture);

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        geoList.add(sphere);

        Point3 s = new Point3(4, 4, 4);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;
        Transform transform = new Transform();
        Node sphereNode = new Node(transform.scale(s.x, s.y, s.z).translate(p.x, p.y, p.z).rotateY(ry), geoList, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));

        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(5, 5, 5), true);
        Light directionalLight = new DirectionalLight(new Color(0.4, 0.4, 0.4), new Vector3(1, -1, 0).normalized(), true);
        lights.add(pointLight);
        lights.add(directionalLight);

        geoNodeList.add(sphereNode);

        Color ambientLight = new Color(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new RayTracer(world, camera);
    }

    public static void runChangingMaterial() {

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(5, 5, 5), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

        final Material worldTexture = new TextureChangingMaterial(new ImageTexture(new Color(1, 1, 1), "earth_day.jpg"), new ImageTexture(new Color(1, 1, 1), "earth_night.jpg"));
        Sphere sphere = new Sphere(worldTexture);

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        Material reflectiveMat = new ReflectiveMaterial(new SingleColorTexture(new Color(0.1, 0.1, 0.1)), new SingleColorTexture(new Color(0, 0, 0)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
        Plane planeRef = new Plane(new Point3(-2, -2, -2), new Normal3(0, 1, 0), reflectiveMat);
        //Plane planeRef2 = new Plane(new Point3(-15, -15, -15), new Normal3(1, 0, 0), reflectiveMat);
        //Plane planeRef3 = new Plane(new Point3(-15, -15, -15), new Normal3(0, 0, 1), reflectiveMat);

        geoList.add(sphere);

        geoList.add(planeRef);
        //geoList.add(planeRef2);
        //geoList.add(planeRef3);

        Point3 s = new Point3(1, 1, 1);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;
        Transform transform = new Transform();
        Node sphereNode = new Node(transform.scale(s.x, s.y, s.z).translate(p.x, p.y, p.z).rotateY(ry), geoList, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));

        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(2, 2, 2), true);
        lights.add(pointLight);

        geoNodeList.add(sphereNode);

        Color ambientLight = new Color(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new RayTracer(world, camera);
    }

    public static void runEasyEarthNight() {

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(10, 10, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

        final Material worldTexture = new SingleColorMaterial(new ImageTexture(new Color(1, 1, 1), "earth_night.jpg"));
        Sphere sphere = new Sphere(worldTexture);

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        geoList.add(sphere);

        Point3 s = new Point3(4, 4, 4);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;
        Transform transform = new Transform();
        Node sphereNode = new Node(transform.scale(s.x, s.y, s.z).translate(p.x, p.y, p.z).rotateY(ry), geoList, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));

        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(5, 5, 5), true);
        Light directionalLight = new DirectionalLight(new Color(0.4, 0.4, 0.4), new Vector3(1, -1, 0).normalized(), true);
        lights.add(pointLight);
        lights.add(directionalLight);

        geoNodeList.add(sphereNode);

        Color ambientLight = new Color(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new RayTracer(world, camera);
    }

    public static void runInterpolatedEarthDay() {

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(10, 10, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

        final Material worldTexture = new SingleColorMaterial(new InterpolatedImageTexture(new Color(1, 1, 1), "earth_day_small.jpg"));
        Sphere sphere = new Sphere(worldTexture);

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        geoList.add(sphere);

        Point3 s = new Point3(4, 4, 4);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;
        Transform transform = new Transform();
        Node sphereNode = new Node(transform.scale(s.x, s.y, s.z).translate(p.x, p.y, p.z).rotateY(ry), geoList, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));

        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(5, 5, 5), true);
        Light directionalLight = new DirectionalLight(new Color(0.4, 0.4, 0.4), new Vector3(1, -1, 0).normalized(), true);
        lights.add(pointLight);
        lights.add(directionalLight);

        geoNodeList.add(sphereNode);

        Color ambientLight = new Color(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new RayTracer(world, camera);
    }

    public static void runInterpolatedEarthNight() {

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(10, 10, 10), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

        final Material worldTexture = new SingleColorMaterial(new InterpolatedImageTexture(new Color(1, 1, 1), "earth_night_small.jpg"));
        Sphere sphere = new Sphere(worldTexture);

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        geoList.add(sphere);

        Point3 s = new Point3(4, 4, 4);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;
        Transform transform = new Transform();
        Node sphereNode = new Node(transform.scale(s.x, s.y, s.z).translate(p.x, p.y, p.z).rotateY(ry), geoList, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));

        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(5, 5, 5), true);
        Light directionalLight = new DirectionalLight(new Color(0.4, 0.4, 0.4), new Vector3(1, -1, 0).normalized(), true);
        lights.add(pointLight);
        lights.add(directionalLight);

        geoNodeList.add(sphereNode);

        Color ambientLight = new Color(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new RayTracer(world, camera);
    }

    public static void runOwnDemoScene() {

        PerspectiveCamera camera = new PerspectiveCamera(new Point3(20, 20, 20), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

        final Material worldTexture = new TextureChangingMaterial(new ImageTexture(new Color(1, 1, 1), "earth_day.jpg"), new ImageTexture(new Color(1, 1, 1), "earth_night.jpg"));
        final Material worldTextureNight = new TextureChangingMaterial(new ImageTexture(new Color(1, 1, 1), "earth_day.jpg"), new ImageTexture(new Color(1, 1, 1), "earth_night.jpg"));

        Sphere sphere = new Sphere(worldTexture);
        Sphere sphere2 = new Sphere(worldTextureNight);
        AxisAlignedBox box = new AxisAlignedBox(worldTexture);

        Material reflectiveMat = new ReflectiveMaterial(new SingleColorTexture(new Color(0.1, 0.1, 0.1)), new SingleColorTexture(new Color(0, 0, 0)), 64, new SingleColorTexture(new Color(0.5, 0.5, 0.5)));
        Plane planeRef = new Plane(new Point3(-5, -5, -5), new Normal3(0, 1, 0), reflectiveMat);
        Plane planeRef2 = new Plane(new Point3(-15, -15, -15), new Normal3(1, 0, 0), reflectiveMat);
        Plane planeRef3 = new Plane(new Point3(-15, -15, -15), new Normal3(0, 0, 1), reflectiveMat);

        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList2 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoList3 = new ArrayList<Geometry>();
        ArrayList<Geometry> geoNodeList = new ArrayList<Geometry>();

        geoList.add(sphere);
        geoList2.add(box);
        geoList3.add(sphere2);

        Point3 s = new Point3(4, 4, 4);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;
        Transform transform = new Transform();
        Node sphereNode = new Node(transform.scale(s.x, s.y, s.z).translate(p.x, p.y, p.z).rotateY(ry), geoList, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));
        Node sphereNode2 = new Node(transform.scale(s.x+3, s.y+3, s.z+3).translate(p.x+1.5, p.y+2, p.z).rotateY(ry + 40), geoList3, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));
        Node boxNode = new Node(transform.scale(s.x - 1, s.y - 1, s.z - 1).translate(p.x, p.y + 1.5, p.z + 3).rotateY(ry), geoList2, new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));

        ArrayList<Light> lights = new ArrayList<Light>();
        Light pointLight = new PointLight(new Color(1, 1, 1), new Point3(5, 5, 5), true);
        Light directionalLight = new DirectionalLight(new Color(0.4, 0.4, 0.4), new Vector3(1, -1, 0).normalized(), true);
        lights.add(pointLight);
        lights.add(directionalLight);

        geoNodeList.add(sphereNode);
        geoNodeList.add(sphereNode2);
        geoNodeList.add(boxNode);
        geoNodeList.add(planeRef);
        geoNodeList.add(planeRef2);
        geoNodeList.add(planeRef3);

        Color ambientLight = new Color(0.25, 0.25, 0.25);
        World world = new World(geoNodeList, lights, ambientLight, 1.33);
        new RayTracer(world, camera);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Exercise6Testing.runEasyEarthDay();
                Exercise6Testing.runEasyEarthNight();
                Exercise6Testing.runInterpolatedEarthDay();
                Exercise6Testing.runInterpolatedEarthNight();
                Exercise6Testing.runOwnDemoScene();
                Exercise6Testing.runChangingMaterial();
            }
        });
    }

    public static void all() {
        Exercise6Testing.main(new String[]{});
    }

}
