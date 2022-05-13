package primitives;

public class Material {
    public Double3 kD = Double3.ZERO, kS = Double3.ZERO, kT = Double3.ZERO, kR = Double3.ZERO;
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

    /**
     * setter for kT
     * @param kT
     * @return this
     */
    public Material setKT(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * setter for kT
     * @param kT
     * @return this
     */
    public Material setKT(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter for kR
     * @param kR
     * @return this
     */
    public Material setKR(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * setter for kR
     * @param kR
     * @return this
     */
    public Material setKR(Double3 kR) {
        this.kR = kR;
        return this;
    }
}
