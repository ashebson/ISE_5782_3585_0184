package geometries;

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

}
