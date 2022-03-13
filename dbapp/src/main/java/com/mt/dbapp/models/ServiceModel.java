package com.mt.dbapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data //lombok annotation: to have the setters and getters implicit
@Entity
public class ServiceModel {
    //attributes or db columns
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 5)
    private String tag;

    private String action;

    // constructors
    public ServiceModel() {}

    public ServiceModel(UUID id, String tag,String action) {
        this.id = id;
        this.tag = tag;
        this.action = action;
    }

}
