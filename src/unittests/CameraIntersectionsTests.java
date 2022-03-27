package unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;

import primitives.*;
import geometries.*;
import renderer.*;

/**
 * Integration Tests for Camera
 */
public class CameraIntersectionsTests {

    /**
     * checks if camera with 3x3 view plane has the correct number of intersections
     * with geometry
     * 
     * @param c
     * @param g
     * @param expected
     */
    private void assertCameraIntersectionCount(Camera c, Geometry g, int expected) {
        Ray r;
        int intersectionCount = 0;
        List<Point> result;
        int nX = 3, nY = 3;
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                r = c.constructRay(nX, nY, j, i);
                result = g.findIntersections(r);
                if (result != null)
                    intersectionCount += result.size();
            }
        }
        assertEquals(intersectionCount, expected);
    }

    /**
     * Integration test for camera sphere intersections
     */
    @Test
    public void CameraSphereIntersectionsTests() {
        // TC01: Camera behind sphere (2 points)
        Sphere s = new Sphere(1, new Point(0, 0, -3));
        Camera c = new Camera(new Point(0, 0, 0), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setWidthAndHeight(3, 3);
        assertCameraIntersectionCount(c, s, 2);
        // TC02: View plane in sphere, big sphere (18 points)
        s = new Sphere(2.5, new Point(0, 0, -2.5));
        c = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setWidthAndHeight(3, 3);
        assertCameraIntersectionCount(c, s, 18);
        // TC03: View plane in sphere, small sphere (10 points)
        s = new Sphere(2, new Point(0, 0, -2));
        c = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setWidthAndHeight(3, 3);
        assertCameraIntersectionCount(c, s, 10);
        // TC04: View plane and camera in sphere (9 points)
        s = new Sphere(4, new Point(0, 0, -0.5));
        c = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setWidthAndHeight(3, 3);
        assertCameraIntersectionCount(c, s, 9);
        // TC05: Camera infront of sphere (0 points)
        s = new Sphere(0.5, new Point(0, 0, 1));
        c = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setWidthAndHeight(3, 3);
        assertCameraIntersectionCount(c, s, 0);
    }

    /**
     * Integration test for camera plane intersections
     */
    @Test
    public void CameraPlaneIntersectionsTests() {
        // TC01: parallel plane (9 points)
        Plane p = new Plane(new Point(0, 0, -4), new Vector(0, 0, -1));
        Camera c = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setWidthAndHeight(3, 3);
        assertCameraIntersectionCount(c, p, 9);
        // TC02: plane with slight angle (9 points)
        p = new Plane(new Point(0, 0, -4), new Vector(0, -0.5, -1));
        assertCameraIntersectionCount(c, p, 9);
        // TC03: plane with large angle (6 points)
        p = new Plane(new Point(0, 0, -4), new Vector(0, -2, -1));
        assertCameraIntersectionCount(c, p, 6);
    }

    /**
     * Integration test for camera triangle intersections
     */
    @Test
    public void CameraTriangleIntersectionsTests() {
        // TC01: small triangle (1 point)
        Triangle t = new Triangle(new Point(0, 1, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        Camera c = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setWidthAndHeight(3, 3);
        assertCameraIntersectionCount(c, t, 1);
        // TC02: tall triangle (2 points)
        t = new Triangle(new Point(0, 20, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        assertCameraIntersectionCount(c, t, 2);
    }
}
