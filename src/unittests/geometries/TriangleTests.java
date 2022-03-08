package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Triangle class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class TriangleTests {
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(0, 0, 0);
        Point p2 = new Point(1, 0, 0);
        Point p3 = new Point(0, 1, 0);
        Triangle t = new Triangle(p1, p2, p3);
        Vector n = t.getNormal(p1);
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        // TC01: Correct normal length
        assertEquals(n.length(), 1, "Wrong normal length");
        // TC02: Normal is orthogonal
        assertTrue(n.dotProduct(v1) == 0, "normal not orthogonal");
        assertTrue(n.dotProduct(v2) == 0, "normal not orthogonal");
    }
}
