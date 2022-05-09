package lighting;
import primitives.*;

public class SpotLight extends PointLight {
    private Vector direction;
    private int kN;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
        kN = 1;
    }

    public PointLight setKN(int kN) {
        this.kN = kN;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        Color I0 = super.getIntensity(p);
        Vector l = this.getL(p);
        if (kN == 1)
            return I0.scale(Math.max(0,direction.dotProduct(l)));
        return I0.scale(Math.max(0, Math.pow(direction.dotProduct(l),kN)));
    }
}
