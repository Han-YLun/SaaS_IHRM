package com.ihrm.domain.system;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bs_city")
public class City implements Serializable {

    @Id
    private String id;
    private String name;
    private java.util.Date createTime;

    public void setId(String value) {
        this.id = value;
    }
    public String getId() {
       return this.id;
    }
    public void setName(String value) {
        this.name = value;
    }
    public String getName() {
       return this.name;
    }
    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }
    public java.util.Date getCreateTime() {
       return this.createTime;
    }
}
