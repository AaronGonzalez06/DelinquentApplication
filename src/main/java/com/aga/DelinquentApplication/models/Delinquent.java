package com.aga.DelinquentApplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delinquents")
public class Delinquent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String alias;
    private Integer age;
    private String gender;
    private String nationality;
    private Date arrestDate;
    private String description;
    private String province;
    private String locality;

    @ManyToMany(mappedBy = "delinquents")
    public List<Case> cases = new ArrayList<>();

    @Override
    public String toString() {
        return "Delinquent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alias='" + alias + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", arrestDate=" + arrestDate +
                ", description='" + description + '\'' +
                ", province='" + province + '\'' +
                ", locality='" + locality + '\'' +
                ", cases=" + cases.size() +
                '}';
    }

    public Delinquent( String firstName, String lastName, String alias, Integer age, String gender, String nationality, Date arrestDate, String description, String province, String locality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
        this.arrestDate = arrestDate;
        this.description = description;
        this.province = province;
        this.locality = locality;
    }

    public Delinquent(Integer id) {
        this.id = id;
    }
}
