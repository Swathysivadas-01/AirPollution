package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * to define an entity
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {

    /**
     * @Id annotation to make id variable as Primary key
     */
    @Id
    @NotNull
    @Column(name = "id", length = 50)
    private String id;
    @NotNull
    @Column(name = "password")
    private String password;
    private String name;
    private String age;
    private String country;


}
