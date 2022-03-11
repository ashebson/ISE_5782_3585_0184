package unittests.geometries;

import primitives.*;
import geometries.*;

import static org.junit.jupiter.api.Assertions.*;
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
}
