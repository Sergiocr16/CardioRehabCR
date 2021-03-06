package com.aditum.cardiorehabcr.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A IncomeDiagnosisPatient.
 */
@Entity
@Table(name = "income_diagnosis_patient")
public class IncomeDiagnosisPatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "income_diagnosis_id", nullable = false)
    private Long incomeDiagnosisId;

    @NotNull
    @Column(name = "exist", nullable = false)
    private Boolean exist;

    @ManyToOne
    @JsonIgnoreProperties("incomeDiagnosisPatients")
    private InitialAssessment initialAssessment;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public IncomeDiagnosisPatient description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIncomeDiagnosisId() {
        return incomeDiagnosisId;
    }

    public IncomeDiagnosisPatient incomeDiagnosisId(Long incomeDiagnosisId) {
        this.incomeDiagnosisId = incomeDiagnosisId;
        return this;
    }

    public void setIncomeDiagnosisId(Long incomeDiagnosisId) {
        this.incomeDiagnosisId = incomeDiagnosisId;
    }

    public Boolean isExist() {
        return exist;
    }

    public IncomeDiagnosisPatient exist(Boolean exist) {
        this.exist = exist;
        return this;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public InitialAssessment getInitialAssessment() {
        return initialAssessment;
    }

    public IncomeDiagnosisPatient initialAssessment(InitialAssessment initialAssessment) {
        this.initialAssessment = initialAssessment;
        return this;
    }

    public void setInitialAssessment(InitialAssessment initialAssessment) {
        this.initialAssessment = initialAssessment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IncomeDiagnosisPatient)) {
            return false;
        }
        return id != null && id.equals(((IncomeDiagnosisPatient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "IncomeDiagnosisPatient{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", incomeDiagnosisId=" + getIncomeDiagnosisId() +
            ", exist='" + isExist() + "'" +
            "}";
    }
}
