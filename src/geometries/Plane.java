package geometries;

import primitives.*;

/**
 * Plane class
 * 
 * @author Aryeh and Zvi
 */
public class Plane implements Geometry {
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
        //this.normal = p1.subtract(q0).crossProduct(p2.subtract(q0)).normalize();
        this.normal = null;
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    /**
     * retruns the normal
     *
     * @return normal
     */
    Vector getNormal(){
        return getNormal(q0);
    }

}
