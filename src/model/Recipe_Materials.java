package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity

@Table(name="Recipe_Materials")
@NamedQueries({
    @NamedQuery(name="getMaterialsOfRecipe", query="SELECT rm FROM Recipe_Materials AS rm WHERE rm.recipe_id = :recipe_id"),
    @NamedQuery(name="countMaterialsOfRecipe",query="SELECT count(rm) FROM Recipe_Materials AS rm WHERE rm.recipe_id = :recipe_id"),
    @NamedQuery(name="getMaterials",query="SELECT m FROM Materials AS m, Recipe_Materials AS rm WHERE m.material_id = rm.material_id AND rm.recipe_id=:recipe_id ")

})

public class Recipe_Materials {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;


    @Column(name="recipe_id")
    private Integer recipe_id;


    @Column(name="material_id")
    private Integer material_id;

    @Column(name="quantity")
    private Double quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Integer getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Integer material_id) {
        this.material_id = material_id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }




}
