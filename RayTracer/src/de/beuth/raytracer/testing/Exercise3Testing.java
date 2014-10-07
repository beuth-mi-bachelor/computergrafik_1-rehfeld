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
public class Exercise3Testing {

    public static final Color ambientLight = new Color(0.25, 0.25, 0.25);
    public static final Color ambientLightNormal = new Color(0, 0, 0);

    public static final PerspectiveCamera camera = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), (Math.PI / 4));

    public static final Material single_material_plane = new SingleColorMaterial(new Color(1, 0, 0));
    public static final Material single_material_sphere = new SingleColorMaterial(new Color(0, 1, 0));
    public static final Material single_material_box = new SingleColorMaterial(new Color(0, 0, 1));
    public static final Material single_material_triangle = new SingleColorMaterial(new Color(1, 1, 0));
    public static final Plane plane = new Plane(new Point3(0,0,0), new Normal3(0, 1, 0), single_material_plane);
    public static final Sphere sphere = new Sphere(new Point3(1, 1, 1), 0.5, single_material_sphere);
    public static final AxisAlignedBox box = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), single_material_box);
    public static final Triangle triangle = new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new Normal3(0,0,1), new Normal3(0,0,1), new Normal3(0,0,1), single_material_triangle);

    public static final Material lamber_material_plane = new LambertMaterial(new Color(1, 0, 0));
    public static final Material lambert_material_sphere = new LambertMaterial(new Color(0, 1, 0));
    public static final Material lambert_material_box = new LambertMaterial(new Color(0, 0, 1));
    public static final Material lambert_material_triangle = new LambertMaterial(new Color(1, 1, 0));
    public static final Plane plane2 = new Plane(new Point3(0,0,0), new Normal3(0, 1, 0), lamber_material_plane);
    public static final Sphere sphere2 = new Sphere(new Point3(1, 1, 1), 0.5, lambert_material_sphere);
    public static final AxisAlignedBox box2 = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), lambert_material_box);
    public static final Triangle triangle2 = new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new Normal3(0,0,1), new Normal3(0,0,1), new Normal3(0,0,1), lambert_material_triangle);

    public static final Material Phong_material_plane = new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64);
    public static final Material Phong_material_sphere = new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64);
    public static final Material Phong_material_box = new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64);
    public static final Material Phong_material_triangle = new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64);
    public static final Plane plane3 = new Plane(new Point3(0,0,0), new Normal3(0, 1, 0), Phong_material_plane);
    public static final Sphere sphere3 = new Sphere(new Point3(1, 1, 1), 0.5, Phong_material_sphere);
    public static final AxisAlignedBox box3 = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), Phong_material_box);
    public static final Triangle triangle3 = new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new Normal3(0,0,1), new Normal3(0,0,1), new Normal3(0,0,1), Phong_material_triangle);

    public static final Material BlinnPhong_material_plane = new BlinnPhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64);
    public static final Material BlinnPhong_material_sphere = new BlinnPhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64);
    public static final Material BlinnPhong_material_box = new BlinnPhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64);
    public static final Material BlinnPhong_material_triangle = new BlinnPhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64);
    public static final Plane plane4 = new Plane(new Point3(0,0,0), new Normal3(0, 1, 0), BlinnPhong_material_plane);
    public static final Sphere sphere4 = new Sphere(new Point3(1, 1, 1), 0.5, BlinnPhong_material_sphere);
    public static final AxisAlignedBox box4 = new AxisAlignedBox(new Point3(-1.5, 0.5, 0.5), new Point3(-0.5, 1.5, 1.5), BlinnPhong_material_box);
    public static final Triangle triangle4 = new Triangle(new Point3(0, 0, -1), new Point3(1, 0, -1), new Point3(1, 1, -1), new Normal3(0,0,1), new Normal3(0,0,1), new Normal3(0,0,1), BlinnPhong_material_triangle);

    public static final AxisAlignedBox box5 = new AxisAlignedBox(new Point3(-2, 0.5, 0.5), new Point3(-1, 1.5, 1.5), BlinnPhong_material_box);
    public static final Sphere sphere5 = new Sphere(new Point3(0, 1, 0), 1, BlinnPhong_material_sphere);
    public static final Sphere sphere6 = new Sphere(new Point3(2, 2, -3), 2, BlinnPhong_material_sphere);




    public static void all() {
        runExample3();
        runExample4();
        runExample5();
        runExample6();
        runExample7();
        runExample8();
        runExample9();
        ownExample();

    }

    public static void runExample3() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane);
        geoList.add(Exercise3Testing.sphere);
        geoList.add(Exercise3Testing.box);
        geoList.add(Exercise3Testing.triangle);
        PointLight light = new PointLight(new Color(1,1,1), new Point3(4,4,4), false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLight, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void runExample4() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane2);
        geoList.add(Exercise3Testing.sphere2);
        geoList.add(Exercise3Testing.box2);
        geoList.add(Exercise3Testing.triangle2);
        PointLight light = new PointLight(new Color(1,1,1), new Point3(4,4,4), false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLightNormal, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void runExample5() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane3);
        geoList.add(Exercise3Testing.sphere3);
        geoList.add(Exercise3Testing.box3);
        geoList.add(Exercise3Testing.triangle3);
        PointLight light = new PointLight(new Color(1,1,1), new Point3(4,4,4), false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLightNormal, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void runExample6() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane3);
        geoList.add(Exercise3Testing.sphere3);
        geoList.add(Exercise3Testing.box3);
        geoList.add(Exercise3Testing.triangle3);
        DirectionalLight light = new DirectionalLight(new Color(1,1,1), new Vector3(-1, -1, -1), false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLightNormal, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void runExample7() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane3);
        geoList.add(Exercise3Testing.sphere3);
        geoList.add(Exercise3Testing.box3);
        geoList.add(Exercise3Testing.triangle3);
        SpotLight light = new SpotLight(new Color(1,1,1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/14, false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLightNormal, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void runExample8() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane3);
        geoList.add(Exercise3Testing.sphere3);
        geoList.add(Exercise3Testing.box3);
        geoList.add(Exercise3Testing.triangle3);
        SpotLight light = new SpotLight(new Color(1,1,1), new Point3(5,5,5), new Vector3(-1, -1, -1), Math.PI/14, false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLight, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void runExample9() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane4);
        geoList.add(Exercise3Testing.sphere4);
        geoList.add(Exercise3Testing.box4);
        geoList.add(Exercise3Testing.triangle4);
        SpotLight light = new SpotLight(new Color(1,1,1), new Point3(5,5,5), new Vector3(-1, -1, -1), Math.PI/14, false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        World world = new World(geoList,lights, Exercise3Testing.ambientLight, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void ownExample() {
        ArrayList<Geometry> geoList = new ArrayList<Geometry>();
        geoList.add(Exercise3Testing.plane4);
        geoList.add(Exercise3Testing.box5);
        geoList.add(Exercise3Testing.sphere5);
        geoList.add(Exercise3Testing.sphere6);
        PointLight light = new PointLight(new Color(1,1,1), new Point3(4,4,4), false);
        SpotLight light1 = new SpotLight(new Color(1,1,1), new Point3(5, 5, 5), new Vector3(-0.5, -0.5, -0.5), Math.PI/9, false);
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(light);
        lights.add(light1);
        World world = new World(geoList,lights, Exercise3Testing.ambientLightNormal, 0);
        new RayTracer(world, Exercise3Testing.camera);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Exercise3Testing.all();
            }
        });
    }
}
