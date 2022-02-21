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
     * constructor
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        // check if dir isn't normalized and if so normalize
        if (!dir.isNormalized()) {
            dir = dir.normalize();
        }
        this.p0 = p0;
        this.dir = dir;
    }
    /**
     * @return the p0
     */
    public Point getP0() {
        return p0;
    }
    /**
     * @return the dir
     */
    public Vector getDir() {
        return dir;
    }
    @Override
    public String toString() {
        return "Ray: " + p0 + ", " + dir;
    }
}
