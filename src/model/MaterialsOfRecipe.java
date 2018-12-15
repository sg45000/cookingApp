package model;

public class MaterialsOfRecipe extends Materials{
    private Double quantity;
    private Integer recipe_id;



    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public MaterialsOfRecipe(Materials m,Double quantity,Integer recipe_id){
        this.setName(m.getName());
        this.setQuantity(quantity);
        this.setRecipe_id(recipe_id);
        this.setUnit(m.getUnit());
    }

}
