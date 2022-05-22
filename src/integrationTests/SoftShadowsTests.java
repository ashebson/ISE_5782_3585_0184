package integrationTests;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

public class SoftShadowsTests {
    private Intersectable plane = new Plane(new Point(0,0,-200), new Vector(0.01,0,1))
            .setEmission(new Color(BLUE)) //
			.setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(30));			
	private Material trMaterial = new Material().setKD(0.5).setKS(0.5).setNShininess(30);

	private Scene scene = new Scene("Test scene");
	private Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setWidthAndHeight(200, 200).setDistance(1000) //
			.setRayTracer(new RayTracerBasic(scene));


	@Test
	public void softShadowSphereTest(){  
        Geometry sphere = new Sphere(new Point(-38,-38,-1),15);
        scene.geometries.add(plane, sphere.setEmission(new Color(BLUE)).setMaterial(trMaterial));
		scene.lights.add( //
				new LightBulb(new Color(400, 240, 0), new Point(-100, -100, 200), 40) //
						.setKL(1E-5).setKQ(1.5E-7));
		camera.setImageWriter(new ImageWriter("softShadowSphereTest", 800, 800)) //
				.renderImage() //
				.writeToImage();
	}

    /**
	 * Helper function for the tests in this module
	 */
	void sphereTriangleHelper(String pictName, Triangle triangle, Point spotLocation) {
        Intersectable sphere = new Sphere(new Point(0, 0, -200), 60d) //
			.setEmission(new Color(BLUE)) //
			.setMaterial(new Material().setKD(0.5).setKS(0).setNShininess(30));
		scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
		scene.lights.add( //
        new LightBulb(new Color(400, 240, 0), new Point(-100, -100, 200), 15) //
						.setKL(1E-5).setKQ(1.5E-7));
		camera.setImageWriter(new ImageWriter(pictName, 500, 500)) //
				.renderImage() //
				.writeToImage();
	}

    @Test
	public void softShadowSphereTriangleTest() {
		sphereTriangleHelper("softShadowSphereTriangleTest", //
				new Triangle(new Point(-49, -19, 0), new Point(-19, -49, 0), new Point(-47, -47, -4)), //
				new Point(-100, -100, 200)); 
	}
}
