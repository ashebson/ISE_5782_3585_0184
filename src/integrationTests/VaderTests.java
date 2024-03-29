package integrationTests;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import lighting.*;
import parser.Parser;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;
import static java.awt.Color.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

public class VaderTests {
        @Test
        void vaderVideoTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
                        InstantiationException, IllegalAccessException, IllegalArgumentException,
                        InvocationTargetException, ParserConfigurationException, SAXException, IOException {
                Camera camera = new Camera(new Point(0, -500, 25), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
                                .setWidthAndHeight(200, 200) //
                                .setDistance(1000);
                Scene scene;
                Geometries[] split_triangles = loadVader();
                Vector delta = new Vector(-2.0 / 60, 0, 0);
                Color DLColor = new Color(256, 0, 0);
                Vector DLVector = new Vector(1, 1, 1);
                ImageWriter imageWriter;
                for (int i = 0; i < 60; i++) {
                        scene = new Scene("my scene");
                        scene.geometries.add(split_triangles);
                        if (i == 0) {
                                scene.lights.add(new DirectionalLight(DLColor, DLVector));
                        } else {
                                scene.lights.add(new DirectionalLight(DLColor, DLVector.add(delta.scale(i))));
                        }
                        imageWriter = new ImageWriter("vaderVideo/vaderVideoTestFrame" + i, 1000, 1000);
                        camera.setImageWriter(imageWriter) //
                                        .setRayTracer(new RayTracerBasic(scene)) //
                                        .renderImage() //
                                        .writeToImage(); //
                }
        }

        @Test
        void lightSaberOpenningVideoTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
                        InstantiationException, IllegalAccessException, IllegalArgumentException,
                        InvocationTargetException, ParserConfigurationException, SAXException, IOException {
                Camera camera = new Camera(new Point(0, -500, 25), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
                                .setWidthAndHeight(200, 200) //
                                .setDistance(1000);
                camera.moveUp(60);
                camera.turnUp(-8);
                camera.moveRight(5);
                camera.moveUp(5);
                Scene scene;
                Geometries[] split_triangles = loadVader();
                Geometry plane = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1))
                                .setEmission(new Color(3, 3, 10))
                                .setMaterial(
                                                new Material()
                                                                .setKD(0.3)
                                                                .setKS(0.9)
                                                                .setNShininess(300));
                for (int frame = 0; frame < 24; frame++) {
                        scene = new Scene("Test scene");
                        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.01));
                        scene.geometries.add(split_triangles);
                        scene.geometries.add(plane);
                        scene.lights.add(new DirectionalLight(new Color(50, 50, 50), new Vector(0, 1, -0.7)));
                        // Point lightPoint = new Point(40,-40,100);
                        // scene.lights.add(new LightBulb(trCL, lightPoint,10));
                        // scene.lights.add(new PointLight(trCL, lightPoint));

                        Vector lightsaberVector = new Vector(1.5, -0.8, 1).normalize();

                        addLightsaber(
                                        scene,
                                        new Point(-30, -30, 0).add(lightsaberVector.scale(-4)),
                                        lightsaberVector,
                                        66d / 24 * frame,
                                        new Color(java.awt.Color.RED).reduce(2.5),
                                        new Color(1100, 0, 0),
                                        100);

