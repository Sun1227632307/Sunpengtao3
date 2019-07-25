package com.example.sunpengtao.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Shuju {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String pass;
    @Generated(hash = 242638286)
    public Shuju(Long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
    @Generated(hash = 1823096890)
    public Shuju() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return this.pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
