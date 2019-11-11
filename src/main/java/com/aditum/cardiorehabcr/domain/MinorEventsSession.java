package com.aditum.cardiorehabcr.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A MinorEventsSession.
 */
@Entity
@Table(name = "minor_events_session")
public class MinorEventsSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "minor_event_id", nullable = false)
    private Long minorEventId;

    @NotNull
    @Column(name = "exist", nullable = false)
    private Boolean exist;

    @ManyToOne
    @JsonIgnoreProperties("minorEventsSessions")
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

    public MinorEventsSession description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMinorEventId() {
        return minorEventId;
    }

    public MinorEventsSession minorEventId(Long minorEventId) {
        this.minorEventId = minorEventId;
        return this;
    }

    public void setMinorEventId(Long minorEventId) {
        this.minorEventId = minorEventId;
    }

    public Boolean isExist() {
        return exist;
    }

    public MinorEventsSession exist(Boolean exist) {
        this.exist = exist;
        return this;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Session getSession() {
        return session;
    }

    public MinorEventsSession session(Session session) {
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
        if (!(o instanceof MinorEventsSession)) {
            return false;
        }
        return id != null && id.equals(((MinorEventsSession) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MinorEventsSession{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", minorEventId=" + getMinorEventId() +
            ", exist='" + isExist() + "'" +
            "}";
    }
}
