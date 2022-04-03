package user_interface.data_classes;

public class PointViewData {

    public PointViewData(Integer num, Double x_coord, Double y_coord) {
        this.num = num;
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }

    public Integer getNum() {
        return num;
    }

    public Double getX_coord() {
        return x_coord;
    }

    public Double getY_coord() {
        return y_coord;
    }

    Integer num;
    Double x_coord;
    Double y_coord;


}
