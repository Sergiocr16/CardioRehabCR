package com.aditum.cardiorehabcr.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A IncomeDiagnosis.
 */
@Entity
@Table(name = "income_diagnosis")
public class IncomeDiagnosis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JsonIgnoreProperties("incomeDiagnoses")
    private RehabilitationCenter rehabilitationCenter;

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

    public IncomeDiagnosis description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public IncomeDiagnosis deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public RehabilitationCenter getRehabilitationCenter() {
        return rehabilitationCenter;
    }

    public IncomeDiagnosis rehabilitationCenter(RehabilitationCenter rehabilitationCenter) {
        this.rehabilitationCenter = rehabilitationCenter;
        return this;
    }

    public void setRehabilitationCenter(RehabilitationCenter rehabilitationCenter) {
        this.rehabilitationCenter = rehabilitationCenter;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IncomeDiagnosis)) {
            return false;
        }
        return id != null && id.equals(((IncomeDiagnosis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "IncomeDiagnosis{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
