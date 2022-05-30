package integrationTests;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class LightsTests {
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

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

		ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new PointLight(spCL, spPL).setKL(0.001).setKQ(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		scene1.geometries.add(sphere);
		scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setKL(0.001).setKQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new DirectionalLight(trCL, trDL));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new PointLight(trCL, trPL).setKL(0.001).setKQ(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, trPL, trDL).setKL(0.001).setKQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a narrow spot light
	 */
	@Test
	public void sphereSpotSharp() {
		scene1.geometries.add(sphere);
		scene1.lights
			.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setKN(6).setKL(0.001).setKQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a narrow spot light
	 */
	@Test
	public void trianglesSpotSharp() {
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, trPL, trDL).setKN(7).setKL(0.001).setKQ(0.00004));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	@Test 
	void sphereMultiLightsTest(){
		scene1.geometries.add(sphere);
		Point p1 = new Point(-50, -50, 25); 
		Point p2 = new Point(-50, -40, 25); 
		Point p3 = new Point(-50, -50, 35); 
		scene1.lights.add(new PointLight(spCL, p1).setKL(0.001).setKQ(0.0002));
		scene1.lights.add(new SpotLight(spCL, p2, new Vector(1, 1, -0.5)).setKN(6).setKL(0.001).setKQ(0.0001));
		scene1.lights.add(new DirectionalLight(spCL, new Vector(-1, -1, 0.5)));
		scene1.lights.add(new SpotLight(spCL, p3, new Vector(1, 1, -0.5)).setKL(0.001).setKQ(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightSphereMulti", 500, 500);
		camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	@Test 
	void triangleMultiLightsTest(){
		Point p1 = new Point(30, 10, -100);
		Point p2 = new Point(20, 10, -100);
		Point p3 = new Point(30, 10, -90);
		scene2.geometries.add(triangle1, triangle2);
		scene2.lights.add(new SpotLight(trCL, p1, trDL).setKL(0.001).setKQ(0.0001));
		scene2.lights.add(new SpotLight(trCL, p2, trDL).setKN(7).setKL(0.001).setKQ(0.00004));
		scene2.lights.add(new PointLight(trCL, p3).setKL(0.001).setKQ(0.0002));
		scene2.lights.add(new DirectionalLight(trCL, trDL));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesMulti", 500, 500);
		camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	@Test
    public void cylinderLightTest(){
        // TC01: small cylinder (1 point)
        Geometry cylinder = new Cylinder(
                new Ray(new Point(0,0,2), new Vector(-0.5,0.7,1)), 
                1,
                3).setMaterial(
					new Material()
					.setKD(0.8)
					.setKS(0.5)
					.setNShininess(20)
				).setEmission(
					new Color(java.awt.Color.BLUE)
				);

		Geometry plane = new Plane(new Point(0,0,0), new Vector(0,0,1)).setMaterial(
			new Material()
			.setKD(0.8)
			.setKS(0.5)
			.setNShininess(20)
		);
		Scene scene = new Scene("lightCylinderTest")
			.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
		Camera camera = new Camera(new Point(100,0,100), new Vector(-1,0,0), new Vector(0,0,1))
			.setWidthAndHeight(150, 150) //
			.setDistance(1000)
			.turnUp(-45)
			.moveTo(40);
		LightBulb lb = new LightBulb(new Color(100,100,100), new Point(0,10,10), 1);
		scene.geometries.add(cylinder);
		scene.geometries.add(plane);
		scene.lights.add(lb);
		ImageWriter imageWriter = new ImageWriter("lightCylinder", 2000, 2000);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() //
				.writeToImage(); //
    }
}