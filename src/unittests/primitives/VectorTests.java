package unittests.primitives;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import primitives.*;
import static primitives.Util.*;

/**
 * Unit tests for primitives.Vector class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class VectorTests {
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001,"crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)),"crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)),"crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(
                IllegalArgumentException.class, () -> v1.crossProduct(v3),"crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test zero vector throws exception
     */
    @Test
    public void testZeroVector() {
        // =============== Boundary Values Tests ==================
        assertThrows(
                IllegalArgumentException.class, () -> new Vector(0, 0, 0),"zero vector does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    public void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        assertTrue(isZero(v1.lengthSquared() - 14),"lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    public void testLength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(0, 3, 4);
        assertTrue(isZero(v1.length() - 5),"length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // TC01: Test dot-product for orthogonal vectors
        assertTrue(isZero(v1.dotProduct(v3)),"dotProduct() for orthogonal vectors is not zero");

        // =============== Boundary Values Tests ==================
        // TC02: Test dot-product for non-orthogonal vectors
        assertTrue(isZero(v1.dotProduct(v2) + 28),"dotProduct() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    public void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // TC01: Test normalization for non-zero vectors
        assertTrue(isZero(v.normalize().length() - 1),"normalize() wrong value");

        // TC02: Test that the vectors are co-lined
        assertThrows(IllegalArgumentException.class,
                () -> v.crossProduct(u),"the normalized vector is not parallel to the original one");

        // TC03: Test that the normal is in the correct direction
        assertTrue(v.dotProduct(u) >= 0,"ERROR: the normalized vector is opposite to the original one");
    }

    @Test
    void testTurn() {
        Vector v1 = new Vector(1,0,0);
        Vector v2 = new Vector(0,1,0);
        Vector u = v1.turn(Math.PI/6,v2);
        Vector expected = new Vector(Math.sqrt(3)/2,1.0/2,0);
        assertEquals(u, expected, "Wrong Vector");

        v1 = new Vector(2,0,0);
        u = v1.turn(Math.PI/6,v2);
        expected = new Vector(Math.sqrt(3),1,0);
        assertEquals(u, expected, "Wrong Vector");

        v1 = new Vector(1,0,0);
        u = v1.turn(Math.PI,v2);
        expected = new Vector(-1,0,0);
        assertEquals(u, expected, "Wrong Vector");

        v1 = new Vector(1,0,0);
        u = v1.turn(Math.PI/2,v2);
        expected = new Vector(0,1,0);
        assertEquals(u, expected, "Wrong Vector");
    }
}
