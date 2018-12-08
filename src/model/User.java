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
@Table(name="user")
@NamedQueries({
    @NamedQuery(name="checkRegisteredCode",query="SELECT COUNT(u) FROM User AS u WHERE u.email=:email"),
    @NamedQuery(name="checkLoginEmailAndPassword",query="SELECT u FROM User AS u WHERE u.email=:email AND u.password=:password")
})
public class User {

    @Id
    @Column(name="user_id" ,nullable=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer user_id;

    @Column(name="name" ,nullable=false)
    private String name;

    @Column(name="email" ,nullable=false)
    private String email;

    @Column(name="password" ,nullable=false)
    private String password;

    @Column(name="created_at" ,nullable=false)
    private Timestamp created_at;

    @Column(name="updated_at" ,nullable=false)
    private Timestamp updated_at;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
