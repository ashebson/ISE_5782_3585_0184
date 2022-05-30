package unittests.primitives;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import primitives.*;

public class BoxTests {
    @Test
    void testIntersects() {
        Ray ray = new Ray(new Point(-1,-1,1), new Vector(1,1,1));
        Box box = new Box(new Point(1,2,1), new Point(5,6,5));
        assertTrue(box.intersects(ray));
        ray = new Ray(new Point(-1,-1,1), new Vector(-1,-1,-1));
        assertFalse(box.intersects(ray));
    }
}
