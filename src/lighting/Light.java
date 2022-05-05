package lighting;

import primitives.Color;

/**
 * This is class for light source
 */
abstract class Light {
    private Color intensity;

    /**
     * Constructor
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * getter for intensity
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
