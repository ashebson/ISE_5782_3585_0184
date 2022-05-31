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
        generateBox();
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
        Vector v = axisRay.getDir();
        Point basePoint1 = axisRay.getP0();
        Plane basePlane1 = new Plane(basePoint1, v);
        Point basePoint2;
        if (height != 0){
            basePoint2 = basePoint1.add(v.scale(height));
        }else{
            basePoint2 = basePoint1;
        }
        Plane basePlane2 = new Plane(basePoint2, v);
        lst = basePlane1.findGeoIntersections(ray,max);
        if (lst != null)
            for (GeoPoint geoPoint : lst) {
                double distanceSquared = Util.alignZero(geoPoint.point.DistanceSquared(basePoint1));
                if (Util.alignZero(distanceSquared - radius*radius) < 0)
                    res.add(new GeoPoint(this, geoPoint.point));
            }
        lst = basePlane2.findGeoIntersections(ray,max);
        if (lst != null)
            for (GeoPoint geoPoint : lst) {
                double distanceSquared = Util.alignZero(geoPoint.point.DistanceSquared(basePoint2));
                if (Util.alignZero(distanceSquared - radius*radius) < 0)
                    res.add(new GeoPoint(this, geoPoint.point));
            }
        if (res.size() == 0)
            return null;
        return res;
    }

    @Override
    public void generateBox(){
        Point base1 = axisRay.getP0();
        Point base2 = axisRay.getPoint(height);
        double xMax = Math.max(base1.getX(), base2.getX());
        double xMin = Math.min(base1.getX(), base2.getX());
        double yMax = Math.max(base1.getY(), base2.getY());
        double yMin = Math.min(base1.getY(), base2.getY());
        double zMax = Math.max(base1.getZ(), base2.getZ());
        double zMin = Math.min(base1.getZ(), base2.getZ());
        Point min = new Point(xMin, yMin, zMin);
        Point max = new Point(xMax, yMax, zMax);
        Vector delta = new Vector(1,1,1).scale(radius);
        min = min.subtract(delta);
        max = max.add(delta);
        setBox(new Box(min, max));
    }
}
