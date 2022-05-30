package integrationTests;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.LightBulb;
import primitives.*;
import renderer.*;
import scene.*;

public class MultiThreadingTests {
    @Test
    void FindBestThreadCountTest(){
        long startTime = 0;
        long endTime = 0;
        for (int i = 1; i < 20; i++){
            System.out.println("ThreadsCount: " + i);
            if (i == 2){
                System.out.println("wait");
            }
            startTime = System.currentTimeMillis();
            //////////////////////////////////////////////
            Scene scene = new Scene("Test scene"+i);
            Material trMaterial = new Material().setKD(0.5).setKS(0.5).setNShininess(30);
		    Camera camera = new Camera(new Point(-30, 500, 500), new Vector(0, -1, 0), new Vector(0, 0, 1)) //
			.setWidthAndHeight(200, 200).setDistance(1000) //
			.setRayTracer(new RayTracerBasic(scene));
		        camera.turnUp(-45);
		    Geometry sphere = new Sphere(new Point(0,0,21),20);
		    Geometry plane1 = new Plane(new Point(0,0,0),new Vector(0,0,1));
            scene.geometries.add(plane1.setMaterial(trMaterial), sphere.setEmission(new Color(6,3,17)).setMaterial(trMaterial));
		    scene.lights.add( //
				new LightBulb(new Color(300, 300, 300), new Point(100, 0, 100), 30) //
						.setKL(1E-5).setKQ(1.5E-7));
		    camera.setImageWriter(new ImageWriter("MultiThreading/softShadowSphereTest"+i, 500, 500)) //
				.renderImage()//
				.writeToImage();
            //////////////////////////////////////////////
            endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        }
    }
}
