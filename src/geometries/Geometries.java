package geometries;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import primitives.*;

/**
 * Intersectable Interface
 */
public class Geometries implements Intersectable {
    private List<Intersectable> geometries;

    /**
     * default constructor
     */
    public Geometries(){
        geometries = new LinkedList<Intersectable>();
    }

    /**
     * constructor with a list of geometries
     * @param geometries
     */
    public Geometries(Intersectable... geometries){
        this.geometries = new LinkedList<Intersectable>();
        for (Intersectable g : geometries)
            this.geometries.add(g);
    }

    /**
     * constructor with a element
     * @param element
     */
    public Geometries(Element geometriesElement){
        geometries = new ArrayList<Intersectable>();
        NodeList elements = geometriesElement.getChildNodes();
        for (int i = 0; i < elements.getLength(); i++){
            if (elements.item(i) instanceof Element){
                Element element = (Element) elements.item(i);
                switch(element.getNodeName()){
                    case "sphere":
                        geometries.add(new Sphere(element));
                        break;
                    case "triangle":
                        geometries.add(new Triangle(element));
                        break;
                    default:
                        break;
                }
            }
            
        }
    }

    /**
     * add a geometry to the list
     * @param geometry
     */
    public void add(Intersectable... g){
        for (Intersectable geometry : g)
            this.geometries.add(geometry);
    }
    

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new LinkedList<Point>();
        List<Point> intersectableIntersections;
        for (Intersectable intersectable : geometries) {
            intersectableIntersections = intersectable.findIntersections(ray);
            if (intersectableIntersections != null) {
                intersections.addAll(intersectableIntersections);
            }
        }
        if(intersections.size() == 0)
            return null;
        return intersections;
    }
    
}
