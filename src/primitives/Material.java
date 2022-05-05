package primitives;

public class Material {
    public Double3 kD, kS;
    public int nShininess;

    /**
     * setter for kD
     * @param kD
     * @return this
     */
    public Material setKD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * setter for kD
     * @param kD
     * @return this
     */
    public Material setKD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * setter for kS
     * @param kS
     * @return this
     */
    public Material setKS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * setter for kS
     * @param kS
     * @return this
     */
    public Material setKS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter for nShininess
     * @param nShininess
     * @return this
     */
    public Material setNShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
