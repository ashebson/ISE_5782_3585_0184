package geometries;

import primitives.*;

public class Plane implements Geometry {
    private Point q0;
    private Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point q0, Point p1, Point p2) {
        this.q0 = q0;
        this.normal = p1.subtract(q0).crossProduct(p2.subtract(q0)).normalize();
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    Vector getNormal(){
        return getNormal(q0);
    }

}
