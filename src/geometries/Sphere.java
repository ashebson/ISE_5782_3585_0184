package geometries;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;

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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
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
        if (t1 > 0 && t2 > 0){
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            GeoPoint gp1 = new GeoPoint(this, p1);
            GeoPoint gp2 = new GeoPoint(this, p2);
            return List.of(gp1,gp2);
        }else if(t1 > 0 && t2 <= 0){
            Point p1 = new Point(0,0,0);
            try{
                p1 = ray.getPoint(t1);
            }catch (IllegalArgumentException e){
                System.out.println("t1: " + t1);
                p1 = ray.getPoint(t1);
            }
            GeoPoint gp1 = new GeoPoint(this, p1);
            return List.of(gp1);
        }else if(t1 <= 0 && t2 > 0){
            Point p2 = ray.getPoint(t2);
            GeoPoint gp2 = new GeoPoint(this, p2);
            return List.of(gp2);
        }else{
            return null;
        }
    }
}
