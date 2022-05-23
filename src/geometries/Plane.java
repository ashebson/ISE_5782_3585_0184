package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

/**
 * Plane class
 * 
 * @author Aryeh and Zvi
 */
public class Plane extends Geometry {
    private Point q0;
    private Vector normal;

    /**
     * constructor with a point and a normal
     *
     * @param q0
     * @param normal
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * constructor with 3 points
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Plane(Point q0, Point p1, Point p2) {
        this.q0 = q0;
        this.normal = p1.subtract(q0).crossProduct(p2.subtract(q0)).normalize();
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     * returns the normal
     *
     * @return normal
     */
    Vector getNormal() {
        return getNormal(q0);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        double devider = normal.dotProduct(v);
        if (Util.isZero(devider) || p0.equals(q0)) {
            return null;
        }
        double t = normal.dotProduct(q0.subtract(p0)) / devider;
        t = Util.alignZero(t);
        if (t <= 0)
            return null;
        GeoPoint intersection = new GeoPoint(this, ray.getPoint(t));
        double distance = ray.getP0().Distance(intersection.point);
        if (maxDistance == Double.POSITIVE_INFINITY) {
            return List.of(intersection);
        }
        if (Util.alignZero(distance - maxDistance) > 0) {
            return null;
        }
        return List.of(intersection);
    }

    /**
     * retruns two normalized orthogonal vectors in the plane
     * 
     * @return
     */
    public List<Vector> getBaseVectors() {
        Vector v1;
        try {
            v1 = normal.crossProduct(new Vector(1, 0, 0));
        } catch (IllegalArgumentException e) {
            v1 = normal.crossProduct(new Vector(0, 1, 0));
        }
        Vector v2 = normal.crossProduct(v1);
        return List.of(v1.normalize(), v2.normalize());
    }
}
