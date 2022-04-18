package unittests.primitives;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import primitives.*;

public class RayTests {
    /**
     * Test method for {@link primitives.Ray#findClosestPoint(primitives.Point)}.
     */
    @Test
    void testFindClosestPoint() {
        List<Point> points = List.of(new Point(2, 2, 2) ,new Point(1, 1, 1), new Point(3, 3, 3));
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test when closest point is in the middle of the list
        assertEquals(ray.findClosestPoint(points), new Point(1, 1, 1), "wrong result");
        // =============== Boundary Values Tests ==================
        // TC11: Test when closest point is the first element of the list
        points = List.of(new Point(1, 1, 1), new Point(2, 2, 2), new Point(3, 3, 3));
        assertEquals(ray.findClosestPoint(points), new Point(1, 1, 1), "wrong result");
        // TC12: Test when closest point is the last element of the list
        points = List.of(new Point(3, 3, 3), new Point(2, 2, 2), new Point(1, 1, 1));
        assertEquals(ray.findClosestPoint(points), new Point(1, 1, 1), "wrong result");
        // TC13: Test when points is empty
        points = new ArrayList<>();
        assertNull(ray.findClosestPoint(points), "wrong result");
    }
}
