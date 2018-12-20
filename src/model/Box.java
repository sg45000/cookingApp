package model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="box")
@NamedQueries({
    @NamedQuery(name="getMyMaterials" , query="SELECT m FROM Box AS b,Materials AS m WHERE m.material_id=b.material_id AND b.user_id=:user_id" ),
    @NamedQuery(name="getMyBox" ,query="SELECT b FROM Box AS b WHERE b.material_id=:material_id AND b.user_id = :user_id"),
    @NamedQuery(name="checkBoxMaterial", query="SELECT b FROM Box AS b WHERE b.material_id=:material_id AND b.user_id=:user_id"),
    @NamedQuery(name="cookFromMyBox" , query="SELECT b FROM Box AS b WHERE b.material_id = :material_id AND b.user_id=:user_id")
})
public class Box {

    @Id
    @Column(name="id" ,nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_id" ,nullable=false)
    private Integer user_id;

    @Column(name="material_id" ,nullable=false)
    private Integer material_id;

    @Column(name="quantity" ,nullable=false)
    private Double quantity;

    @Column(name="created_at" ,nullable=false)
    private Timestamp created_at;

    @Column(name="updated_at" ,nullable=false)
    private Timestamp updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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




}
