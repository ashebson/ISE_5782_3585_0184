package lighting;

import primitives.*;

public interface LightSource {
    /**
     * getter for intensity of the light
     * @param p
     * @return intensity
     */
    public Color getIntensity(Point p);

    /**
     * builds vector from light to point
     * @param p
     * @return L
    */
    public Vector getL(Point p);

    /**
     * calculates distance to given point
     * @param p
     * @return
     */
    public double getDistance(Point p);
}
