package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name="getRecomendRecipe" , query="SELECT box_r_m FROM B_RM AS box_r_m WHERE box_r_m.user_id=:user_id"),
    @NamedQuery(name="getMateQuan" , query="SELECT box_r_m FROM B_RM AS box_r_m WHERE box_r_m.recipe_id =:recipe_id AND box_r_m.material_id = :material_id AND box_r_m.user_id=:user_id")
})

@Entity
@Table(name="box_r_m")

public class B_RM {

    @Id
    @Column(name="id" ,nullable=false)
    private Integer id;

    @Column(name="material_id",nullable=false)
    private Integer material_id;


    @Column(name="recipe_id" ,nullable=false)
    private Integer recipe_id;

    @Column(name="b_quan" ,nullable=false)
    private Double b_quan;

    @Column(name="rm_quan" ,nullable=false)
    private Double rm_quan;

    @Column(name="user_id")
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Integer material_id) {
        this.material_id = material_id;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Double getB_quan() {
        return b_quan;
    }

    public void setB_quan(Double b_quan) {
        this.b_quan = b_quan;
    }

    public Double getRm_quan() {
        return rm_quan;
    }

    public void setRm_quan(Double rm_quan) {
        this.rm_quan = rm_quan;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }




}
