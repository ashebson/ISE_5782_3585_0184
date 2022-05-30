package primitives;

public class Box {
    Point min, max;

    /**
     * Constructor
     * 
     * @param min
     *            - minimum point of the box
     * @param max
     *            - maximum point of the box
     */
    public Box(Point min, Point max) {
        this.min = min;
        this.max = max;
    }
    /**
     * checks if box intersects with a ray
     * @param ray
     * @return
     */
    public Boolean intersects(Ray ray){
        Double3 p0 = ray.getP0().xyz;
        Double3 r = ray.getDir().xyz;
        Double3 t1 = min.xyz.subtract(p0).reduce(r);
        Double3 t2 = max.xyz.subtract(p0).reduce(r);
        double tMinX = Math.min(t1.d1, t2.d1);
        double tMaxX = Math.max(t1.d1, t2.d1);
        double tMinY = Math.min(t1.d2, t2.d2);
        double tMaxY = Math.max(t1.d2, t2.d2);
        double tMinZ = Math.min(t1.d3, t2.d3);
        double tMaxZ = Math.max(t1.d3, t2.d3);
        double tMin = Math.max(tMinX, Math.max(tMinY, tMinZ));
        double tMax = Math.min(tMaxX, Math.min(tMaxY, tMaxZ));
        return tMin <= tMax && tMax >= 0;
    }

    public Point getMin() {
        return min;
    }

    public Point getMax() {
        return max;
    }
}
