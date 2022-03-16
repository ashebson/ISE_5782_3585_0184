package geometries;
import java.util.List;

import primitives.*;

/**
 * Intersectable Interface
 */
public interface Intersectable {
    /**
     * returns the list of the intersection points of the ray with the scene
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray);
}
