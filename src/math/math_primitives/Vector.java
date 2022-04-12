package math.math_primitives;

public class Vector {


    public double x,y,z;

    public Vector(Vector v){
        this.x = v.x;
        this.y = v.y;
        this.z = v.z;
    }

    public Vector(Point p){
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public Vector get_normalized_vector() throws Exception{

        if(this.length() == 0){

            throw new Exception("Нормировка вектора нулевой длины!");

        }
        return new Vector(x/this.length(), y/this.length(), z/this.length());
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
