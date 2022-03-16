package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
/**
 * Sphere class
 * 
 * @author Aryeh and Zvi
 */
public class Sphere implements Geometry {
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
    public Sphere (double radius, Point center) {
        this.center = center;
        this.radius = radius;
    }

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
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u;
        try{
            u = center.subtract(p0);
        }catch(IllegalArgumentException e){
            Point p = center.add(v.scale(radius));
            return List.of(p);
        }
        double tm = v.dotProduct(u);
        double d2 = u.lengthSquared() - tm*tm;
        double r2 = radius*radius;
        if (d2 > r2 || Util.isZero(d2-r2))
            return null;
        double th = Math.sqrt(r2-d2);
        double t1 = tm + th;
        double t2 = tm - th;
        Util.alignZero(t1);
        Util.alignZero(t2);
        if (t1 > 0 && t2 > 0){
            Point p1 = p0.add(v.scale(t1));
            Point p2 = p0.add(v.scale(t2));
            return List.of(p1,p2);
        }else if(t1 > 0 && t2 <= 0){
            Point p1 = p0.add(v.scale(t1));
            return List.of(p1);
        }else if(t1 <= 0 && t2 > 0){
            Point p2 = p0.add(v.scale(t2));
            return List.of(p2);
        }else{
            return null;
        }
    }
}
