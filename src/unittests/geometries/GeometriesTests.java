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
            new Plane(new Point(3,0,0),new Vector(1,0,0)),
            new Triangle(new Point(4,0,0), new Point(4,0,2), new Point(4,2,0)),
            new Polygon(new Point(5,0,0),new Point(5,0,2),new Point(5,2,2), new Point(5,2,0))
        );
        ray = new Ray(new Point(-1, 0, 0), new Vector(-3, 1, 0));
        assertNull(geometries.findIntersections(ray), "Wrong number of points");
        // TC03: One intersection (1 point)
        ray = new Ray(new Point(1, 0, 0), new Vector(-3, 1, 0));
        assertEquals(geometries.findIntersections(ray).size(),1,"Wrong number of points");
        // TC04: All geometries intersect (5 points)
        ray = new Ray(new Point(-1, 0, 0), new Vector(10, 1, 2));
        assertEquals(geometries.findIntersections(ray).size(),5,"Wrong number of points");

        // ==================== Equivalence Partitions Tests ======================
        // TC11: some geometries intersect (4 points)
        ray = new Ray(new Point(-1, 0, 0), new Vector(3, 1, 1));
        assertEquals(geometries.findIntersections(ray).size(),3,"Wrong number of points");
    }
}
