package primitives;

/**
 * Vector class
 * 
 * @author Aryeh and Zvi
 */
public class Vector extends Point {

    /**
     * constructor with Double3
     * 
     * @param xyz
     */
    public Vector(Double3 v) {
        super(v);
        if(v.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector cannot be zero");
    };

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
     * add a vector to this vector and returns the result
     * 
     * @param v
     * @return the sum of this vector and v
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * subtract a vector from this vector and returns the result
     * 
     * @param v
     * @return this vector minus v
     */
    public Vector subtract(Vector v) {
        return new Vector(xyz.subtract(v.xyz));
    }

    /**
     * scalar multiplication of this vector and returns the result
     * 
     * @return scalar multiplication of this vector and s
     * @param s
     */
    public Vector scale(double s) {
        return new Vector(xyz.scale(s));
    }

    /**
     * returns this with different length
     * @param s
     * @return new vector
     */
    public Vector changeLength(double s){
        return this.normalize().scale(s);
    }

    /**
     * dot product of this vector and v
     * 
     * @return dot product of this vector and v
     * @param v
     */
    public double dotProduct(Vector v) {
        return xyz.d1 * v.xyz.d1 + xyz.d2 * v.xyz.d2
                + xyz.d3 * v.xyz.d3;
    }

    /**
     * cross product of this vector and v
     * 
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
     * returns the length of this vector quared
     * 
     * @return the length of the vector squared
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * returns the length of this vector
     * 
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * returns the unit vector of this vector
     * 
     * @return the normalized vector
     */
    public Vector normalize() {
        if (lengthSquared() != 1.0) // if the vector is already normalized, return this vector
            return scale(1.0 / length());
        return this;
    }

    @Override
    public String toString() {
        return "→ " + xyz;
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
        return xyz.equals(other.xyz);
    }

    public Vector turn(double angle, Vector ortho){
        ortho = ortho.changeLength(this.length());
        double ortho_scaler = Math.sin(angle);
        double this_scaler = Math.cos(angle);
        if (Util.isZero(ortho_scaler)){
            return this.scale(this_scaler).changeLength(this.length());
        }else if(Util.isZero(this_scaler)){
            return ortho.scale(ortho_scaler).changeLength(this.length());
        }
        return ortho.scale(Math.sin(angle)).add(this.scale(Math.cos(angle))).changeLength(this.length());
    }
}
