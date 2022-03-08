package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
    Point center;
    double radius;
    /**
     * 
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).scale(1/radius);
    }
    public Sphere (Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }
    public Point getCenter() {
        return center;
    }
    public double getRadius() {
        return radius;
    }
    
}
