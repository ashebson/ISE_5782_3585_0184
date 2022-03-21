package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Tube class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class TubeTests {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // =============== Boundary Values Tests ==================
        double rd = 1;
        Point p0 = new Point(0, 0, 0);
        Vector v = new Vector(1, 0, 0);
        Ray r = new Ray(p0,v);
        Tube t = new Tube(r, rd);
        Point p = new Point(0, 1, 0);
        Vector n = t.getNormal(p);
        // TC01: Correct normal when orthogonal
        assertEquals(n,new Vector(0,1,0), "Wrong normal");
        // ============ Equivalence Partitions Tests ==============
        p = new Point(5, 1, 0);
        t = new Tube(r, rd);
        n = t.getNormal(p);
        // TC11: Correct normal
        assertEquals(n,new Vector(0,1,0), "Wrong normal");
    }

    /**
     * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ==================== Boundry Values Tests ======================
        // TC01: Ray starts from the tube and goes out (0 points)
        Tube t = new Tube(new Ray(new Point(1,1,0), new Vector(0,0,1)), 1);
        Ray r = new Ray(new Point(0,1,1), new Vector(-1,1,1));
        assertNull(t.findIntersections(r), "Wrong number of points");
        // TC02: Ray starts from the tube and goes in (1 points)
        r = new Ray(new Point(0,1,1), new Vector(1,1,1));
        assertNull(t.findIntersections(r), "Wrong number of points");
        // TC03: Ray is contained in the tube (0 points)
        r = new Ray(new Point(0,1,1), new Vector(0,0,1));
        assertNull(t.findIntersections(r), "Wrong number of points");
        // TC04: Ray is equal the ray in the cylinder (1 points)
        r = new Ray(new Point(1,1,0), new Vector(0,0,1));
        assertEquals(t.findIntersections(r), 1, "Wrong number of points");
        // TC05: Ray is tangent to the tube (0 points)
        r = new Ray(new Point(-1,0,0), new Vector(1,0,1));
        assertNull(t.findIntersections(r), "Wrong number of points");
        // ==================== Equivelence Partitions Tests ===============
        // TC11: Ray is parallel to the tube (0 points)
        r = new Ray(new Point(0,2,2), new Vector(0,0,1));
        assertNull(t.findIntersections(r), "Wrong number of points");
        // TC12: Ray is orthogonal to the tube (1 point)
        r = new Ray(new Point(1,3,2), new Vector(0,-1,0));
        assertEquals(t.findIntersections(r).size(), 1, "Wrong number of points");

    }
}
