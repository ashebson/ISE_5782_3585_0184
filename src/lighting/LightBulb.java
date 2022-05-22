package lighting;

import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point;

public class LightBulb extends PointLight {

    public LightBulb(Color intensity, Point position, double radius) {
        super(intensity, position);
        shape = new Sphere(position, radius).setMaterial(
            new Material()
            .setKT(0.8)
            .setKD(0.2)
            .setKS(0.2)
            .setNShininess(30)
        );
    }
    
}
