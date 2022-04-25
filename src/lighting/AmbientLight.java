package lighting;

import org.w3c.dom.Element;

import primitives.*;

public class AmbientLight {
    Color intensity;
    /**
     * Constructor
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * Constructor
     * 
     * @param intensity
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    public AmbientLight(Element element){
        intensity = new Color(element.getAttribute("color"));
    }

    /**
     * getter for intensity
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
