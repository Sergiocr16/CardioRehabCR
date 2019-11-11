package com.aditum.cardiorehabcr.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A DepressiveSymptomsSession.
 */
@Entity
@Table(name = "depressive_symptoms_session")
public class DepressiveSymptomsSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "depressive_symptom_id", nullable = false)
    private Long depressiveSymptomId;

    @NotNull
    @Column(name = "exist", nullable = false)
    private Boolean exist;

    @ManyToOne
    @JsonIgnoreProperties("depressiveSymptomsSessions")
    private Session session;

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

    public DepressiveSymptomsSession description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDepressiveSymptomId() {
        return depressiveSymptomId;
    }

    public DepressiveSymptomsSession depressiveSymptomId(Long depressiveSymptomId) {
        this.depressiveSymptomId = depressiveSymptomId;
        return this;
    }

    public void setDepressiveSymptomId(Long depressiveSymptomId) {
        this.depressiveSymptomId = depressiveSymptomId;
    }

    public Boolean isExist() {
        return exist;
    }

    public DepressiveSymptomsSession exist(Boolean exist) {
        this.exist = exist;
        return this;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Session getSession() {
        return session;
    }

    public DepressiveSymptomsSession session(Session session) {
        this.session = session;
        return this;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepressiveSymptomsSession)) {
            return false;
        }
        return id != null && id.equals(((DepressiveSymptomsSession) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DepressiveSymptomsSession{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", depressiveSymptomId=" + getDepressiveSymptomId() +
            ", exist='" + isExist() + "'" +
            "}";
    }
}
