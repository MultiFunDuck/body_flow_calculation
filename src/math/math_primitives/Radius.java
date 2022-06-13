package math.math_primitives;

public abstract class Radius {

    public double start;
    public double end;
    public double length = end - start;


    public abstract double get_radius(double x);

    public abstract void set_end_diameter(double diameter);

    public abstract void set_start_diameter(double diameter);

    public double get_start(){
        return this.start;
    }

    public double get_end(){
        return this.end;
    }

    public double get_end_radius(){
        return get_radius(this.end);
    }

    public double get_start_radius(){
        return get_radius(this.start);
    }

    public double get_length(){
        return this.end - this.start;
    }

    public void set_start (double start){

        this.start = start;
        this.length = this.end - this.start;
    }

    public void set_end(double end){

        this.end = end;
        this.length = this.end - this.start;
    }



    public boolean is_in_range(double x){
        return ((start <= x) && (x <= end));
    }

    public abstract Radius get_derivative();

}
