package primitives;

/**
 * @author Aryeh and Zvi
 */
public class Vector extends Point {
    /**
     * Constructor
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        // check if is 0 vector, if so throw exception
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector cannot be 0 vector");
        }
    }

    /**
     * @param v
     * @return the sum of this vector and v
     */
    public Vector add(Vector v) {
        Double3 sum = xyz.add(v.xyz);
        return new Vector(sum.d1, sum.d2, sum.d3);
    }

    /**
     * @param v
     * @return this vector minus v
     */
    public Vector subtract(Vector v) {
        Double3 sub = xyz.subtract(v.xyz);
        return new Vector(sub.d1, sub.d2, sub.d3);
    }

    /**
     * @return scalar multiplication of this vector and s
     * @param s
     */
    public Vector scale(double s) {
        Double3 scaled = xyz.scale(s);
        return new Vector(scaled.d1, scaled.d2, scaled.d3);
    }

    /**
     * @return dot product of this vector and v
     * @param v
     */
    public Double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2
                + xyz.d3 * v.xyz.d3;
    }

    /**
     * @return the cross product of this vector and v
     * @param v
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2,
                xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3,
                xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }

    /**
     * @return the length of the vector squared
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * @return the normalized vector
     */
    public Vector normalize() {
        return scale(1.0 / length());
    }

    /**
     * @return the normal
     */
    public Vector getNormal() {
        return new Vector(xyz.d1, xyz.d2, xyz.d3);
    }

    public Boolean isNormalized() {
        return lengthSquared() == 1.0;
    }

    @Override
    public String toString() {
        return "→ " + xyz;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((xyz == null) ? 0 : xyz.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Vector))
            return false;
        Vector other = (Vector) obj;
        if (xyz == null) {
            if (other.xyz != null)
                return false;
        } else if (!xyz.equals(other.xyz))
            return false;
        return true;
    }
}
