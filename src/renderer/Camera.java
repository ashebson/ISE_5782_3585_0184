package renderer;

import primitives.*;

public class Camera {
    private Point p0;
    private Vector vUp, vTo, vRight;
    private double width, height;
    private double distance;

    /**
     * constructor based on a point, vectors, width, height and distance
     * 
     * @param p0
     * @param vTo
     * @param vUp
     * @param width
     * @param height
     */
    public Camera(Point p0, Vector vTo, Vector vUp) {
        if (vUp.dotProduct(vTo) != 0)
            throw new IllegalArgumentException("vUp and vTo must be orthogonal");
        this.vUp = vUp.normalize();
        this.vTo = vTo.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
        this.p0 = p0;
    }

    /**
     * getter for p0
     * 
     * @return the p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for vUp
     * 
     * @return the vUp
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * getter for vTo
     * 
     * @return the vTo
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * getter for vRight
     * 
     * @return the vRight
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * getter for width
     * 
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getter for height
     * 
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * getter for distance
     * 
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * setter for width and height, which returns the new camera
     * @param width
     * @param height
     * @return the new camera
     * */
    public Camera setWidthAndHeight(double width, double height){
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * setter for distance, which returns the new camera
     * @param distance
     * @return the new camera
     * */
    public Camera setDistance(double distance){
        this.distance = distance;
        return this;
    }

    /**
     * constructs ray through pixel
     * @param nX pixel column count
     * @param nY pixel row count
     * @param i pixel row
     * @param j pixel column
     * @return ray
     * */
    public Ray constructRay(int nX, int nY, int j, int i){
        double rY = height/nY;
        double rX = width/nX;
        Point pC = p0.add(vTo.scale(distance));
        double yI = -(i-((double)nY -1)/2)*rY;
        double xJ = (j-((double)nX -1)/2)*rX;
        Point pIJ = pC;
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));
        return new Ray(p0, pIJ.subtract(p0));
    }
}
