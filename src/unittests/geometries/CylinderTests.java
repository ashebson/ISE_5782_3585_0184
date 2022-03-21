package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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

    /**
     * Test method for {@link geometries.Cylinder#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ==================== Boundry Values Tests ======================
        // TC01: Ray starts from the edge and goes out (0 points)
        Cylinder c = new Cylinder(new Ray(new Point(1,1,0), new Vector(0,0,1)), 1, 3);
        Ray r = new Ray(new Point(1,0,3), new Vector(1,1,1));
        assertNull(c.findIntersections(r),"Wrong number of points");
        // TC02: Ray starts from the edge and goes in (1 points)
        r = new Ray(new Point(1,0,3), new Vector(1,1,-1));
        assertEquals(c.findIntersections(r).size(),1,"Wrong number of points");
        // TC03: Ray starts from the base and goes out (0 points)
        c = new Cylinder(new Ray(new Point(1,1,0), new Vector(0,0,1)), 2, 3);
        r = new Ray(new Point(1,0,3), new Vector(1,1,1));
        assertNull(c.findIntersections(r),"Wrong number of points");
        // TC04: Ray starts from the base and goes in (1 points)
        r = new Ray(new Point(1,0,3), new Vector(1,1,-1));
        assertEquals(c.findIntersections(r).size(),1,"Wrong number of points");
        // TC05: Ray starts from the center and goes in (1 points)
        c = new Cylinder(new Ray(new Point(1,1,0), new Vector(0,0,1)), 1, 3);
        r = new Ray(new Point(1,1,0), new Vector(1,1,1));
        assertEquals(c.findIntersections(r).size(),1,"Wrong number of points");
        // TC06: Ray starts from the center and goes out (0 points)
        r = new Ray(new Point(1,1,0), new Vector(1,1,-1));
        assertNull(c.findIntersections(r),"Wrong number of points");
    }
}
