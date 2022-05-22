package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point> vertices;
	/**
	 * Associated plane in which the polygon lays
	 */
	protected Plane plane;
	private int size;

	/**
	 * Polygon constructor based on vertices list. The list must be ordered by edge
	 * path. The polygon must be convex.
	 * 
	 * @param vertices list of vertices according to their order by edge path
	 * @throws IllegalArgumentException in any case of illegal combination of
	 *                                  vertices:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consequent vertices are in the same
	 *                                  point
	 *                                  <li>The vertices are not in the same
	 *                                  plane</li>
	 *                                  <li>The order of vertices is not according
	 *                                  to edge path</li>
	 *                                  <li>Three consequent vertices lay in the
	 *                                  same line (180&#176; angle between two
	 *                                  consequent edges)
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lay in the same
		// line.
		// Generate the direction of the polygon according to the angle between last and
		// first edge being less than 180 deg. It is hold by the sign of its dot product
		// with
		// the normal. If all the rest consequent edges will generate the same sign -
		// the
		// polygon is convex ("kamur" in Hebrew).
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
			// Test the consequent edges have
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
		size = vertices.length;
	}

	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	@Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){
        List<Point> planeIntersections = plane.findIntersections(ray);
        if (planeIntersections == null)
            return null;
        Point p = planeIntersections.get(0);
		List<Point> ps = vertices;
		Point p0 = ray.getP0();
		Vector v = ray.getDir();
		List<Vector> vs = new ArrayList<Vector>();
		for (var point : ps) {
			vs.add(point.subtract(p0));
		}
		List<Vector> ns = new ArrayList<Vector>();
		for (int i = 0; i < vs.size(); i++) {
			Vector vi = vs.get(i);
			Vector vi1 = vs.get((i + 1) % vs.size());
			ns.add(vi.crossProduct(vi1));
		}
		List<Double> vns = new ArrayList<Double>();
		for (var normal : ns) {
			vns.add(normal.dotProduct(v));
		}
		// check no product is zero
		for (var vn : vns) {
			if (Util.isZero(vn))
				return null;
		}
		// check all products have the same sign as v1
		for (var vn : vns) {
			if (vn > 0 != vns.get(0) > 0)
				return null;
		}
		GeoPoint intersection = new GeoPoint(this, p);
		double distance = ray.getP0().Distance(intersection.point);
		if (maxDistance == Double.POSITIVE_INFINITY){
			return List.of(intersection);
		}
		if (Util.alignZero(distance - maxDistance) > 0){
			return null;
		}
        return List.of(intersection);
    }
}
