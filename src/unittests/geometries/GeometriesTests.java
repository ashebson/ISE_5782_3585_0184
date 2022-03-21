package unittests.geometries;
import primitives.*;
import geometries.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Geometries class
 */
public class GeometriesTests {
    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        // =================== Boundary Values Tests ==================
        // TC01: Geometries list is empty (0 points)
        Geometries geometries = new Geometries();
        Ray ray = new Ray(new Point(1,1,1), new Vector(1,1,1));
        assertNull(geometries.findIntersections(ray), "Wrong number of points");
        // TC02: No intersections (0 points)
        geometries = new Geometries(
            new Sphere(1, new Point(1, 0, 0)),
            new Tube(new Ray(new Point(0,3,0), new Vector(0,0,1)),1)
        );
        ray = new Ray(new Point(-1, 0, 0), new Vector(-3, 1, 0));
        assertNull(geometries.findIntersections(ray), "Wrong number of points");
        // TC03: One intersection (1 point)
        geometries = new Geometries(
            new Sphere(1, new Point(1, 0, 0)),
            new Tube(new Ray(new Point(0,3,0), new Vector(0,0,1)),1)
        );
        ray = new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0));
        assertEquals(geometries.findIntersections(ray),1);
        // TC04: All geometries intersect (6 points)
        geometries = new Geometries(
            new Sphere(1, new Point(1, 0, 0)),
            new Sphere(2, new Point(2, 2, -1)),
            new Tube(new Ray(new Point(5,2,-2), new Vector(0,0,4)),1)
        );
        ray = new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0));
        assertEquals(geometries.findIntersections(ray),6,"Wrong number of points");

        // ==================== Equivalence Partitions Tests ======================
        // TC11: some geometries intersect (2 points)
        geometries = new Geometries(
            new Sphere(1, new Point(1, 0, 0)),
            new Tube(new Ray(new Point(5,5,-2), new Vector(0,0,4)),1)
        );
        ray = new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0));
        assertEquals(geometries.findIntersections(ray),2,"Wrong number of points");
    }
}
