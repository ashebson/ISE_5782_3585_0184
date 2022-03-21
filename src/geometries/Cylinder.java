package geometries;

import java.util.List;

import primitives.*;

/**
 * Cylinder class
 * 
 * @author Aryeh and Zvi
 */
public class Cylinder extends Tube {
    double height;

    /**
     * constructor based on Ray and radius and height
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * getter for the height
     * 
     * @return height
     */
    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        Vector pp0;
        try {
            pp0 = p.subtract(p0);
        } catch (IllegalArgumentException e) {
            return v.scale(-1);
        }
        double vpp0 = v.dotProduct(pp0);
        if (Util.isZero(vpp0)) {
            return v.scale(-1);
        } else if (Util.isZero(v.dotProduct(pp0) - height)) {
            return v;
        } else {
            return super.getNormal(p);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }

}
