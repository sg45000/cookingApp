package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="recipes")

@NamedQueries({
    @NamedQuery(name="getAllRecipes",query="SELECT r FROM Recipes AS r ORDER BY r.recipe_id DESC"),
    @NamedQuery(name="getCountRecipes",query="SELECT count(r) FROM Recipes AS r "),
    @NamedQuery(name="getLastRecipeId",query="SELECT max(r.recipe_id) FROM Recipes AS r ")


})
public class Recipes {

    @Id
    @Column(name="recipe_id" ,nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer recipe_id;

    @Column(name="name",nullable=false)
    private String name;

    @Lob
    @Column(name="how_to" ,length=500 ,nullable=false)
    private String how_to;

    @Column(name="created_at" ,nullable=false)
    private Timestamp created_at;

    @Column(name="updated_at" ,nullable=false)
    private Timestamp updated_at;

    @Column(name="image_name")
    private String image_name;

    @Column(name="time")
    private String time;

    @Column(name="how_many")
    private String how_many;

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHow_to() {
        return how_to;
    }

    public void setHow_to(String how_to) {
        this.how_to = how_to;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHow_many() {
        return how_many;
    }

    public void setHow_many(String how_many) {
        this.how_many = how_many;
    }




}
