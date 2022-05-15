package unittests.geometries;

import primitives.*;
import geometries.*;
import geometries.Intersectable.GeoPoint;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for geometries.Plane class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class PlaneTests {
    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Plane p = new Plane(p1, p2, p3);
        Vector n = p.getNormal(p1);
        // TC01: Correct normal
        assertEquals(new Vector(0,0,1), n, "Wrong normal");
        // =============== Boundary Values Tests ==================
        // TC11: Throws when two points are equal
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(p1, p1, p2),"Doesn't throw when two points are equal");
        // TC12: Throws when all points are on the same line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)), "Doesn't throw when all points are on the same line");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ==================== Equivalence Partitions Tests ======================
        Point p = new Point(0,1,0);
        Vector v = new Vector(1,1,1);
        Plane pl = new Plane(p, v);
        // TC01: Ray intersectes the plane (1 points)
        Ray r = new Ray(new Point(3,0,0), new Vector(-1,-5,-1));
        List<Point> result = pl.findIntersections(r);
        Point p1 = new Point(2.7142857142857144,-1.4285714285714288,-0.28571428571428575);
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(result,List.of(p1),"Wrong points");
        // TC02: Ray doesn't intersect with the plane(0 points)
        r = new Ray(new Point(3,0,0), new Vector(1,5,1));
        result = pl.findIntersections(r);
        assertEquals(result, null, "Wrong number of points");
        // ==================== Boundary Values Tests ======================
        // ** Group: Ray's line is parallel to the plane
        // TC11: Ray is included in the plane (0 points)
        r = new Ray(new Point(1,0,0), new Vector(-1,1,0));
        result = pl.findIntersections(r);
        assertEquals(result, null, "Wrong number of points");
        // TC12: Ray isn't included in the plane (0 points)
        r = new Ray(new Point(2,0,0), new Vector(-1,1,0));
        result = pl.findIntersections(r);
        assertEquals(result, null, "Wrong number of points");

        // ** Group: Ray's line is orthogonal to the plane
        // TC21: Ray's head is included in the plane (0 points)
        r = new Ray(new Point(1,0,0), new Vector(1,1,1));
        result = pl.findIntersections(r);
        assertEquals(result, null, "Wrong number of points");
        // TC22: Ray's head is before the plane (1 points)
        r = new Ray(new Point(0,0,-1), new Vector(1,1,1));
        result = pl.findIntersections(r);
        p1 = new Point(0.6666666666666667,0.6666666666666667,-0.33333333333333326);
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(result,List.of(p1));
        // TC23: Ray's head is after the plane (0 points)
        r = new Ray(new Point(0,0,2), new Vector(1,1,1));
        result = pl.findIntersections(r);
        assertEquals(result, null, "Wrong number of points");

        // ** Group: Ray's line isn't orthogonal not parallel to the plane
        // TC31: Ray's head starts at the plane (0 points)
        r = new Ray(new Point(0,0,1), new Vector(2,1,1));
        result = pl.findIntersections(r);
        assertEquals(result, null, "Wrong number of points");
        // TC32: Ray's head starts at the plane's point (0 points)
        r = new Ray(new Point(0,1,0), new Vector(2,1,1));
        result = pl.findIntersections(r);
        assertEquals(result, null, "Wrong number of points");
    }

    @Test
    void testFindGeoIntersections() {
        // ==================== Equivalence Partitions Tests ======================
        Point p = new Point(0,1,0);
        Vector v = new Vector(1,1,1);
        Plane pl = new Plane(p, v);
        Ray r = new Ray(new Point(3,0,0), new Vector(-1,-5,-1));
        double distance = 1.484615;
        // TC01: Ray intersectes the plane, within distance (1 points)
        List<GeoPoint> result = pl.findGeoIntersections(r,distance*1.1);
        assertEquals(result.size(), 1, "Wrong number of points");
        // TC02: Ray intersectes the plane, outside distance (0 points)
        result = pl.findGeoIntersections(r,distance*0.9);
        assertEquals(result, null, "Wrong number of points");
        // ==================== Boundary Values Tests ======================
        // TC11: Ray intersects the plane, with exact distance (1 points)
        result = pl.findGeoIntersections(r,distance);
        assertEquals(result.size(), 1, "Wrong number of points");
    }
}
