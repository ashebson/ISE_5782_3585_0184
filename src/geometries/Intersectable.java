package geometries;
import java.util.List;

import primitives.*;

/**
 * Intersectable Interface
 */
public abstract class Intersectable {
    /**
     * returns the list of the intersection points of the ray with the scene
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                                : geoList.stream().map(gp -> gp.point).toList();
    }
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
    /**
     * GeoPoint static class
     */
    public static class GeoPoint{
        public Geometry geometry;
        public Point point;

        /**
         * constructor
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (!(obj instanceof GeoPoint))
                return false;
            GeoPoint other = (GeoPoint) obj;
            return point.equals(other.point) && geometry.equals(other.geometry);
        }

        @Override
        public String toString() {
            return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
        }
    }
}
