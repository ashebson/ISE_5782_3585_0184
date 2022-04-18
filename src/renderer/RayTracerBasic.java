package renderer;

import java.util.List;

import primitives.Color;
import primitives.*;
import scene.Scene;

/**
 * this is also a ray tracer, yay yay!
 */
public class RayTracerBasic extends RayTracerBase{
    /**
     * Constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    private Color calcColor(Point point){
        return scene.ambientLight.getIntensity();
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if (intersections == null)
            return scene.background;
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }
}
