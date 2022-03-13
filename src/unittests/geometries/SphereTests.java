package unittests.geometries;

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
    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point o = new Point(0,0,0);
        Point p = new Point(1,0,0);
        double r = 1;
        Sphere s = new Sphere(o, r);
        Vector n = s.getNormal(p);
        // TC01: Correct normal
        assertEquals(new Vector(1,0,0), n, "Wrong normal");
    }
}
