package com.stsoft.demotaxi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "classes")
public class StandartClass {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @NotNull
        private String nameEn;

        @NotNull
        private String nameRu;

        @NotNull
        private Long level;


}
