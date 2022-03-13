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
        Vector v = axisRay.getDir();
        Point p0 = axisRay.getP0();
        double t = v.dotProduct(p.subtract(p0));
        Point o;
        try{
            o = p0.add(v.scale(t));
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
}
