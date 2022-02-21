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
        // TODO Auto-generated method stub
        return null;
    }
    public Ray getAxisRay() {
        return axisRay;
    }
    public double getRadius() {
        return radius;
    }
}
