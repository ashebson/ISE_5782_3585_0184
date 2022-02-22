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

    @Override
    public Vector getNormal(Point p) {
        // TODO Auto-generated method stub
        return null;
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
