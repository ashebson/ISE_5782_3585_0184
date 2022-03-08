package unittests.primitives;

import static org.junit.Assert.*;
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
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows("crossProduct() for parallel vectors does not throw an exception",
                IllegalArgumentException.class, () -> v1.crossProduct(v3));
    }

    @Test
    public void testZeroVector(){
        // =============== Boundary Values Tests ==================
        assertThrows("zero vector does not throw an exception",
                IllegalArgumentException.class, () -> new Vector(0, 0, 0));
    }

    @Test
    public void testLengthSquared(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
		assertTrue("lengthSquared() wrong value", isZero(v1.lengthSquared() - 14));
    }

    @Test 
    public void testLength(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(0, 3, 4);
        assertTrue("length() wrong value", isZero(v1.length() - 5));
    }

    @Test
    public void testDotProduct(){
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // TC01: Test dot-product for orthogonal vectors
        assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // TC02: Test dot-product for non-orthogonal vectors
        assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
    }

    @Test
    public void testNormalize(){
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // TC01: Test normalization for non-zero vectors
        assertTrue("normalize() wrong value", isZero(v.normalize().length() - 1));
        
        // TC02: Test that the vectors are co-lined
		assertThrows("the normalized vector is not parallel to the original one",IllegalArgumentException.class,()->v.crossProduct(u));
		
        // TC03: Test that the normal is in the correct direction
		assertTrue("ERROR: the normalized vector is opposite to the original one",v.dotProduct(u) >= 0);
    }
}
