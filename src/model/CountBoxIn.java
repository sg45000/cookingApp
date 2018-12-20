package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
    @NamedQuery(name="getCountBoxIn" , query="SELECT cbi FROM CountBoxIn AS cbi WHERE cbi.user_id=:user_id")
})

@Entity
@Table(name="count_box_in")


public class CountBoxIn {

    @Id
    @Column(name="id")
    private Integer id;

    @Column(name="count")
    private Integer count;

    @Column(name="recipe_id")
    private Integer recipe_id;

    @Column(name="user_id")
    private Integer user_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }



}
