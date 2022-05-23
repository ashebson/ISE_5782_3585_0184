package renderer;


import java.util.List;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

/**
 * this is also a ray tracer, yay yay!
 */
public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final Double3 INITIAL_K = Double3.ONE;
    private static final double MIN_CALC_COLOR_K = 0.0001;
    private static final int MAX_NUMBER_OF_SHADOW_RAYS = 1000;

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
        double scaler = Math.pow(Math.max(0, v.scale(-1).dotProduct(r)), material.nShininess);
        if (Util.isZero(scaler)){
            return Double3.ZERO;
        }
        return material.kS.scale(scaler);
    }

    /**
     * Calculates the affect of light sources and material emittion on the color of
     * a geopoint
     * 
     * @param geoPoint
     * @param ray
     * @return
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Color Ie = geoPoint.geometry.getEmission();
        Color Ilights = Color.BLACK;
        Material material = geoPoint.geometry.getMaterial();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDir();        
        for (LightSource light : scene.lights) {
            List<Vector> ls = light.getLs(geoPoint.point, MAX_NUMBER_OF_SHADOW_RAYS);
            Color Ils = Color.BLACK;
            for(Vector l : ls){
                double nl = Util.alignZero(n.dotProduct(l));
                double vn = Util.alignZero(v.dotProduct(n));
                if (nl * vn > 0) {
                    Double3 ktr = transparency(geoPoint, l, n, light);
                    if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
                        Double3 diff = calcDiffusive(material, nl);
                        Double3 spec = calcSpecular(material, nl, n, l, v);
                        Color Il = light.getIntensity(geoPoint.point).scale(diff.add(spec));
                        Ils = Ils.add(Il.scale(ktr));
                    }
                }
            }

            Ils = Ils.reduce(ls.size());
            Ilights = Ilights.add(Ils);
        }
        return Ie.add(Ilights);
    }

    /**
     * calculates the global effects of a scene on a point
     * 
     * @param geoPoint
     * @param v
     * @param level
     * @param k
     * @return the global effects on a point
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Material material = geoPoint.geometry.getMaterial();
        Double3 kkr = material.kR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            color = calcGlobalEffects(constructReflectedRay(geoPoint.point, v, n), level, material.kR, kkr);
        }
        Double3 kkt = material.kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            color = color.add(calcGlobalEffects(constructRefractedRay(geoPoint.point, v, n), level, material.kT, kkt));
        }
        return color;
    }

    /**
     * creates a refracted ray
     * 
     * @param point
     * @param v
     * @param n
     * @return refracted ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * calculates the local effects of a scene
     * 
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffects(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint geoPoint = findClosestIntersection(ray);
        if (geoPoint == null) {
            return scene.background;
        }
        return calcColor(geoPoint, ray, level - 1, kkx).scale(kx);
    }

    /**
     * finds the closest intersection of a ray with the scene
     * 
     * @param ray
     * @return the closest intersection
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return closestPoint;
    }

    /**
     * creates a reflected ray
     * 
     * @param point
     * @param v
     * @param n
     * @return reflected ray
     */
    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        double nv = n.dotProduct(v);
        Vector r = v.subtract(n.scale(nv * 2));
        return new Ray(point, r, n);
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
        return Ia.add(calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K));
    }

    /**
     * calculates the color of a geopoint recursivly
     * 
     * @param geoPoint
     * @param ray
     * @param level
     * @param k
     * @return geopoint color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);
        if (level == 1) {
            return color;
        } else {
            return color.add(calcGlobalEffects(geoPoint, ray.getDir(), level, k));
        }
    }

    /**
     * calculates the color of a ray
     * 
     * @param ray
     * @return color of the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        if (closestPoint == null)
            return scene.background;
        return calcColor(closestPoint, ray);
    }

    /**
     * checks if geopoint is unshaded
     * 
     * @param geopoint
     * @param l
     * @param n
     * @param lightSource
     * @return if is unshaded
     */
    public boolean unshaded(GeoPoint geopoint, Vector l, Vector n, LightSource lightSource) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
                lightSource.getDistance(geopoint.point));
        if (intersections == null)
            return true;
        for (GeoPoint intersection : intersections) {
            if (intersection.geometry.getMaterial().kT == Double3.ZERO) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks the transparency of a geopoint
     * @param geopoint
     * @param l
     * @param n
     * @param lightSource
     * @return
     */
    public Double3 transparency(GeoPoint geopoint, Vector l, Vector n, LightSource lightSource) {
        Vector lightDirection = l.scale(-1);
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay,
                lightSource.getDistance(geopoint.point));
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint intersection : intersections) {
            ktr = intersection.geometry.getMaterial().kT.product(ktr);
            if (ktr.equals(Double3.ZERO)){
                return Double3.ZERO;
            }
        }
        return ktr;
    }
}
