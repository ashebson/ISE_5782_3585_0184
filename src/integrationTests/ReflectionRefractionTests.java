package integrationTests;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.*;
import static java.awt.Color.*;


public class ReflectionRefractionTests{
    private Scene scene = new Scene("Test scene");
    private Camera camera1 = new Camera(new Point(-100, -100, 0), new Vector(1, 1, 0), new Vector(0, 0, 1)) //
                .setWidthAndHeight(150, 150) //
                .setDistance(1000);
    
			
    @Test 
    void superMegaAwesomeTest(){
        Geometry plane1 = new Plane(new Point(0,0,0), new Point(0,1,0.5), new Point(1,0,0.5))
            .setEmission(new Color(RED).reduce(2))
            .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));
        Geometry sphere1 = new Sphere(new Point(0,0,3),2)
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300).setKT(new Double3(0.6)));
        Geometry sphere2 = new Sphere(new Point(-2.5,2.5,2),1)
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));
        Geometry plane2 = new Plane(new Point (5,5,0),new Point (2.5,-2.5,0), new Point(2.5,-2.5,5))
            .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300).setKR(1));
        LightSource pointLight = new PointLight(new Color(255,255,255), new Point(-10,-10,10));
        LightSource directionalLight = new DirectionalLight(new Color(10,10,10), new Vector(1,-1,-0.5));
        scene.geometries.add(plane1);
        scene.geometries.add(sphere1);
        scene.geometries.add(sphere2);
        scene.geometries.add(plane2);
        scene.lights.add(pointLight);
        scene.lights.add(directionalLight);
        ImageWriter imageWriter = new ImageWriter("reflectionRefractionTest", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage(); //
	}
}