                        int QUALITY = 500;
                        ImageWriter imageWriter = new ImageWriter("vaderVideo2/vaderVideoFrame" + frame, QUALITY,
                                        QUALITY);
                        camera.setImageWriter(imageWriter) //
                                        .setRayTracer(new RayTracerBasic(scene)) //
                                        .renderImage() //
                                        .writeToImage(); //
                }
        }

        @Test
        void lightSaberCuttingVideoTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
                        InstantiationException, IllegalAccessException, IllegalArgumentException,
                        InvocationTargetException, ParserConfigurationException, SAXException, IOException {
                Camera camera = new Camera(new Point(0, -500, 25), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
                                .setWidthAndHeight(200, 200) //
                                .setDistance(1000);
                camera.moveUp(60);
                camera.turnUp(-8);
                camera.moveRight(5);
                camera.moveUp(5);
                Scene scene;
                Geometries[] split_triangles = loadVader();
                Geometry plane = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1))
                                .setEmission(new Color(3, 3, 10))
                                .setMaterial(
                                                new Material()
                                                                .setKD(0.3)
                                                                .setKS(0.9)
                                                                .setNShininess(300));
                int n = 30;
                int s = 24;
                int i;
                for (int frame = s; frame < s + n; frame++) {
                        i = frame - s;
                        scene = new Scene("Test scene");
                        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.01));
                        scene.geometries.add(split_triangles);
                        scene.geometries.add(plane);
                        scene.lights.add(new DirectionalLight(new Color(50, 50, 50), new Vector(0, 1, -0.7)));
                        // Point lightPoint = new Point(40,-40,100);
                        // scene.lights.add(new LightBulb(trCL, lightPoint,10));
                        // scene.lights.add(new PointLight(trCL, lightPoint));

                        Vector lightsaberVector = new Vector(1.5 + (-3.0 / n * (i)), -0.8, 1).normalize();
                        addLightsaber(
                                        scene,
                                        new Point(-30 + (75.0 / n * (i)),
                                                        -30 + (-40.0 * 2 / n * ((i) < n / 2 ? i : n - (i))), 0)
                                                        .add(lightsaberVector.scale(-4)),
                                        lightsaberVector,
                                        66.0,
                                        new Color(java.awt.Color.RED).reduce(2.5),
                                        new Color(1100, 0, 0),
                                        100);

                        int QUALITY = 500;
                        ImageWriter imageWriter = new ImageWriter("vaderVideo2/vaderVideoFrame" + frame, QUALITY,
                                        QUALITY);
                        camera.setImageWriter(imageWriter) //
                                        .setRayTracer(new RayTracerBasic(scene)) //
                                        .renderImage() //
                                        .writeToImage(); //
                }
        }

        @Test
        void vader1picture() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
                        InstantiationException, IllegalAccessException, IllegalArgumentException,
                        InvocationTargetException, ParserConfigurationException, SAXException, IOException {
                Camera camera = new Camera(new Point(0, -500, 25), new Vector(0, 1, 0), new Vector(0, 0, 1)) //
                                .setWidthAndHeight(200, 200) //
                                .setDistance(1000);
                camera.moveUp(60);
                camera.turnUp(-8);
                camera.moveRight(5);
                camera.moveUp(5);
                Scene scene;
                Geometries[] split_triangles = loadVader();
                Geometry plane = new Plane(new Point(0, 0, -5), new Vector(0, 0, 1))
                                .setEmission(new Color(3, 3, 10))
                                .setMaterial(
                                                new Material()
                                                                .setKD(0.3)
                                                                .setKS(0.9)
                                                                .setNShininess(300));

                scene = new Scene("Test scene");
                scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.01));
                scene.geometries.add(split_triangles);
                scene.geometries.add(plane);
                scene.lights.add(new DirectionalLight(new Color(50, 50, 50), new Vector(0, 1, -0.7)));
                // Point lightPoint = new Point(40,-40,100);
                // scene.lights.add(new LightBulb(trCL, lightPoint,10));
                // scene.lights.add(new PointLight(trCL, lightPoint));

                Vector lightsaberVector = new Vector(1.5, -0.8, 1).normalize();

                addLightsaber(
                                scene,
                                new Point(-30, -30, 0).add(lightsaberVector.scale(-4)).add(new Vector(0, -20, 0)),
                                new Vector(1.5, -0.2, 1).normalize(),
                                66,
                                new Color(java.awt.Color.RED).reduce(2),
                                new Color(1100, 0, 0),
                                30);

                addLightsaber(
                                scene,
                                new Point(-30, -30, 0).add(lightsaberVector.scale(-4).add(new Vector(75, -50, 0))),
                                new Vector(-1.5, 0.2, 1).normalize(),
                                66,
                                // new Color(java.awt.Color.GREEN).reduce(2),
                                // new Color(0, 1100, 0),
                                new Color(0, 204, 255).reduce(2),
                                new Color(0, 300, 800),
                                30);
                int QUALITY = 2000;
                ImageWriter imageWriter = new ImageWriter("vaderPictureB", QUALITY,
                                QUALITY);
                camera.setImageWriter(imageWriter) //
                                .setRayTracer(new RayTracerBasic(scene)) //
                                .renderImage() //
                                .writeToImage(); //

        }

        private Geometries[] loadVader() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
                        InstantiationException, IllegalAccessException, IllegalArgumentException,
                        InvocationTargetException, ParserConfigurationException, SAXException, IOException {
                Scene scene = Parser.parseXml(
                                Paths.get(System.getProperty("user.dir"), "formatted_vader_2.xml").toString());
                Geometries triangles = scene.geometries;
                return triangles.split(128);
        }

        private void addLightsaber(Scene scene, Point lightsaberPointStart, Vector lightsaberVector,
                        double lightsaberLength, Color glassColor, Color lightIntesity, int n) {
                Point lightsaberPoint = lightsaberPointStart.add(lightsaberVector.scale(22));
                if (lightsaberLength != 0) {
                        Geometry lightsaberFlame = new Cylinder(new Ray(lightsaberPoint, lightsaberVector), 2.5d,
                                        lightsaberLength)
                                        .setEmission(glassColor)
                                        .setMaterial(new Material()
                                                        .setKD(0.2)
                                                        .setKS(0.8)
                                                        .setNShininess(60)
                                                        .setKT(0.7));
                        scene.geometries.add(lightsaberFlame);
                        for (int i = 0; i < n; i++) {
                                scene.lights.add(new PointLight(
                                                lightIntesity.reduce(n),
                                                i == 0 ? lightsaberPoint
                                                                : lightsaberPoint.add(lightsaberVector
                                                                                .scale(i * lightsaberLength / n - 1)))
                                                .setKL(0.02).setKQ(0.00001));
                        }
                }

                //
                // ***********************Lightsaber Handel***********************
                //

                Geometry lightsaberHandle1 = new Cylinder(new Ray(lightsaberPoint, lightsaberVector.scale(-1)), 2.8d,
                                3d)
                                .setEmission(Color.BLACK)
                                .setMaterial(new Material()
                                                .setKD(0.32)
                                                .setKS(0.82)
                                                .setNShininess(60)
                                                .setKR(0.4));
                Geometry lightsaberHandle2 = new Cylinder(
                                new Ray(lightsaberPoint.add(lightsaberVector.scale(-3)), lightsaberVector.scale(-1)),
                                3d, 1.5d)
                                .setEmission(Color.BLACK)
                                .setMaterial(new Material()
                                                .setKD(0.29)
                                                .setKS(0.79)
                                                .setNShininess(60)
                                                .setKR(0.35));
                Geometry lightsaberHandle3 = new Cylinder(
                                new Ray(lightsaberPoint.add(lightsaberVector.scale(-4.5)), lightsaberVector.scale(-1)),
                                2.4d, 5.5d)
                                .setEmission(new Color(40, 40, 40))
                                .setMaterial(new Material()
                                                .setKD(0.91)
                                                .setKS(0.43)
                                                .setNShininess(20)
                                                .setKR(0.84));
                Geometry lightsaberHandle4 = new Cylinder(
                                new Ray(lightsaberPoint.add(lightsaberVector.scale(-7)), lightsaberVector.scale(-1)),
                                2.6d, 1d)
                                .setEmission(new Color(40, 40, 40))
                                .setMaterial(new Material()
                                                .setKD(0.8)
                                                .setKS(0.5)
                                                .setNShininess(20)
                                                .setKR(0.88));
                Geometry lightsaberHandle5 = new Cylinder(
                                new Ray(lightsaberPoint.add(lightsaberVector.scale(-10)), lightsaberVector.scale(-1)),
                                2.75d, 4d)
                                .setEmission(Color.BLACK)
                                .setMaterial(new Material()
                                                .setKD(0.4)
                                                .setKS(0.76)
                                                .setNShininess(20)
                                                .setKR(0.42));
                Geometry lightsaberHandle6 = new Cylinder(
                                new Ray(lightsaberPoint.add(lightsaberVector.scale(-14)), lightsaberVector.scale(-1)),
                                3d, 7d)
                                .setEmission(Color.BLACK)
                                .setMaterial(new Material()
                                                .setKD(0.5)
                                                .setKS(0.9)
                                                .setNShininess(20)
                                                .setKR(0.87));
                Geometry lightsaberHandle7 = new Cylinder(
                                new Ray(lightsaberPoint.add(lightsaberVector.scale(-21)), lightsaberVector.scale(-1)),
                                2.6d, 1d)
                                .setEmission(new Color(30, 30, 30))
                                .setMaterial(new Material()
                                                .setKD(0.85)
                                                .setKS(0.34)
                                                .setNShininess(20)
                                                .setKR(0.9));
                Intersectable Lightsaber = new Geometries(
                                lightsaberHandle1,
                                lightsaberHandle2,
                                lightsaberHandle3,
                                lightsaberHandle4,
                                lightsaberHandle5,
                                lightsaberHandle6,
                                lightsaberHandle7);

                scene.geometries.add(Lightsaber);
        }
}
