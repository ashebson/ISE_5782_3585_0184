package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Triangle class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class TriangleTests {
    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Triangle t = new Triangle(p1, p2, p3);
        Vector n = t.getNormal(p1);
        // TC01: Correct normal
        assertEquals(new Vector(0,0,1), n, "Wrong normal");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects triangle (1 point)
        Triangle t = new Triangle(new Point(2,0,0), new Point(0,3,0), new Point(0,0,1));
        Ray r = new Ray(new Point(1,0,0), new Vector(1,1,1));
        List<Point> result = t.findIntersections(r);
        List<Point> expected = List.of(new Point(1.2727272727272727,0.2727272727272727,0.2727272727272727));
        assertEquals(result, expected, "Wrong intersections");
        // TC02: Ray does not intersect triangle (open) (0 points)
        r = new Ray(new Point(1,0,0), new Vector(1,-1,1));
        result = t.findIntersections(r);
        assertNull(result, "Wrong intersections");
        // TC03: Ray does not intersect triangle (open) (0 points)
        r = new Ray(new Point(-1,-1,0), new Vector(0,0,1));
        result = t.findIntersections(r);
        assertNull(result, "Wrong intersections");
        // =============== Boundary Values Tests ==================
        // TC11: Ray intersects triangle on the vertex (0 point)
        r = new Ray(new Point(-1,-1,0), new Vector(1,1,1));
        result = t.findIntersections(r);
        assertNull(result, "Wrong intersections");
        // TC12: Ray intersects triangle on the edge (0 point)
        r = new Ray(new Point(-1,-1,0), new Vector(4,2,1));
        result = t.findIntersections(r);
        assertNull(result, "Wrong intersections");
        // TC13: Ray doesn't intersect the triangle but intersects the line of the edge of the triangle (0 point)
        r = new Ray(new Point(-1,-1,0), new Vector(0,2,3));
        result = t.findIntersections(r);
        assertNull(result, "Wrong intersections");
    }
}
