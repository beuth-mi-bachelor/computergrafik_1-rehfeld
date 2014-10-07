/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.color;

import de.beuth.raytracer.color.interfaces.IColor;

public class Color implements IColor{

    /**
     * value of the red color.
     */
    public final double r;
    /**
     * value of the green color.
     */
    public final double g;
    /**
     * value of the blue color.
     */
    public final double b;

    /**
     * Defines an additive color model with the three primary colors red, green and blue.
     * @param r double
     * @param g double
     * @param b double
     */
    public Color (final double r, final double g, final double b) {

        if (r >= 0 && r <= 1 && g >= 0 && g <= 1 && b >= 0 && b <= 1) {
            this.r = r;
            this.g = g;
            this.b = b;
        } else {
            throw new IllegalArgumentException("please insert only values between 0 and 1.");
        }

    }

    /**
     * add the values of the primary components of two color models
     * @param c Color
     * @return a new color model
     */
    @Override
    public Color add(Color c) {

        if (c.r >= 0 && c.r <= 1 && c.g >= 0 && c.g <= 1 && c.b >= 0 && c.b <= 1) {
            double newr = r + c.r;
            double newg = g + c.g;
            double newb = b + c.b;
            return new Color(newr, newg, newb);
        }
            throw new IllegalArgumentException("Bitte nur Werte zwischen 0 und 1 eingeben.");


    }

    /**
     * subtracts the values of the primary components of two colors
     * @param c Color
     * @return a new color model
     */
    @Override
    public Color sub(Color c) {

        if (c.r >= 0 && c.r <= 1 && c.g >= 0 && c.g <= 1 && c.b >= 0 && c.b <= 1) {
            double newr = r - c.r;
            double newg = g - c.g;
            double newb = b - c.b;
            return new Color(newr, newg, newb);
        }
            throw new IllegalArgumentException("Bitte nur Werte zwischen 0 und 1 eingeben.");
    }

    /**
     * multiplies the values of the primary components of two colors
     * @param c Color
     * @return a new color model
     */
    @Override
    public Color mul(Color c) {

        if (c.r >= 0 && c.r <= 1 && c.g >= 0 && c.g <= 1 && c.b >= 0 && c.b <= 1) {
            double newr = r * c.r;
            double newg = g * c.g;
            double newb = b * c.b;
            return new Color(newr, newg, newb);
        }
            throw new IllegalArgumentException("Bitte nur Werte zwischen 0 und 1 eingeben.");
    }

    /**
     * multiplies the values of the primary components of a color with a double value
     * @param v double
     * @return a new color model
     */
    @Override
    public Color mul(double v) {

        double newr = r * v;
        double newg = g * v;
        double newb = b * v;
        return new Color(newr, newg, newb);
    }

    /**
     * converts color to standard Color class
     * @return Color converted from this color
     */
    public java.awt.Color toRGB() {
        final int colorR = (int) (255 * this.r);
        final int colorG = (int) (255 * this.g);
        final int colorB = (int) (255 * this.b);
        return new java.awt.Color(colorR, colorG, colorB);
    }

    @Override
    public String toString() {
        return "Color{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Color color = (Color) o;

        if (Double.compare(color.b, b) != 0) return false;
        if (Double.compare(color.g, g) != 0) return false;
        if (Double.compare(color.r, r) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(r);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(g);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static void main(String[] args) {

        Color a = new Color(1.0, 0.1, 0.5);
        Color e = a.mul(1.0);
        System.out.println(e);

    }

}