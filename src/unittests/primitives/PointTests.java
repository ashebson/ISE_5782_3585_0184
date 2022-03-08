package unittests.primitives;

import static org.junit.Assert.*;

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
    public void testAdd(){
        // ============ Equivalence Partitions Tests ==============
        // Test addition of point and vector
		Point p1 = new Point(1, 2, 3);
		assertTrue("Point + Vector does not work correctly",p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0)));
		
    }
    
    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    public void testSubtract(){
        // ============ Equivalence Partitions Tests ==============
        // Test subtraction of points
        Point p1 = new Point(1, 2, 3);
        assertTrue("Point - Point does not work correctly",new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)));
    }
}
