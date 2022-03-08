package geometries;

import primitives.*;

/**
 * Geometry interface
 * 
 * @author Aryeh and Zvi
 */
public interface Geometry {
    /**
     * returns for the normal vector for a point
     *
     * @param p
     * @return
     */
    Vector getNormal(Point p);
}
