package model;

public class BoxMaterials {
    public Box b;
    public Materials m;
    public BoxMaterials(Materials m,Box b){
        this.m=m;
        this.b=b;

    }
    public Box getB() {
        return b;
    }
    public void setB(Box b) {
        this.b = b;
    }
    public Materials getM() {
        return m;
    }
    public void setM(Materials m) {
        this.m = m;
    }




}
