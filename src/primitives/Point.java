package primitives;

public class Point {
    final protected Double3 xyz;

    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    public Vector subtract(Point p) {
        Double3 sub = xyz.subtract(p.xyz);
        return new Vector(sub.d1,sub.d2,sub.d3);
    }

    public Point add(Vector v) {
        Double3 sum = xyz.add(v.xyz);
        return new Point(sum.d1,sum.d2,sum.d3);
    }

    public double DistanceSquared(Point p) {
        return this.xyz.d1 * this.xyz.d1 + this.xyz.d2 * this.xyz.d2
                + this.xyz.d3 * this.xyz.d3;
    }
    public double Distance(Point p){
        return Math.sqrt(DistanceSquared(p));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((xyz == null) ? 0 : xyz.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        if (xyz == null) {
            if (other.xyz != null)
                return false;
        } else if (!xyz.equals(other.xyz))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "• "+ xyz;
    }
}
