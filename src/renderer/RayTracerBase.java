package renderer;
import scene.*;
import primitives.*;

/**
 * This is a ray tracer, yay!
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor
     * @param scene
     */
    public RayTracerBase(Scene scene){
        this.scene = scene;
    }

    /**
     * traces ray
     * @param ray
     * @return color
     */
    public abstract Color traceRay(Ray ray);
}
