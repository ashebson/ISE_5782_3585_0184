package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double max) {
        /**
         * we find the projection and calcs its size and if it is like the height the point inside the cylinder (we can use dot product cause the axsis in normalized )
         * 
         */
        
        List<GeoPoint> res = new ArrayList<>();
        List<GeoPoint> lst = super.findGeoIntersectionsHelper(ray,max);
        if (lst != null)
            for (GeoPoint geoPoint : lst) {
                double distance = Util.alignZero(geoPoint.point.subtract(axisRay.getP0()).dotProduct(axisRay.getDir()));
                if (distance > 0 && distance <= height)
                    res.add(geoPoint);
            }

        if (res.size() == 0)
            return null;
        return res;
    }
}
