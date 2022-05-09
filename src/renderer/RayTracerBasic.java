package renderer;

import static org.junit.Assume.assumeTrue;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.*;
import scene.Scene;

/**
 * this is also a ray tracer, yay yay!
 */
public class RayTracerBasic extends RayTracerBase {
    private static final double DELTA = 0.1;

    /**
     * Constructor
     * 
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Calculates the diffusive component of the light on a material
     * 
     * @param material
     * @param nl
     * @return
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * Calculates the specular component of the light on a material
     * 
     * @param material
     * @param nl
     * @param n
     * @param l
     * @param v
     * @return
     */
    private Double3 calcSpecular(Material material, double nl, Vector n, Vector l, Vector v) {
        Vector r = l.subtract(n.scale(nl * 2));
        return material.kS.scale(Math.pow(Math.max(0, v.scale(-1).dotProduct(r)), material.nShininess));
    }

    /**
     * Calculates the affect of light sources and material emittion on the color of
     * a geopoint
     * 
     * @param geoPoint
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color Ie = geoPoint.geometry.getEmission();
        Color Ils = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();
        for (var light : scene.lights) {
            Vector l = light.getL(geoPoint.point);
            double nl = n.dotProduct(l);
            double vn = v.dotProduct(n);
            if (nl * vn > 0) {
                if (true || unshaded(geoPoint, l, n)) {
                    Double3 diff = calcDiffusive(material, nl);
                    Double3 spec = calcSpecular(material, nl, n, l, v);
                    Color Il = light.getIntensity(geoPoint.point).scale(diff.add(spec));
                    Ils = Ils.add(Il);
                }
            }
        }
        return Ie.add(Ils);
    }

    /**
     * calculates the color of a geopoint
     * 
     * @param geoPoint
     * @param ray
     * @return color of the geopoint
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        Color Ia = scene.ambientLight.getIntensity();
        return Ia.add(calcLocalEffects(geoPoint, ray));
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    /**
     * checks if geopoint is unshaded
     * 
     * @param gp
     * @param l
     * @param n
     * @return if is unshaded
     */
    public boolean unshaded(GeoPoint geopoint, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geopoint.point, lightDirection);
        return scene.geometries.findGeoIntersections(lightRay) == null;
    }
}
