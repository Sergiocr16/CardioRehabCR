package com.aditum.cardiorehabcr.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MayorEventsSession.
 */
@Entity
@Table(name = "mayor_events_session")
public class MayorEventsSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "mayor_event_id", nullable = false)
    private Long mayorEventId;

    @NotNull
    @Column(name = "exist", nullable = false)
    private Boolean exist;

    @ManyToOne
    @JsonIgnoreProperties("mayorEventsSessions")
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

    public MayorEventsSession description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMayorEventId() {
        return mayorEventId;
    }

    public MayorEventsSession mayorEventId(Long mayorEventId) {
        this.mayorEventId = mayorEventId;
        return this;
    }

    public void setMayorEventId(Long mayorEventId) {
        this.mayorEventId = mayorEventId;
    }

    public Boolean isExist() {
        return exist;
    }

    public MayorEventsSession exist(Boolean exist) {
        this.exist = exist;
        return this;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Session getSession() {
        return session;
    }

    public MayorEventsSession session(Session session) {
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
        if (!(o instanceof MayorEventsSession)) {
            return false;
        }
        return id != null && id.equals(((MayorEventsSession) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MayorEventsSession{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", mayorEventId=" + getMayorEventId() +
            ", exist='" + isExist() + "'" +
            "}";
    }
}
