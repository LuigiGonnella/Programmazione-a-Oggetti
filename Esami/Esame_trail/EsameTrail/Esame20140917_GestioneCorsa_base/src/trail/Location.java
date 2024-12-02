package trail;

import java.util.ArrayList;
import java.util.List;

import trail.Trail.Delegato;

public class Location {
    private String name;
    private Integer OrderNum;
    private List<Delegato> deles = new ArrayList<>();

    public List<Delegato> getDeles() {
        return deles;
    }

    public void setDeles(List<Delegato> deles) {
        this.deles = deles;
    }

    public void setOrderNum(Integer orderNum) {
        OrderNum = orderNum;
    }

    public Location(String name) {
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public int getOrderNum(){
        return this.OrderNum;
    }
}
