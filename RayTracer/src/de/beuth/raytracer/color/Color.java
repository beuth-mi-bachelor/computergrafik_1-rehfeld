/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.color;

public class Color {

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

        if (r > 1 || r < 0) {
            if (r > 1) {
                this.r = 1.0;
            } else {
                this.r = 0.0;
            }
        } else {
            this.r = r;
        }

        if (g > 1 || g < 0) {
            if (g > 1) {
                this.g = 1.0;
            } else {
                this.g = 0.0;
            }
        } else {
            this.g = g;
        }

        if (b > 1 || b < 0) {
            if (b > 1) {
                this.b = 1.0;
            } else {
                this.b = 0.0;
            }
        } else {
            this.b = b;
        }

    }

    /**
     * Defines an additive color model from original java.awt.Color
     * @param color java.awt.Color
     */
    public Color (java.awt.Color color) {
        this.r = color.getRed() / 255.0;
        this.g = color.getGreen() / 255.0;
        this.b = color.getBlue() / 255.0;
    }

    /**
     * add the values of the primary components of two color models
     * @param c Color
     * @return a new color model
     */
    public Color add(final Color c) {
        return new Color(this.r + c.r, this.g + c.g, this.b + c.b);
    }

    /**
     * subtracts the values of the primary components of two colors
     * @param c Color
     * @return a new color model
     */
    public Color sub(final Color c) {
        return  new Color(this.r - c.r, this.g - c.g, this.b - c.b);
    }

    /**
     * multiplies the values of the primary components of two colors
     * @param c Color
     * @return a new color model
     */
    public Color mul(final Color c) {
        return new Color(this.r * c.r, this.g * c.g, this.b * c.b);
    }

    /**
     * multiplies the values of the primary components of a color with a double value
     * @param v double
     * @return a new color model
     */
    public Color mul(final double v) {
        return new Color(this.r * v, this.g * v, this.b * v);
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