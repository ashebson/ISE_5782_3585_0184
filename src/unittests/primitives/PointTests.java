package unittests.primitives;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import primitives.*;

/**
 * Unit tests for primitives.Point class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class PointTests {
    /**
     * Test method for {@link primitives.Point#add(primitives.Point)}.
     */
    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Add point and vector correctly
        Point p1 = new Point(1, 2, 3);
        Vector v1 = new Vector(-1, -2, -3);
        Point p2 = new Point(0, 0, 0);
        assertTrue(p1.add(v1).equals(p2), "Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct subtraction between point and vector
        Point p1 = new Point(1, 2, 3);
        Vector v1 = new Vector(1, 1, 1);
        Point p2 = new Point(2, 3, 4);
        assertTrue(v1.equals(p2.subtract(p1)), "Point - Point does not work correctly");
    }
}
