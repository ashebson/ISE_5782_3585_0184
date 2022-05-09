package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource{
    private Point position;
    private double kC, kL, kQ;

    /**
     * Constructor
     * @param position
     * @param kC
     * @param kL
     * @param kQ
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.kC = 1;
        this.kL = 0;
        this.kQ = 0;
    }


    @Override
    public Color getIntensity(Point p) {
        double d = position.Distance(p);
        return this.getIntensity().scale(1/(kC + kL*d + kQ*d*d));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }


    @Override
    public double getDistance(Point p) {
        return position.Distance(p);
    }
}
