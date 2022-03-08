package unittests.geometries;

import primitives.*;
import geometries.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

/**
 * Unit tests for geometries.Plane class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class PlaneTests {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Plane p = new Plane(p1, p2, p3);
        Vector n = p.getNormal(p1);
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        // TC01: Correct normal length
        assertEquals(n.length(), 1, "Wrong normal length");
        // TC02: Normal is orthogonal
        assertTrue(n.dotProduct(v1) == 0, "normal not orthogonal");
        assertTrue(n.dotProduct(v2) == 0, "normal not orthogonal");
        // =============== Boundary Values Tests ==================
        // TC11: Throws when two points are equal
        assertThrows("Doesn't throw when two points are equal", IllegalArgumentException.class,
                () -> new Plane(p1, p1, p2));
        // TC12: Throws when all points are on the same line
        assertThrows("Doesn't throw when all points are on the same line", IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)));
    }
}
