package geometries;
import java.util.LinkedList;
import java.util.List;

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
     * add a geometry to the list
     * @param geometry
     */
    public void add(Intersectable... g){
        for (Intersectable geometry : g)
            this.geometries.add(geometry);
    }
    

    @Override
    public List<Point> findIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }
    
}