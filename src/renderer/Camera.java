package renderer;

import java.util.MissingResourceException;

import primitives.*;

public class Camera {
    private Point p0;
    private Vector vUp, vTo, vRight;
    private double width, height;
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

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

    /**
     * sets the image writer
     * @param imageWriter
     * @return this
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * sets the ray tracer
     * @param rayTracer
     * @return this
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * renders the scene
     * @return this
     */
    public Camera renderImage(){
        if (imageWriter == null 
            || rayTracer == null 
            || p0 == null 
            || vTo == null 
            || vUp == null 
            || vRight == null 
            || width == 0 
            || height == 0 
            || distance == 0) 
            throw new MissingResourceException("fields must be initialized", "Camera", "*");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                Ray ray = constructRay(nX, nY, i, j);
                Color color = rayTracer.traceRay(ray);
                imageWriter.writePixel(i, j, color);
            }
        }
        return this;
    }

    /**
     * adds grid to the image
     * @param interval
     * @param color
    */
    public void printGrid(int interval, Color color){
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter must be initialized", "Camera", "imageWriter");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++){
            for (int j = 0; j < nY; j++){
                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * writes image to file
     */
    public void writeToImage(){
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter must be initialized", "Camera", "imageWriter");
        imageWriter.writeToImage();
    }
}
