package geometries;

import primitives.*;

public class Cylinder extends Tube {
    double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public Vector getNormal(Point p) {
        Point p0 = axisRay.getP0();
        Vector v = axisRay.getDir();
        Vector pp0;
        try{
            pp0 = p.subtract(p0);
        }catch (IllegalArgumentException e){
            return v.scale(-1);
        }
        double vpp0 = v.dotProduct(pp0);
        if (vpp0 == 0){
            return v.scale(-1);
        }else if(v.dotProduct(pp0) == height){
            return v;
        }else{
            return super.getNormal(p);
        }
    }

}
