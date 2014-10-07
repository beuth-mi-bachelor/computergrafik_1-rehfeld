/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.application;

import de.beuth.raytracer.camera.Camera;
import de.beuth.raytracer.color.Color;
import de.beuth.raytracer.edgeDetection.EdgeDetection;
import de.beuth.raytracer.geometry.Geometry;
import de.beuth.raytracer.geometry.Hit;
import de.beuth.raytracer.material.Tracer;
import de.beuth.raytracer.mathlibrary.Mat3x3;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Ray;
import de.beuth.raytracer.sampling.Sampler;
import de.beuth.raytracer.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ImageMaker extends Canvas {

    private BufferedImage image;
    private boolean edgeDetection;
    private boolean normalImage;
    private boolean celShading;

    private Integer processors;

    public final static Mat3x3 ma1 = new Mat3x3(-1, -2, -1, 0, 0, 0, 1, 2, 1);
    public final static Mat3x3 ma2 = new Mat3x3(-1, 0, 1, -2, 0, 2, -1, 0, 1);

    private final World world;
    private final World celShadingWorld;
    private final Camera camera;

    public int imageWidth;
    public int imageHeight;

    public ImageMaker(final World world, final Camera camera, final Integer... processorsTest) {
        this.imageWidth = RayTracer.WIDTH;
        this.imageHeight = RayTracer.HEIGHT;
        this.image = new BufferedImage(this.imageWidth, this.imageHeight, BufferedImage.TYPE_INT_RGB);
        this.edgeDetection = false;
        this.normalImage = false;
        this.celShading = false;
        if (processorsTest.length == 0) {
            this.processors = Runtime.getRuntime().availableProcessors();
        } else {
            this.processors = processorsTest[0];
        }


        ArrayList<Geometry> celShadingGeo = new ArrayList<Geometry>();
        for(Geometry geometry : world.geoList){
            celShadingGeo.add(geometry.convertToCelShadingMaterial());
        }

        this.celShadingWorld = new World(celShadingGeo, world.ambientLights, world.ambientLight, world.refractionIndex);

        this.world = world;
        this.camera = camera;

    }

    public BufferedImage getImage() {
        return this.image;
    }

    private void updateSizes() {
        this.imageWidth = RayTracer.WIDTH;
        this.imageHeight = RayTracer.HEIGHT;
        this.image = new BufferedImage(this.imageWidth, this.imageHeight, BufferedImage.TYPE_INT_RGB);
        this.paint(this.getGraphics());
    }

    public BufferedImage makeImage() {
        this.updateSizes();
        Collection<Rectangle> listOfRectangles = this.calculateSlices();
        calculateImage(listOfRectangles, this.imageWidth, this.imageHeight);
        return this.image;
    }

    @Override
    public void paint(Graphics g) {
        if (this.edgeDetection) {
            g.drawImage(new EdgeDetection().edgeDetect(image), 0, 0, null);
        } else {
            g.drawImage(this.image, 0, 0, null);
        }
    }

    private Collection<Rectangle> calculateSlices() {

        final int w = this.imageWidth;
        final int h = this.imageHeight;

        final int slices = 10;
        final int restOfWidth = w % slices;
        final int restOfHeight = h % slices;
        final int widthToSlice = w - restOfWidth;
        final int heightToSlice = h - restOfHeight;

        java.util.List<Rectangle> listOfRectangles = new ArrayList<Rectangle>();

        for (int x = 0; x <= widthToSlice; x += slices) {
            for (int y = 0; y <= heightToSlice; y += slices) {
                Rectangle rec;
                if (x == widthToSlice && y == heightToSlice) {
                    rec = new Rectangle(x, y, x + restOfWidth, y + restOfHeight);
                } else if (x == widthToSlice) {
                    rec = new Rectangle(x, y, x + restOfWidth, y + slices);
                } else if (y == heightToSlice) {
                    rec = new Rectangle(x, y, x + slices, y + restOfHeight);
                } else {
                    rec = new Rectangle(x, y, x + slices, y + slices);
                }
                listOfRectangles.add(rec);
            }
        }
        Collections.shuffle(listOfRectangles);
        return listOfRectangles;
    }

    private void calculateImage(final Collection<Rectangle> listOfRectangles, final int w, final int h) {
        final int processors = this.processors;
        final ExecutorService executor = Executors.newFixedThreadPool(processors);

        final ImageMaker imgMaker = this;

        for (final Rectangle rect : listOfRectangles) {
            executor.execute(new Runnable() {
                public void run() {
                    renderData(rect, w, h, image.getColorModel(), image.getRaster());
                }
            });
        }

        executor.shutdown();
        try {
            // 33 Minutes until termination
            executor.awaitTermination(2000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void toggleEdgeDetection() {
        if (this.edgeDetection) {
            this.edgeDetection = false;
        } else {
            this.edgeDetection = true;
        }
        this.makeImage();
        this.repaint();
    }

    public void toggleCelShading() {
        if (this.celShading) {
            this.celShading = false;
        } else {
            this.celShading = true;
        }
        this.makeImage();
        this.doCelShading();
    }

    public void toggleNormalImage() {
        if (this.normalImage) {
            this.normalImage = false;
        } else {
            this.normalImage = true;
        }
        this.makeImage();
        this.repaint();
    }

    public void toggleNormalAndEdge() {
        if (this.normalImage) {
            this.normalImage = false;
        } else {
            this.normalImage = true;
        }
        if (this.edgeDetection) {
            this.edgeDetection = false;
        } else {
            this.edgeDetection = true;
        }
        this.makeImage();
        this.repaint();
    }

    private void doCelShading() {
        BufferedImage returnImage = deepCopy(this.image);
        BufferedImage tempImage = deepCopy(this.image);
        this.edgeDetection = true;
        BufferedImage edgeImage = new EdgeDetection().edgeDetect(this.makeImage());
        this.edgeDetection = false;
        this.image = tempImage;
        for (int i = 0; i < returnImage.getWidth(); i++) {
            for (int j = 0; j < returnImage.getHeight(); j++) {
                if (edgeImage.getRGB(i, j) == -16777216) {
                    returnImage.setRGB(i, j, edgeImage.getRGB(i, j));
                }
                else {
                    returnImage.setRGB(i, j, returnImage.getRGB(i, j));
                }
            }
        }
        this.image = returnImage;
        this.repaint();
    }

    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private void renderData(Rectangle coords, int w, int h, ColorModel colorModel, WritableRaster writableRaster) {
        for (int x = coords.x; x < coords.width; x++) {
            for (int y = coords.y; y < coords.height; y++) {
                final Ray ray;
                final de.beuth.raytracer.color.Color color;
                // get the ray for the current position
                ray = this.camera.rayFor(w, h, x, y);

                // look if there is a hit
                Hit hit = this.world.hit(ray);

                //if hit is found, get color of geometry, else get default black background color
                if (hit != null) {
                    // color to draw the normals of the image
                    if (this.normalImage || this.edgeDetection) {
                        color = this.getNormalColor(ray);
                    } else if (this.celShading) {
                        color = this.getCelShadingColor(ray);
                    } else {
                        color = this.getColor(hit, ray);
                    }
                } else {
                    color = World.BACKGROUND_COLOR;
                }

                writableRaster.setDataElements(x, h - y - 1, colorModel.getDataElements(color.toRGB().getRGB(), null));
            }
        }
    }

    public de.beuth.raytracer.color.Color getColor(final Hit hit, final Ray ray) {
        //return Sampler.colorFor(world, hit, ray);
        return hit.geo.material.colorFor(hit, this.world, new Tracer(this.world, 6));
    }

    public de.beuth.raytracer.color.Color getNormalColor(final Ray r) {

        if (r == null) {
            throw new IllegalArgumentException("The ray cannot be null!");
        }
        final Hit hit = world.hit(r);

        if (hit != null) {
            double a = hit.geo.hashCode();
            if (a < 0) {
                a = a * -1;
            }
            while (a > 1) {
                a = a / 2;
            }

            if (hit.n.equals(new Normal3(1, 0, 0))) {
                return new Color(a, 1, 1);
            }
            if (hit.n.equals(new Normal3(0, 1, 0))) {
                return new Color(a, 0, a);

            }
            if (hit.n.equals(new Normal3(0, 0, 1))) {
                return new Color(1, a, a);

            }

            if (hit.n.equals(new Normal3(-1, 0, 0))) {
                return new Color(a, 1, 0);

            }
            if (hit.n.equals(new Normal3(0, -1, 0))) {
                return new Color(a, 1, a);

            }
            if (hit.n.equals(new Normal3(0, 0, -1))) {
                return new Color(a, 0, a);

            } else {

                return new Color(0, a, 1);

            }

        } else {
            return World.BACKGROUND_COLOR;
        }
    }

    public de.beuth.raytracer.color.Color getCelShadingColor(final Ray r) {
        final Hit hit = this.celShadingWorld.hit(r);
        if (hit != null) {
            return hit.geo.material.colorFor(hit, this.celShadingWorld, new Tracer(this.world, 6));
        } else {
            return World.BACKGROUND_COLOR;
        }

    }

}
