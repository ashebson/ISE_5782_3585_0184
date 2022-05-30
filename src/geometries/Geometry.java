package geometries;

import primitives.*;

/**
 * Geometry interface
 * 
 * @author Aryeh and Zvi
 */
public abstract class Geometry extends Intersectable{
    protected Color emission = Color.BLACK;
    private Material material = new Material();

    /**
     * getter for the normal
     *
     * @param p
     * @return normal
     */
    public abstract Vector getNormal(Point p);

    /**
     * getter for the emission
     *
     * @return emition
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * sets the color of the emission
     *
     * @param emission
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * getter for the material
     *
     * @return material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setter for the material
     *
     * @param material
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
