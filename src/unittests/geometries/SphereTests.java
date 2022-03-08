package unittests.geometries;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Sphere class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class SphereTests {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point o = new Point(0,0,0);
        Point p = new Point(1,0,0);
        double r = 1;
        Sphere s = new Sphere(o, r);
        Vector n = s.getNormal(p);
        // TC01: Correct normal length
        assertEquals(n.length(), 1, "Wrong normal length");
        Vector po = p.subtract(o);
        // TC02: Normal is outward
        assertTrue(n.dotProduct(po) >= 0, "Normal not outward");
        // TC03: Normal is orthogonal
        assertThrows("Normal not orthogonal",IllegalArgumentException.class, ()->n.crossProduct(po));
    }
}
