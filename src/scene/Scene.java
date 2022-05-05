package scene;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import geometries.*;
import primitives.*;
import lighting.*;

public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<LightSource>();

    /**
     * Constructor
     * @param name
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Constructor
     * @param file
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    // public Scene(String name, File file) throws ParserConfigurationException, SAXException, IOException{
    //     this.name = name;
    //     DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    //     DocumentBuilder builder = factory.newDocumentBuilder();
    //     Document doc = builder.parse(file);
    //     doc.getDocumentElement().normalize();
    //     Element rootElement = doc.getDocumentElement();
    //     Element ambientLightElement = (Element) rootElement.getElementsByTagName("ambient-light").item(0);
    //     Element geometriesElement = (Element) rootElement.getElementsByTagName("geometries").item(0);
    //     background = new Color(rootElement.getAttribute("background-color"));
    //     ambientLight = new AmbientLight(ambientLightElement);
    //     geometries = new Geometries(geometriesElement);
    // }

    /**
     * setter for background
     * @param background
     * @return this
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter for ambient light
     * @param ambientLight
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * setter for geometries
     * @param geometries
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * setter for lights
     * @param lights
     * @return this
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
