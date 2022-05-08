package lighting;
import primitives.*;

public class SpotLight extends PointLight {
    private Vector direction;
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        Color I0 = super.getIntensity(p);
        Vector l = this.getL(p);
        return I0.scale(Math.max(0, direction.dotProduct(l)));
    }
}
