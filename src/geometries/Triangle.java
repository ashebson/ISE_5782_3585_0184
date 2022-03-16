package geometries;

import java.util.List;

import primitives.*;

/**
 * Triangle class
 * 
 * @author Aryeh and Zvi
 */
public class Triangle extends Polygon {
    /**
     * constructor with 3 points
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }
}
