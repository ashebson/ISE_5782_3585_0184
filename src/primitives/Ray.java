package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * Ray class
 * 
 * @author Aryeh and Zvi
 *
 */
public class Ray {
    private Point p0;
    private Vector dir;
    private static final double DELTA = 0.1;
    /**
     * constructor based on a point and a vector
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        // check if dir isn't normalized and if so normalize
        dir = dir.normalize();
        this.p0 = p0;
        this.dir = dir;
    }

    public Ray(Point p0, Vector direction, Vector normal){
        double ln = direction.dotProduct(normal);
        if (ln < 0) {
            normal = normal.scale(-1);
        }
        this.p0 = p0.add(normal.scale(DELTA));
        this.dir = direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Ray))
            return false;
        Ray other = (Ray) obj;
        return p0.equals(other.p0) && dir.equals(other.dir);
    }

    /**
     * getter for the point
     * @return the p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for the direction vector
     * @return the dir
     */
    public Vector getDir() {
        return dir;
    }
    
    @Override
    public String toString() {
        return "Ray: " + p0 + ", " + dir;
    }

    /**
     * gets point t away from the origin
     * @param t
     * @return the point
     */
    public Point getPoint(double t) {
        if (Util.isZero(t)){
            return p0;
        }
        return p0.add(dir.scale(t));
    }

    /**
     * gets the closest intersection point to the ray
     * @param geometries
     * @return the point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
               : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        GeoPoint closestGeoPoint = null;
        double minDistanceSquared = Double.MAX_VALUE;
        for (GeoPoint geoPoint : geoPoints) {
            double distanceSqaured = geoPoint.point.DistanceSquared(p0);
            if (distanceSqaured < minDistanceSquared) {
                minDistanceSquared = distanceSqaured;
                closestGeoPoint = geoPoint;
            }
        }
        return closestGeoPoint;
    }
}
