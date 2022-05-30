package unittests.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import parser.Parser;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class ParserTests {
    @Test
    void testParseDouble3() {
        Double3 x = Parser.parseDouble3("1 2 3");
        assertEquals(x, new Double3(1,2,3));
    }

    @Test
    void testParseScene() throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Scene scene = Parser.parseXml("/Users/aryehshebson/Desktop/Degree/Minip SE/Project/ISE_5782_3585_0184/xml/test1.xml");
        Camera camera = new Camera(new Point(0, 0, 2000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setWidthAndHeight(150, 150) //
			.setDistance(1000);
        ImageWriter imageWriter = new ImageWriter("xmlParserTest", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage(); //
    }
}
