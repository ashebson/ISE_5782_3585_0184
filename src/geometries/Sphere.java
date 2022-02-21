package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
    Point center;
    double radius;
    @Override
    public Vector getNormal(Point p) {
        // TODO Auto-generated method stub
        return null;
    }
    public Point getCenter() {
        return center;
    }
    public double getRadius() {
        return radius;
    }
    
}
