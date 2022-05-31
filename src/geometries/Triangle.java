package geometries;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;

import primitives.*;
import parser.*;

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

    public Triangle(Element element){
        this(
            new Point(Parser.parseDouble3(element.getAttribute("p0"))),
            new Point(Parser.parseDouble3(element.getAttribute("p1"))),
            new Point(Parser.parseDouble3(element.getAttribute("p2")))
        );
        Material material = new Material((Element)element.getElementsByTagName("primitives.Material").item(0));
        this.setMaterial(material);
    }


    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){
        List<Point> planeIntersections = plane.findIntersections(ray);
        if (planeIntersections == null)
            return null;
        Point p = planeIntersections.get(0);
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();
        double vn1 = n1.dotProduct(v);
        double vn2 = n2.dotProduct(v);
        double vn3 = n3.dotProduct(v);
        GeoPoint intersection;
        if (Util.isZero(vn1) || Util.isZero(vn2) || Util.isZero(vn3))
            return null;
        if ((vn1 > 0 == vn2 > 0) && (vn1 > 0 == vn3 > 0)){
            intersection = new GeoPoint(this,p);
            double distance = ray.getP0().Distance(intersection.point);
            if (maxDistance == Double.POSITIVE_INFINITY){
                return List.of(intersection);
            }
            if (Util.alignZero(distance - maxDistance) > 0){
                return null;
            }
            return List.of(intersection);
        }
        return null;
    }
}
