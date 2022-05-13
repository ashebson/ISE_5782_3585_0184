package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
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
        Sphere s = new Sphere(o,r);
        Vector n = s.getNormal(p);
        // TC01: Correct normal
        assertEquals(new Vector(1,0,0), n, "Wrong normal");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0),1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                   "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
            
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(0.5,0,0),new Vector(1,1,1)));
        p1 = new Point(1.1937129433613967,0.6937129433613968,0.6937129433613968);
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(List.of(p1), result, "Wrong intersection points");
        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point(3,0,0),new Vector(1,1,1)));
        assertEquals(result, null, "Wrong number of points");
        // =============== Boundary Values Tests ==================

        // ** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(2,0,0),new Vector(-1,0,-1)));
        p1 = new Point(1,0,-1);
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(List.of(p1), result, "Wrong intersection points");
        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point(2,0,0),new Vector(1,0,1)));
        assertEquals(result, null, "Wrong number of points");
        // ** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(0,-1,0),new Vector(1,1,1)));
        p1 = new Point(1,0,1);
        p2 = new Point(0.3333333333333333,-0.6666666666666667,0.3333333333333333);
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(result.size(), 2, "Wrong number of points");
        assertEquals(result, List.of(p2, p1), "Wrong intersection points");
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1,1,0), new Vector(0,-1,0)));
        p1 = new Point(1,-1,0);
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(result,List.of(p1), "Wrong intersection points");
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1,0.5,0), new Vector(0,-1,0)));
        p1 = new Point(1,-1,0);
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(result,List.of(p1), "Wrong intersection points");
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point(1,0,0), new Vector(1,1,1)));
        p1 = new Point(1.5773502691896257,0.5773502691896258,0.5773502691896258);
        assertEquals(result.size(), 1, "Wrong number of points");
        assertEquals(result,List.of(p1), "Wrong intersection points");
        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point(1,-1,0), new Vector(0,-1,0)));
        assertEquals(result, null, "Wrong number of points");
        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point(1,-2,0), new Vector(0,-1,0)));
        // ** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point(-1,-1,0), new Vector(1,0,0)));
        assertEquals(result, null, "Wrong number of points");
        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point(1,-1,0),  new Vector(1,0,0)));
        assertEquals(result, null, "Wrong number of points");
        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point(2,-1,0),  new Vector(1,0,0)));

        // ** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line (0 points)
        result = sphere.findIntersections(new Ray(new Point(3,0,0), new Vector(0,1,0)));
        assertEquals(result, null, "Wrong number of points");
    }

    @Test
    public void testFindGeoIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0),1d);
        Ray ray = new Ray(new Point(-1, 0, 0),new Vector(3, 1, 0));
        List<GeoPoint> result = sphere.findGeoIntersections(ray);
        double distance1 = 2.6719632653425105;
        double distance2 = 1.1227699268595444;
        // =========================== Equivalence Partitions ==========================
        // TC01: Ray intersectes the sphere twice, both within distance (2 points)
        result = sphere.findGeoIntersections(ray, distance1*1.1);
        assertEquals(result.size(), 2, "Wrong number of points");
        // TC02: Ray intersectes the sphere twice, with one in distance (1 points)
        result = sphere.findGeoIntersections(ray, (distance1+distance2)/2);
        assertEquals(result.size(), 1, "Wrong number of points");
        // TC03: Ray intersectes the sphere twice, both out of distance (0 points)
        result = sphere.findGeoIntersections(ray, distance2*0.9);
        assertEquals(result, null, "Wrong number of points");
        // =========================== Boundary Values Tests ==========================
        // TC11: Ray intersectes the sphere twice, one within distance and one on exact distance (2 points)
        result = sphere.findGeoIntersections(ray, distance1);
        assertEquals(result.size(), 2, "Wrong number of points");
        // TC12: Ray intersectes the sphere twice, one out of distance and one on exact distance (1 points)
        result = sphere.findGeoIntersections(ray, distance2);
        assertEquals(result.size(), 1, "Wrong number of points");
    }
}
