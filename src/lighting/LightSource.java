package lighting;

import java.util.List;

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
     * builds vectors from light to point
     * @return position
     */
    public List<Vector> getLs(Point p, int maxAmountOfShadowRays);

    /**
     * calculates distance to given point
     * @param p
     * @return
     */
    public double getDistance(Point p);
}
