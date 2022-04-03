package user_interface.data_classes;

public class TableViewData {

    Integer num;
    String generatrix;
    String form;

    public TableViewData(Integer num, String generatrix, String form) {
        this.num = num;
        this.generatrix = generatrix;
        this.form = form;
    }

    public Integer getNum() {
        return num;
    }

    public String getGeneratrix() {
        return generatrix;
    }

    public String getForm() {
        return form;
    }
}
