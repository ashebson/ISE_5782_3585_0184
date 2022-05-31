package geometries;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import parser.Parser;
import primitives.*;

/**
 * Intersectable Interface
 */
public class Geometries extends Intersectable {
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
        generateBox();
    }

    /**
     * constructor with a element
     * @param element
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     */
    public Geometries(Element geometriesElement) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        geometries = new ArrayList<Intersectable>();
        NodeList elements = geometriesElement.getChildNodes();
        for (int i = 0; i < elements.getLength(); i++){
            if (elements.item(i) instanceof Element){
                Element element = (Element) elements.item(i);
                geometries.add((Intersectable)Parser.parseObject(element));
            }
        }
        generateBox();
    }

    /**
     * add a geometry to the list
     * @param geometry
     */
    public void add(Intersectable... g){
        for (Intersectable geometry : g)
            this.geometries.add(geometry);
        generateBox();
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
    
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = new LinkedList<GeoPoint>();
        List<GeoPoint> intersectableIntersections;
        for (Intersectable intersectable : geometries) {
            intersectableIntersections = intersectable.findGeoIntersections(ray,maxDistance);
            if (intersectableIntersections != null) {
                intersections.addAll(intersectableIntersections);
            }
        }
        if(intersections.size() == 0)
            return null;
        return intersections;
    }

    @Override
    protected void generateBox() {
        List<Double> minXs = new ArrayList<Double>();
        List<Double> minYs = new ArrayList<Double>();
        List<Double> minZs = new ArrayList<Double>();
        List<Double> maxXs = new ArrayList<Double>();
        List<Double> maxYs = new ArrayList<Double>();
        List<Double> maxZs = new ArrayList<Double>();
        for (var geometry: geometries){
            Box box = geometry.getBox();
            if (box == null){
                setBox(null);
                return;
            }
            Point min = box.getMin();
            Point max = box.getMax();
            minXs.add(min.getX());
            minYs.add(min.getY());
            minZs.add(min.getZ());
            maxXs.add(max.getX());
            maxYs.add(max.getY());
            maxZs.add(max.getZ());
        }
        double maxX = Collections.max(maxXs);
        double maxY = Collections.max(maxYs);
        double maxZ = Collections.max(maxZs);
        double minX = Collections.min(minXs);
        double minY = Collections.min(minYs);
        double minZ = Collections.min(minZs);
        Point min = new Point(minX,minY,minZ);
        Point max = new Point(maxX,maxY,maxZ);
        setBox(new Box(min,max));
    }

    public Geometries[] split(int count){
        Geometries[] ret = new Geometries[count+1];
        List<Intersectable> sub = new ArrayList<>();
        int chunk = geometries.size()/count;
        for (int i = 0; i < count; i++){
            sub = geometries.subList(i*chunk, (i+1)*chunk);
            ret[i] = new Geometries(sub.toArray(new Intersectable[0]));
        }
        sub = geometries.subList(chunk*count,geometries.size());
        ret[count] = new Geometries(sub.toArray(new Intersectable[0]));
        return ret;
    }
}
