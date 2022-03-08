package unittests.geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Tube class
 * 
 * @author Aryeh Shebson & Zvi Korach
 */
public class TubeTests {
    @Test
    public void testGetNormal() {
        // =============== Boundary Values Tests ==================
        double rd = 1;
        Point p0 = new Point(0, 0, 0);
        Vector v = new Vector(1, 0, 0);
        Ray r = new Ray(p0,v);
        Tube t = new Tube(r, rd);
        Point p = new Point(0, 1, 0);
        Vector n = t.getNormal(p);
        // TC01: Correct normal when orthogonal
        assertEquals(n,new Vector(0,1,0), "Wrong normal");
        // ============ Equivalence Partitions Tests ==============
        p = new Point(5, 1, 0);
        t = new Tube(r, rd);
        n = t.getNormal(p);
        // TC11: Correct normal
        assertEquals(n,new Vector(0,1,0), "Wrong normal");
    }
}
