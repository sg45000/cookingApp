package model;

import java.util.List;

public class RecomendRecipe {

    private List<MateQuan> mqList;
    private Recipes  r;
    private Double ratio;

    public List<MateQuan> getMqList() {
        return mqList;
    }
    public void setMqList(List<MateQuan> mqList) {
        this.mqList = mqList;
    }
    public Recipes getR() {
        return r;
    }
    public void setR(Recipes r) {
        this.r = r;
    }
    public Double getRatio() {
        return ratio;
    }
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }



}
