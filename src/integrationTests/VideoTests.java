package integrationTests;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

import java.util.ArrayList;
import java.util.List;

public class VideoTests {
    private Scene scene1 = new Scene("Test scene");
	private Scene scene2 = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
	private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setWidthAndHeight(150, 150) //
			.setDistance(1000);
	private Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setWidthAndHeight(200, 200) //
			.setDistance(1000);

	private Point[] p = { // The Triangles' vertices:
			new Point(-110, -110, -150), // the shared left-bottom
			new Point(95, 100, -150), // the shared right-top
			new Point(110, -110, -150), // the right-bottom
			new Point(-75, 78, 100) }; // the left-top
	private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
	private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
	private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
	private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
	private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
	private Material material = new Material().setKD(0.5).setKS(0.5).setNShininess(300);
	private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
	private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
	private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
			.setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));
    private Geometry cylinder = new Cylinder(new Ray(new Point(0, 0, -50), new Vector(1, 1, 1)), 50d, 60d)
            .setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));
    //@Test
	public void sphereDirectional() {
        Color colorDelta = new Color(BLACK);
        Vector vectorDelta = new Vector(-(2.0/60), -(2.0/60), 0);
        Intersectable[] geos = {sphere};
        for(int i = 0; i < 60; i++){
            scene1 = new Scene("Test scene");
            scene1.geometries.add(geos);
            Vector propotionalVectorDelta = new Vector(1,1,1);
            try{
                propotionalVectorDelta = vectorDelta.scale(i);
            }catch (IllegalArgumentException e){
                propotionalVectorDelta = vectorDelta;
            }
            scene1.lights.add(new DirectionalLight(spCL.add(colorDelta), new Vector(1, 1, -0.5).add(propotionalVectorDelta)));
            
            ImageWriter imageWriter = new ImageWriter("sphereVideo/videoFrameSphereDirectional"+i, 500, 500);
		    camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
        }
	}

    //@Test
	public void cylinderDirectional() {
        Color colorDelta = new Color(BLACK);
        Vector vectorDelta = new Vector(-(2.0/60), -(2.0/60), 0);
        Intersectable[] geos = {cylinder};
        for(int i = 0; i < 30; i++){
            scene1 = new Scene("Test scene");
            scene1.geometries.add(geos);
            Vector propotionalVectorDelta = new Vector(1,1,1);
            try{
                propotionalVectorDelta = vectorDelta.scale(i);
            }catch (IllegalArgumentException e){
                propotionalVectorDelta = vectorDelta;
            }
            scene1.lights.add(new DirectionalLight(spCL.add(colorDelta), new Vector(1, 1, -0.5).add(propotionalVectorDelta)));
            
            ImageWriter imageWriter = new ImageWriter("videoFrameCylinderDirectional"+i, 500, 500);
		    camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
        }
	}
}
