/*
 * Copyright (c) 2014. by Angelina Staeck und Michael Duve
 */

package de.beuth.raytracer.testing;

import de.beuth.raytracer.mathlibrary.Mat3x3;
import de.beuth.raytracer.mathlibrary.Normal3;
import de.beuth.raytracer.mathlibrary.Point3;
import de.beuth.raytracer.mathlibrary.Vector3;

public class Exercise1Testing {

    public static void all() {
        Exercise1Testing.main(new String[] {});
    }

    public static void runExample1() {
        Exercise1Testing.main(new String[] {});
    }

    /**
     * main method for testing the math library
     * @param args array of arguments passed to main-method
     */
    public static void main(String[] args) {

        System.out.println("Exercise a");
        final Normal3 test_a = new Normal3(1, 2, 3);
        final Normal3 test_a_expected = new Normal3(0.5, 1, 1.5);
        final Normal3 erg_a = test_a.mul(0.5);
        System.out.println("Aufgabe a:" + test_a + " * 0.5 = " + erg_a);
        System.out.println("Erwartet:" + test_a_expected);
        System.out.println("Ergebnis a: " + erg_a.equals(test_a_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise b");
        final Normal3 test_b_var1 = new Normal3(1, 2, 3);
        final Normal3 test_b_var2 = new Normal3(3, 2, 1);
        final Normal3 test_b_expected = new Normal3(4, 4, 4);
        final Normal3 erg_b = test_b_var1.add(test_b_var2);
        System.out.println("Aufgabe b: " + test_b_var1 + " + " + test_b_var2 + " = " + erg_b);
        System.out.println("Erwartet:" + test_b_expected);
        System.out.println("Ergebnis b: " + erg_b.equals(test_b_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise c");
        final Normal3 test_c_n1 = new Normal3(1, 0, 0);
        final Normal3 test_c_n2 = new Normal3(0, 1, 0);
        final Vector3 test_c_v1 = new Vector3(1, 0, 0);
        final Vector3 test_c_v2 = new Vector3(0, 1, 0);

        final double erg_c_1_1 = test_c_n1.dot(test_c_v1);
        final double erg_c_1_2 = test_c_v1.dot(test_c_n1);
        final double erg_c_1_3 = test_c_v1.dot(test_c_v1);
        final double test_c_expected_1 = 1;

        System.out.println("Aufgabe c_1_1: " + test_c_n1 + " * " + test_c_v1 + " = " + erg_c_1_1);
        System.out.println("Erwartet:" + test_c_expected_1);
        System.out.println("Ergebnis c_1_1: " + (erg_c_1_1 == test_c_expected_1));

        System.out.println("Aufgabe c_1_2: " + test_c_v1 + " * " + test_c_n1 + " = " + erg_c_1_2);
        System.out.println("Erwartet:" + test_c_expected_1);
        System.out.println("Ergebnis c_1_2: " + (erg_c_1_2 == test_c_expected_1));

        System.out.println("Aufgabe c_1_3: " + test_c_v1 + " * " + test_c_v1 + " = " + erg_c_1_3);
        System.out.println("Erwartet:" + test_c_expected_1);
        System.out.println("Ergebnis c_1_3: " + (erg_c_1_3 == test_c_expected_1));

        final double erg_c_0_1 = test_c_n1.dot(test_c_v2);
        final double erg_c_0_2 = test_c_v1.dot(test_c_n2);
        final double erg_c_0_3 = test_c_v1.dot(test_c_v2);
        final double test_c_expected_0 = 0;

        System.out.println("Aufgabe c_0_1: " + test_c_n1 + " * " + test_c_v2 + " = " + erg_c_0_1);
        System.out.println("Erwartet:" + test_c_expected_0);
        System.out.println("Ergebnis c_0_1: " + (erg_c_0_1 == test_c_expected_0));

        System.out.println("Aufgabe c_0_2: " + test_c_v1 + " * " + test_c_n2 + " = " + erg_c_0_2);
        System.out.println("Erwartet:" + test_c_expected_0);
        System.out.println("Ergebnis c_0_2: " + (erg_c_0_2 == test_c_expected_0));

        System.out.println("Aufgabe c_0_2: " + test_c_v1 + " * " + test_c_v2 + " = " + erg_c_0_3);
        System.out.println("Erwartet:" + test_c_expected_0);
        System.out.println("Ergebnis c_0_3: " + (erg_c_0_3 == test_c_expected_0));
        System.out.println("----------------------------");

        System.out.println("Exercise d");
        final Point3 test_d_var1 = new Point3(1, 1, 1);
        final Point3 test_d_var2 = new Point3(2, 2, 0);
        final Vector3 test_d_expected = new Vector3(-1, -1, 1);
        final Vector3 erg_d = test_d_var1.sub(test_d_var2);
        System.out.println("Aufgabe d: " + test_d_var1 + " - " + test_d_var2 + " = " + erg_d);
        System.out.println("Erwartet:" + test_d_expected);
        System.out.println("Ergebnis d: " + erg_d.equals(test_d_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise e");
        final Point3 test_e_var1 = new Point3(1, 1, 1);
        final Vector3 test_e_var2 = new Vector3(4, 3, 2);
        final Point3 test_e_expected = new Point3(-3, -2, -1);
        final Point3 erg_e = test_e_var1.sub(test_e_var2);
        System.out.println("Aufgabe e: " + test_e_var1 + " - " + test_e_var2 + " = " + erg_e);
        System.out.println("Erwartet:" + test_e_expected);
        System.out.println("Ergebnis e: " + erg_e.equals(test_e_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise f");
        final Point3 test_f_var1 = new Point3(1, 1, 1);
        final Vector3 test_f_var2 = new Vector3(4, 3, 2);
        final Point3 test_f_expected = new Point3(5, 4, 3);
        final Point3 erg_f = test_f_var1.add(test_f_var2);
        System.out.println("Aufgabe f: " + test_f_var1 + " + " + test_f_var2 + " = " + erg_f);
        System.out.println("Erwartet:" + test_f_expected);
        System.out.println("Ergebnis f: " + erg_f.equals(test_f_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise g");
        final Vector3 test_g = new Vector3(1, 1, 1);
        final double test_g_expected = Math.sqrt(3);
        final double erg_g = test_g.magnitude;
        System.out.println("Aufgabe g: |" + test_g + "| = " + erg_g);
        System.out.println("Erwartet:" + test_g_expected);
        System.out.println("Ergebnis g: " + (erg_g == test_g_expected));
        System.out.println("----------------------------");


        System.out.println("Exercise h1");
        final Vector3 test_h1_var1 = new Vector3(1, 1, 1);
        final Normal3 test_h1_var2 = new Normal3(4, 3, 2);
        final Vector3 test_h1_expected = new Vector3(-3, -2, -1);
        final Vector3 erg_h1 = test_h1_var1.sub(test_h1_var2);
        System.out.println("Aufgabe h1: " + test_h1_var1 + " - " + test_h1_var2 + " = " + erg_h1);
        System.out.println("Erwartet:" + test_h1_expected);
        System.out.println("Ergebnis h1: " + erg_h1.equals(test_h1_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise h2");
        final Vector3 test_h2_var1 = new Vector3(1, 1, 1);
        final Normal3 test_h2_var2 = new Normal3(4, 3, 2);
        final Vector3 test_h2_expected = new Vector3(5, 4, 3);
        final Vector3 erg_h2 = test_h2_var1.add(test_h2_var2);
        System.out.println("Aufgabe h2: " + test_h2_var1 + " - " + test_h2_var2 + " = " + erg_h2);
        System.out.println("Erwartet:" + test_h2_expected);
        System.out.println("Ergebnis h2: " + erg_h2.equals(test_h2_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise h3");
        final Vector3 test_h3_var1 = new Vector3(1.0, 2.0, 3.0);
        final Double test_h3_var2 = 0.5;
        final Vector3 test_h3_expected = new Vector3(0.5, 1.0, 1.5);
        final Vector3 erg_h3 = test_h3_var1.mul(test_h3_var2);
        System.out.println("Aufgabe h3: " + test_h3_var1 + " - " + test_h3_var2 + " = " + erg_h3);
        System.out.println("Erwartet:" + test_h3_expected);
        System.out.println("Ergebnis h3: " + erg_h3.equals(test_h3_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise i");
        final Normal3 test_i_var1 = new Normal3(0, 1, 0);
        final Vector3 test_i_var2 = new Vector3(-0.707, 0.707, 0);
        final Vector3 test_i_expected = new Vector3(0.707, 0.707, 0);
        final Vector3 erg_i = test_i_var2.reflectedOn(test_i_var1);
        System.out.println("Aufgabe i: " + test_i_var2 + " reflektiert an " + test_i_var1 + " = " + erg_i);
        System.out.println("Erwartet:" + test_i_expected);
        System.out.println("Ergebnis i: " + erg_i.equals(test_i_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise j");
        final Normal3 test_j_var1 = new Normal3(1, 0, 0);
        final Vector3 test_j_var2 = new Vector3(0.707, 0.707, 0);
        final Vector3 test_j_expected = new Vector3(0.707, -0.707, 0);
        final Vector3 erg_j = test_j_var2.reflectedOn(test_j_var1);
        System.out.println("Aufgabe j: " + test_j_var2 + " reflektiert an " + test_j_var1 + " = " + erg_j);
        System.out.println("Erwartet:" + test_j_expected);
        System.out.println("Ergebnis j: " + erg_j.equals(test_j_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise k");
        final Mat3x3 test_k_var1 = new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1);

        final Vector3 test_k_var2 = new Vector3(3, 2, 1);
        final Vector3 test_k_expected = new Vector3(3, 2, 1);
        final Vector3 erg_k = test_k_var1.mul(test_k_var2);

        final Normal3 test_k_var2_norm = new Normal3(3, 2, 1);
        final Normal3 test_k_expected_norm = new Normal3(3, 2, 1);
        final Normal3 erg_k_norm = test_k_var1.mul(test_k_var2_norm);

        System.out.println("Aufgabe k Vektor: " + test_k_var1 + " * " + test_k_var2 + " = " + erg_k);
        System.out.println("Erwartet:" + test_k_expected);
        System.out.println("Ergebnis k Vektor: " + erg_k.equals(test_k_expected));
        System.out.println("Aufgabe k Normale: " + test_k_var1 + " * " + test_k_var2_norm + " = " + erg_k_norm);
        System.out.println("Erwartet:" + test_k_expected_norm);
        System.out.println("Ergebnis k Normale: " + erg_k_norm.equals(test_k_expected_norm));
        System.out.println("----------------------------");

        System.out.println("Exercise l");
        final Mat3x3 test_l_var1 = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Mat3x3 test_l_var2 = new Mat3x3(1, 0, 0, 0, 1, 0, 0, 0, 1);
        final Mat3x3 test_l_expected = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Mat3x3 erg_l = test_l_var1.mul(test_l_var2);
        System.out.println("Aufgabe l: " + test_l_var1 + " * " + test_l_var2 + " = " + erg_l);
        System.out.println("Erwartet:" + test_l_expected);
        System.out.println("Ergebnis l: " + erg_l.equals(test_l_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise m");
        final Mat3x3 test_m_var1 = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Mat3x3 test_m_var2 = new Mat3x3(0, 0, 1, 0, 1, 0, 1, 0, 0);
        final Mat3x3 test_m_expected = new Mat3x3(3, 2, 1, 6, 5, 4, 9, 8, 7);
        final Mat3x3 erg_m = test_m_var1.mul(test_m_var2);
        System.out.println("Aufgabe m: " + test_m_var1 + " * " + test_m_var2 + " = " + erg_m);
        System.out.println("Erwartet:" + test_m_expected);
        System.out.println("Ergebnis m: " + erg_m.equals(test_m_expected));
        System.out.println("----------------------------");

        System.out.println("Exercise n");
        final Mat3x3 test_n_var = new Mat3x3(1, 2, 3, 4, 5, 6, 7, 8, 9);
        final Mat3x3 test_n_expected_col1 = new Mat3x3(8, 2, 3, 8, 5, 6, 8, 8, 9);
        final Mat3x3 test_n_expected_col2 = new Mat3x3(1, 8, 3, 4, 8, 6, 7, 8, 9);
        final Mat3x3 test_n_expected_col3 = new Mat3x3(1, 2, 8, 4, 5, 8, 7, 8, 8);
        final Vector3 test_n_changer = new Vector3(8,8,8);
        final Mat3x3 erg_n_col1 = test_n_var.changeCol1(test_n_changer);
        final Mat3x3 erg_n_col2 = test_n_var.changeCol2(test_n_changer);
        final Mat3x3 erg_n_col3 = test_n_var.changeCol3(test_n_changer);

        System.out.println("Aufgabe n col1: " + test_n_var + " changed col1 with " + test_n_changer + " = " + erg_n_col1);
        System.out.println("Erwartet:" + test_n_expected_col1);
        System.out.println("Ergebnis n col1: " + erg_n_col1.equals(test_n_expected_col1));
        System.out.println("Aufgabe n col2: " + test_n_var + " changed col2 with " + test_n_changer + " = " + erg_n_col2);
        System.out.println("Erwartet:" + test_n_expected_col2);
        System.out.println("Ergebnis n col2: " + erg_n_col2.equals(test_n_expected_col2));
        System.out.println("Aufgabe n col1: " + test_n_var + " changed col3 with " + test_n_changer + " = " + erg_n_col3);
        System.out.println("Erwartet:" + test_n_expected_col3);
        System.out.println("Ergebnis n col1: " + erg_n_col3.equals(test_n_expected_col3));
        System.out.println("----------------------------");

    }

}
