package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class
 * 
 * @author Aryeh and Zvi
 */
public class Sphere implements Geometry {
    Point center;
    double radius;
    
    /** 
     * returns normal at point p
     * @param p
     * @return Vector
     */
    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).scale(1/radius);
    }
    public Sphere (Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter for the center point
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter for the radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }
}
