package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Cylinder class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class CylinderTests {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal(){
        // ============ Equivalence Partitions Tests ==============
        double rd = 1;
        Point p0 = new Point(0, 0, 0);
        Vector v = new Vector(1, 0, 0);
        double h = 3;
        Ray r = new Ray(p0,v);
        Cylinder c = new Cylinder(r, rd, h);
        // TC01: Check normal when on round part
        Point p = new Point(1, 1, 0);
        Vector n = c.getNormal(p);
        assertEquals(n,new Vector(0,1,0), "Wrong normal when on round part");
        // TC02: Check normal when on first base
        p = new Point(0, 0.5, 0);
        n = c.getNormal(p);
        assertEquals(n,new Vector(-1,0,0), "Wrong normal when on first base");
        // TC03: Check normal when on second base
        p = new Point(3, 0.5, 0);
        n = c.getNormal(p);
        assertEquals(n, new Vector(1,0,0), "Wrong normal when on second base");
        // =============== Boundary Values Tests ==================
        // TC11: Check normal when in center of first base
        p = new Point(0, 0, 0);
        n = c.getNormal(p);
        assertEquals(n,new Vector(-1,0,0), "Wrong normal when in center of first base");
        // TC12: Check normal when in center of second base
        p = new Point(3, 0, 0);
        n = c.getNormal(p);
        assertEquals(n,new Vector(1,0,0), "Wrong normal when in center of second base");

    }
}
