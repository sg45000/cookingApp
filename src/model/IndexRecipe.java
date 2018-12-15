package model;

import java.util.List;

public class IndexRecipe {
    Recipes r=null;
    List<MaterialsOfRecipe> morList =null;

    public Recipes getR() {
        return r;
    }
    public void setR(Recipes r) {
        this.r = r;
    }
    public List<MaterialsOfRecipe> getMorList() {
        return morList;
    }
    public void setMorList(List<MaterialsOfRecipe> morList) {
        this.morList = morList;
    }



}
