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
@Table(name="materials")
@NamedQueries({
    @NamedQuery(name="getAllMaterials", query="SELECT mm FROM Materials AS mm WHERE mm.type = :type"),
    @NamedQuery(name="getMaterials",query="SELECT m FROM Materials AS m WHERE m.material_id = :material_id")
})
public class Materials {

    @Id
    @Column(name="material_id" ,nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer material_id;

    @Column(name="name" ,nullable=false)
    private String name;

    @Column(name="type" ,nullable=false)
    private String type;

    @Column(name="unit" ,nullable=false)
    private String unit;

    @Column(name="created_at" ,nullable=false)
    private Timestamp created_at;

    @Column(name="updated_at" ,nullable=false)
    private Timestamp updated_at;

    @Column(name="use_limit")
    private String use_limit;

    public Integer getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Integer material_id) {
        this.material_id = material_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public String getUse_limit() {
        return use_limit;
    }

    public void setUse_limit(String use_limit) {
        this.use_limit = use_limit;
    }




}
