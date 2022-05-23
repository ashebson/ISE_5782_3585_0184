package integrationTests;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

public class SoftShadowsTests {		
	private Material trMaterial = new Material().setKD(0.5).setKS(0.5).setNShininess(30);
	private Scene scene = new Scene("Test scene");
	
	@Test
	public void softShadowSphereTest() {
		Camera camera1 = new Camera(new Point(-30, 500, 500), new Vector(0, -1, 0), new Vector(0, 0, 1)) //
			.setWidthAndHeight(200, 200).setDistance(1000) //
			.setRayTracer(new RayTracerBasic(scene));
		camera1.turnUp(-45);
		Geometry sphere = new Sphere(new Point(0,0,21),20);
		Geometry plane1 = new Plane(new Point(0,0,0),new Vector(0,0,1));
        scene.geometries.add(plane1.setMaterial(trMaterial), sphere.setEmission(new Color(6,3,17)).setMaterial(trMaterial));
		scene.lights.add( //
				new LightBulb(new Color(300, 300, 300), new Point(100, 0, 100), 30) //
						.setKL(1E-5).setKQ(1.5E-7));
		camera1.setImageWriter(new ImageWriter("softShadowSphereTest", 500, 500)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setWidthAndHeight(200, 200).setDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(30).setKT(0.6)));

		scene.lights.add(new LightBulb(new Color(200, 200, 200), new Point(60, 50, 0),20) //
				.setKL(4E-5).setKQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("softSadowrefractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage();
	}
}
