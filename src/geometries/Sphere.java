package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
/**
 * Sphere class
 * 
 * @author Aryeh and Zvi
 */
public class Sphere extends Geometry {
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

    /**
     * Constructor
     * @param radius
     * @param center
     */
    public Sphere (Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    // /**
    //  * Constructor
    //  * @param element
    //  */
    // public Sphere (Element element){
    //     center = new Point(element.getAttribute("center"));
    //     radius = Double.parseDouble(element.getAttribute("radius"));
    // }

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

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u;
        try{
            u = center.subtract(p0);
        }catch(IllegalArgumentException e){
            Point p = center.add(v.scale(radius));
            GeoPoint gp = new GeoPoint(this, p);
            return List.of(gp);
        }
        double tm = v.dotProduct(u);
        double d2 = u.lengthSquared() - tm*tm;
        double r2 = radius*radius;
        if (d2 > r2 || Util.isZero(d2-r2))
            return null;
        double th = Math.sqrt(r2-d2);
        double t1 = tm + th;
        double t2 = tm - th;
        t1 = Util.alignZero(t1);
        t2 = Util.alignZero(t2);
        List<GeoPoint> intersections = new java.util.ArrayList<>();
        if (t1 > 0 && t2 > 0){
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            GeoPoint gp1 = new GeoPoint(this, p1);
            GeoPoint gp2 = new GeoPoint(this, p2);
            intersections.add(gp1);
            intersections.add(gp2);
        }else if(t1 > 0 && t2 <= 0){
            Point p1 = new Point(0,0,0);
            p1 = ray.getPoint(t1);
            GeoPoint gp1 = new GeoPoint(this, p1);
            intersections.add(gp1);
        }else if(t1 <= 0 && t2 > 0){
            Point p2 = ray.getPoint(t2);
            GeoPoint gp2 = new GeoPoint(this, p2);
            intersections.add(gp2);
        }else{
            return null;
        }
        List<GeoPoint> finalIntersections = new ArrayList<>();
        for (GeoPoint intersection : intersections){
            double distance = ray.getP0().Distance(intersection.point);
            if (maxDistance == Double.POSITIVE_INFINITY){
                finalIntersections.add(intersection);
                continue;
            }
            else if (Util.alignZero(distance - maxDistance) > 0){
                continue;
            }
            finalIntersections.add(intersection);
        }
        if (finalIntersections.isEmpty())
            return null;
        return finalIntersections;
    }
}
