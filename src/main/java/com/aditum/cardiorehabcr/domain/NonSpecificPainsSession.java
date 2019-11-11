package com.aditum.cardiorehabcr.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A NonSpecificPainsSession.
 */
@Entity
@Table(name = "non_specific_pains_session")
public class NonSpecificPainsSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "non_specific_pain_id", nullable = false)
    private Long nonSpecificPainId;

    @NotNull
    @Column(name = "exist", nullable = false)
    private Boolean exist;

    @ManyToOne
    @JsonIgnoreProperties("nonSpecificPainsSessions")
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

    public NonSpecificPainsSession description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNonSpecificPainId() {
        return nonSpecificPainId;
    }

    public NonSpecificPainsSession nonSpecificPainId(Long nonSpecificPainId) {
        this.nonSpecificPainId = nonSpecificPainId;
        return this;
    }

    public void setNonSpecificPainId(Long nonSpecificPainId) {
        this.nonSpecificPainId = nonSpecificPainId;
    }

    public Boolean isExist() {
        return exist;
    }

    public NonSpecificPainsSession exist(Boolean exist) {
        this.exist = exist;
        return this;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Session getSession() {
        return session;
    }

    public NonSpecificPainsSession session(Session session) {
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
        if (!(o instanceof NonSpecificPainsSession)) {
            return false;
        }
        return id != null && id.equals(((NonSpecificPainsSession) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NonSpecificPainsSession{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", nonSpecificPainId=" + getNonSpecificPainId() +
            ", exist='" + isExist() + "'" +
            "}";
    }
}
