package geometries;

import primitives.*;

/**
 * Tube class
 * 
 * @author Aryeh and Zvi
 */
public class Tube implements Geometry {
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
        // TODO Auto-generated method stub
        return null;
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
}
