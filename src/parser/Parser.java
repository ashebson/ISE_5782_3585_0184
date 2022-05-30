package parser;

import java.io.File;
import java.io.IOException;
import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

public class Parser {
    public static Scene parseXml(String fileName) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        File file = new File(fileName);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(file);
        Element sceneElement = doc.getDocumentElement();
        Scene scene = new Scene(sceneElement);
        return scene;
    }

    public static Object parseObject(Element element) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        Class cls = Class.forName(element.getNodeName());
        Constructor constructor = cls.getConstructor(Element.class);
        return constructor.newInstance(element);
    }

    // public static Class[] getAttributeTypes(Element element){
    //     NamedNodeMap attributes = element.getAttributes();
    //     List<String> attributesValues = new ArrayList<>();
    //     List<Class> attributesTypes = new ArrayList<>();
    //     for (int i = 0; i < attributes.getLength(); i++) {
    //         attributesValues.add(attributes.item(i).getNodeValue());
    //     }
    //     for (String attributeValue : attributesValues) {
    //         try{
    //             parseDouble3(attributeValue);
    //             attributesTypes.add(Double3.class);
    //         }catch (IndexOutOfBoundsException | NumberFormatException e){}
    //     }
    //     return new Class[]{int.class};
    // }

    public static Double3 parseDouble3(String string) {
        String[] indecies = string.split(" ");
        double x = Double.parseDouble(indecies[0]);
        double y = Double.parseDouble(indecies[1]);
        double z = Double.parseDouble(indecies[2]);
        return new Double3(x, y, z);
    }
}
