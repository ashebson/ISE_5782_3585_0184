package primitives;

/**
 * Ray class
 * 
 * @author Aryeh and Zvi
 *
 */
public class Ray {
    private Point p0;
    private Vector dir;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
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
        return p0.add(dir.scale(t));
    }
}
