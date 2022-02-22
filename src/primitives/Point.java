package primitives;

/**
 * Point class
 * 
 * @author Aryeh and Zvi
 */
public class Point {
    final protected Double3 xyz;

    /**
     * constructor with 3 doubles
     * 
     * @param x
     * @param y 
     * @param z
     * 
     * @return Point
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * subtracts a point from this point and returns the vector result
     * 
     * @param p
     * 
     * @return Vector
     */
    public Vector subtract(Point p) {
        Double3 sub = xyz.subtract(p.xyz);
        return new Vector(sub.d1,sub.d2,sub.d3);
    }

    /**
     * adds a vector to this point and returns the point result
     * 
     * @param v
     * 
     * @return Point
     */
    public Point add(Vector v) {
        Double3 sum = xyz.add(v.xyz);
        return new Point(sum.d1,sum.d2,sum.d3);
    }

    /**
     * returns the distance squared between this point and another point
     * 
     * @param p
     * 
     * @return double
     */
    public double DistanceSquared(Point p) {
        return this.xyz.d1 * this.xyz.d1 + this.xyz.d2 * this.xyz.d2
                + this.xyz.d3 * this.xyz.d3;
    }

    /**
     * returns the distance between this point and another point
     * 
     * @param p
     * 
     * @return double
     */
    public double Distance(Point p){
        return Math.sqrt(DistanceSquared(p));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        if (xyz == null) {
            if (other.xyz != null)
                return false;
        } else if (!xyz.equals(other.xyz))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "• "+ xyz;
    }
}
