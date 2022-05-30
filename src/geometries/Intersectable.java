package geometries;

import java.util.List;

import primitives.*;

/**
 * Intersectable Interface
 */
public abstract class Intersectable {
    private Box box;

    protected abstract void generateBox();

    /**
     * setter for the box
     * @param box
     */
    protected void setBox(Box box) {
        this.box = box;
    }

    /**
     * getter for the box
     * @return
     */
    public Box getBox() {
        return box;
    }

    /**
     * returns the list of the intersection points of the ray with the scene
     * 
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        if (box == null || box.intersects(ray)){
            return findGeoIntersectionsHelper(ray, maxDistance);
        }else{
            return null;
        }
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * GeoPoint static class
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor
         * 
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
