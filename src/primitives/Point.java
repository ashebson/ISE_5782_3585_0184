package primitives;

/**
 * Point class
 * 
 * @author Aryeh and Zvi
 */
public class Point {
    final protected Double3 xyz;

    /**
     * constructor with Double3
     * 
     * @param xyz
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * constructor with 3 doubles
     * 
     * @param x
     * @param y 
     * @param z
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
        return new Vector(xyz.subtract(p.xyz));
    }

    /**
     * adds a vector to this point and returns the point result
     * 
     * @param v
     * 
     * @return Point
     */
    public Point add(Vector v) {
        return new Point(xyz.add(v.xyz));
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
        return xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "• "+ xyz;
    }

    /**
     * returns the x coordinate of this point
     * 
     * @return double
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * returns the y coordinate of this point
     * 
     * @return double
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * returns the z coordinate of this point
     * 
     * @return double
     */
    public double getZ() {
        return xyz.d3;
    }
}
