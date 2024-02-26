package com.aga.DelinquentApplication.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer caseId;

    private String caseName;
    private String caseDescription;
    private String caseType;
    private String caseStatus;
    private Date startDate;
    private Date endDate;
    private String location;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "delinquent_cases",
            joinColumns = @JoinColumn(name = "case_id"),
            inverseJoinColumns = @JoinColumn(name = "delinquent_id")
    )
    private List<Delinquent> delinquents = new ArrayList<>();

    @Override
    public String toString() {
        return "Case{" +
                "caseId=" + caseId +
                ", caseName='" + caseName + '\'' +
                ", caseDescription='" + caseDescription + '\'' +
                ", caseType='" + caseType + '\'' +
                ", caseStatus='" + caseStatus + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", location='" + location + '\'' +
                ", delinquents=" + delinquents.size() +
                '}';
    }

    public Case(String caseName, String caseDescription, String caseType, String caseStatus, Date startDate, String location) {
        this.caseName = caseName;
        this.caseDescription = caseDescription;
        this.caseType = caseType;
        this.caseStatus = caseStatus;
        this.startDate = startDate;
        this.location = location;
    }
}
