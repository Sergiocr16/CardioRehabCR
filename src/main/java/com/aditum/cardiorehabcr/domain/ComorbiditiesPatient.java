package com.aditum.cardiorehabcr.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A ComorbiditiesPatient.
 */
@Entity
@Table(name = "comorbidities_patient")
public class ComorbiditiesPatient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "comorbiditiet_id", nullable = false)
    private Long comorbiditietId;

    @NotNull
    @Column(name = "exist", nullable = false)
    private Boolean exist;

    @ManyToOne
    @JsonIgnoreProperties("comorbiditiesPatients")
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

    public ComorbiditiesPatient description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getComorbiditietId() {
        return comorbiditietId;
    }

    public ComorbiditiesPatient comorbiditietId(Long comorbiditietId) {
        this.comorbiditietId = comorbiditietId;
        return this;
    }

    public void setComorbiditietId(Long comorbiditietId) {
        this.comorbiditietId = comorbiditietId;
    }

    public Boolean isExist() {
        return exist;
    }

    public ComorbiditiesPatient exist(Boolean exist) {
        this.exist = exist;
        return this;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public InitialAssessment getInitialAssessment() {
        return initialAssessment;
    }

    public ComorbiditiesPatient initialAssessment(InitialAssessment initialAssessment) {
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
        if (!(o instanceof ComorbiditiesPatient)) {
            return false;
        }
        return id != null && id.equals(((ComorbiditiesPatient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ComorbiditiesPatient{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", comorbiditietId=" + getComorbiditietId() +
            ", exist='" + isExist() + "'" +
            "}";
    }
}
