package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

/**
 * Tube class
 * 
 * @author Aryeh and Zvi
 */
public class Tube extends Geometry {
    Ray axisRay;
    double radius;

    /**
     * constructor based on Ray and radius
     */
    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    @Override
    public Vector getNormal(Point p) {
        Vector v = axisRay.getDir();
        Point p0 = axisRay.getP0();
        double t = v.dotProduct(p.subtract(p0));
        Point o;
        try{
            o = axisRay.getPoint(t);
        }catch(IllegalArgumentException e){
            o = p0;
        }
        return p.subtract(o).scale(1/radius);
    }

    /**
     * getter for the axisRay
     * @return axisRay
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * getter for the radius
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        return null;
    }
}
