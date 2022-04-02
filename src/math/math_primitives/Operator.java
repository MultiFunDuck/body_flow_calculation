package math.math_primitives;

import java.util.List;

public class Operator {

    private static Operator instance;

    public static synchronized Operator getInstance(){
        if(instance == null){
            instance = new Operator();
        }
        return instance;
    }

    public Point sum(Point p1, Point p2){
        return new Point(p1.x + p2.x,p1.y + p2.y,p1.z + p2.z);
    }

    public Point diff(Point p1, Point p2){
        return new Point(p1.x - p2.x,p1.y - p2.y,p1.z - p2.z);
    }

    public Point mul(Point p1, double c){
        return new Point(p1.x * c,p1.y * c,p1.z * c);
    }

    public Point div(Point p1, double c){
        return new Point(p1.x / c,p1.y / c,p1.z / c);
    }




    public Vector sum(Vector v1, Vector v2){
        return new Vector(v1.x + v2.x,v1.y + v2.y,v1.z + v2.z);
    }

    public Vector diff(Vector v1, Vector v2){
        return new Vector(v1.x + v2.x,v1.y + v2.y,v1.z + v2.z);
    }

    public Vector mul(Vector v1, double c){
        return new Vector(v1.x * c,v1.y * c,v1.z * c);
    }

    public Vector div(Vector v1, double c){
        return new Vector(v1.x / c,v1.y / c,v1.z / c);
    }




    public double scalar_mul(Vector v1, Vector v2){
        return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
    }

    public Vector vector_mul(Vector v1, Vector v2){
        return new Vector(
                v1.y * v2.z - v1.z * v2.y,
                v1.z * v2.x - v1.x * v2.z,
                v1.x * v2.y - v1.y * v2.x
        );
    }

    public Vector weight_normal_vector(List<Point> points) {
        Point mid_point = get_mid_point(points);

        Vector normal_vector = new Vector(0,0,0);
        for(int i = 0; i < points.size() - 1; i++){
            normal_vector = sum(normal_vector,
                    vector_mul(new Vector(diff(points.get(i),mid_point)),
                            new Vector(diff(points.get(i+1),points.get(i) ))));
        }
        normal_vector = sum(normal_vector,
                vector_mul(new Vector(diff(points.get(points.size()-1),mid_point)),
                        new Vector(diff(points.get(0),points.get(points.size()-1)))));


        try {
            return normal_vector.get_normalized_vector();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Point get_mid_point(List<Point> points){

        Point mid = new Point(0,0,0);
        for(int i = 0; i < points.size(); i++){
            mid = sum(mid, points.get(i));
        }
        mid = div(mid,points.size());
        return mid;
    }


    public double[] ListToSimpleArray(List<Double> arrayList){
        return   arrayList.stream()
                .mapToDouble(f -> f != null ? f : Float.NaN)
                .toArray();
    }

    public double[][] MatrixListToSimpleArray(List<List<Double>> arrayList){
        double[][] doubleArray = new double[arrayList.size()][arrayList.get(0).size()];
        for(int i = 0; i < arrayList.size(); i++){
            doubleArray[i] = ListToSimpleArray(arrayList.get(i));
        }
        return doubleArray;
    }
}