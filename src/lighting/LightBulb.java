package lighting;

import java.util.LinkedList;
import java.util.List;

import geometries.*;
import primitives.*;

public class LightBulb extends PointLight {
    private Sphere sphere;

    public LightBulb(Color intensity, Point position, double radius) {
        super(intensity, position);
        sphere = new Sphere(position, radius);
    }

    @Override
    public List<Vector> getLs(Point p, int maxAmountOfShadowRays) {
        Vector l = getL(p);
        Point center = sphere.getCenter();
        Plane plane = new Plane(center, l);
        int N = (int) Math.floor(Math.sqrt(maxAmountOfShadowRays));
        List<Vector> gridVectors = plane.getTwoNormalizedOrthogonalVectors();
        Vector v1 = gridVectors.get(0).scale(sphere.getRadius() / N);
        Vector v2 = gridVectors.get(1).scale(sphere.getRadius() / N);        
        List<Vector> ls = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Point p1 = center;
                if (i - (((double)N-1) / 2) != 0) {
                    p1 = p1.add(v1.scale((i - (((double)N-1) / 2))));
                }
                if (j - (((double)N-1) / 2) != 0) {
                    p1 = p1.add(v2.scale((j - (((double)N-1) / 2))));
                }
                if (center.DistanceSquared(p1) <= sphere.getRadius() * sphere.getRadius()) {
                    ls.add(p.subtract(p1).normalize());
                }
            }
        }
        return ls;
    }
}
