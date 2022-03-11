package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;
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
}
