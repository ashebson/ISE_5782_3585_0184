package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
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

    private Color calcColor(GeoPoint geoPoint, Ray ray){
        Color Ia = scene.ambientLight.getIntensity();
        Color Ie = geoPoint.geometry.getEmission();
        Color Ils = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        for (var light : scene.lights){
            Vector l = light.getL(geoPoint.point);
            double nl = n.dotProduct(l);
            double vn = v.dotProduct(n);
            if (nl*vn > 0){
                Double3 kD = material.kD;
                Double3 kS = material.kS;
                Vector r = l.subtract(n.scale(l.dotProduct(n)*2));
                Double3 diff = kD.scale(Math.abs(l.dotProduct(n)));
                Double3 spec = kS.scale(Math.pow(Math.max(0,v.scale(-1).dotProduct(r)), material.nShininess));
                Color Il = light.getIntensity(geoPoint.point).scale(diff.add(spec));
                Ils = Ils.add(Il);
            }
        }
        return Ia.add(Ie).add(Ils);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }
}
