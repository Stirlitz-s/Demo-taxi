package com.stsoft.demotaxi.aggregators.yandex.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "auth_yandex")
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String name;

    @NotNull
    private String keyToken;
    public AuthToken() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyToken() {
        return keyToken;
    }

    public void setKey(String keyToken) {
        this.keyToken = keyToken;
    }
    @Override
    public String toString() {
        return "id: "+id+"; name: " + name + "; keyToken: " + keyToken;
    }
}
