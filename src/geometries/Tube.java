package geometries;

import primitives.*;

public class Tube implements Geometry {
    Ray axisRay;
    double radius;
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
    public Ray getAxisRay() {
        return axisRay;
    }
    public double getRadius() {
        return radius;
    }
}
