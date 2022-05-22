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

import javax.swing.event.CaretEvent;

public class VideoTests {
    
    @Test
	public void sphereDirectionalVideo() {
		Scene scene = new Scene("Test scene");
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setWidthAndHeight(150, 150) //
			.setDistance(1000);
		Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
		Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
			.setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));
<<<<<<< HEAD
    private Geometry cylinder = new Cylinder(new Ray(new Point(0, 0, -50), new Vector(1, 1, 1)), 50d, 60d)
            .setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));
    @Test
	public void sphereDirectional() {
=======
>>>>>>> a4eab67f7ccadc3dcf6d1737acf2282ef7dafab5
        Color colorDelta = new Color(BLACK);
        Vector vectorDelta = new Vector(-(2.0/60), -(2.0/60), 0);
        Intersectable[] geos = {sphere};
        for(int i = 0; i < 60; i++){
			camera.turnUp(0.02);
            scene = new Scene("Test scene");
            scene.geometries.add(geos);
            Vector propotionalVectorDelta = new Vector(1,1,1);
            try{
                propotionalVectorDelta = vectorDelta.scale(1);
            }catch (IllegalArgumentException e){
                propotionalVectorDelta = vectorDelta;
            }
            scene.lights.add(new DirectionalLight(spCL.add(colorDelta), new Vector(1, 1, -0.5).add(propotionalVectorDelta)));
            
<<<<<<< HEAD
            ImageWriter imageWriter = new ImageWriter("sphereVideo\\videoFrameSphereDirectional"+i, 500, 500);
		    camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
=======
            ImageWriter imageWriter = new ImageWriter("sphereVideo/videoFrameSphereDirectional"+i, 500, 500);
		    camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
>>>>>>> a4eab67f7ccadc3dcf6d1737acf2282ef7dafab5
				.renderImage() //
				.writeToImage(); //
        }
	}

	@Test
	public void superMegaAwesomeVideoTest(){
		Camera camera = new Camera(new Point(-100, -100, 0), new Vector(1, 1, 0), new Vector(0, 0, 1)) //
                .setWidthAndHeight(150, 150) //
                .setDistance(1000);
		Scene scene = new Scene("Test scene");
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
		camera.pivot(180);
		camera.moveTo(-180);
		for (int i = 0; i < 180; i++){
			camera.moveTo(1);
			camera.pivot(1);
			ImageWriter imageWriter = new ImageWriter("superMegaAwesomeVideo/SuperMegaAwesomeFrame"+i, 500, 500);
			camera.setImageWriter(imageWriter) //
					.setRayTracer(new RayTracerBasic(scene)) //
					.renderImage() //
					.writeToImage(); //
		}
	}

    //@Test
	// public void cylinderDirectional() {
    //     Color colorDelta = new Color(BLACK);
    //     Vector vectorDelta = new Vector(-(2.0/60), -(2.0/60), 0);
    //     Intersectable[] geos = {cylinder};
    //     for(int i = 0; i < 30; i++){
    //         scene1 = new Scene("Test scene");
    //         scene1.geometries.add(geos);
    //         Vector propotionalVectorDelta = new Vector(1,1,1);
    //         try{
    //             propotionalVectorDelta = vectorDelta.scale(i);
    //         }catch (IllegalArgumentException e){
    //             propotionalVectorDelta = vectorDelta;
    //         }
    //         scene1.lights.add(new DirectionalLight(spCL.add(colorDelta), new Vector(1, 1, -0.5).add(propotionalVectorDelta)));
            
    //         ImageWriter imageWriter = new ImageWriter("videoFrameCylinderDirectional"+i, 500, 500);
	// 	    camera1.setImageWriter(imageWriter) //
	// 			.setRayTracer(new RayTracerBasic(scene1)) //
	// 			.renderImage() //
	// 			.writeToImage(); //
    //     }
	// }
}
