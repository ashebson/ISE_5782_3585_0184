// package lighting;

// import java.util.ArrayList;
// import java.util.LinkedList;
// import java.util.List;

// import geometries.Cylinder;
// import primitives.Color;
// import primitives.Point;
// import primitives.Ray;
// import primitives.Vector;

// public class LightSaber extends Light implements LightSource {
//     private Cylinder cylinder;
//     private List<PointLight> lights = new LinkedList<PointLight>();
//     private static final int POINT_LIGHT_DENSITY = 10;
//     protected LightSaber(Color intensity, Cylinder cylinder) {
//         super(intensity);
//         this.cylinder = cylinder;
//         Ray ray = cylinder.getAxisRay();
//         for (int i = 0; i < POINT_LIGHT_DENSITY; i++) {
//             lights.add(new PointLight(intensity, ray.getPoint(cylinder.getHeight() / POINT_LIGHT_DENSITY * (i+0.5))));
//         }
//     }

//     @Override
//     public Color getIntensity(Point p) {
//         Color Ils = Color.BLACK;
//         for (var light: lights){
//             Ils = Ils.add(light.getIntensity(p));
//         }
//         return Ils.reduce(POINT_LIGHT_DENSITY);
//     }

//     @Override
//     public Vector getL(Point p) {
//         return lights.get(POINT_LIGHT_DENSITY/2).getL(p);
//     }

//     @Override
//     public List<Vector> getLs(Point p, int maxAmountOfShadowRays) {
//         List<Vector> ls = new LinkedList<Vector>();
//         for (var light: lights){
//             ls.addAll(light.getLs(p, maxAmountOfShadowRays));
//         }
//         return ls;
//     }

//     @Override
//     public double getDistance(Point p) {
//         Point middle = cylinder.getAxisRay().getPoint(cylinder.getHeight() / 2);
//         return Distance
//     }
    
// }
