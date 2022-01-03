package math_primitives;

public abstract class Radius {

    public double start;
    public double end;
    public double length = end - start;


    public abstract double get_radius(double x);

    public double get_start(){
        return this.start;
    };
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

    public void set_start (double start) throws Exception{
        if(this.end < start){
            throw new Exception("setting start of radius righter then end");
        }
        this.start = start;
        this.length = this.end - this.start;
    }

    public void set_end(double end) throws Exception{
        if(this.start > end){
            throw new Exception("setting end of radius lefter then start");
        }
        this.end = end;
        this.length = this.end - this.start;
    }

    public boolean is_in_range(double x){
        return ((start <= x) && (x <= end));
    }

}
