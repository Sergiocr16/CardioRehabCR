package com.aditum.cardiorehabcr.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aditum.cardiorehabcr.domain.MayorEventsSession} entity.
 */
public class MayorEventsSessionDTO implements Serializable {

    private Long id;

    private String description;

    @NotNull
    private Long mayorEventId;

    @NotNull
    private Boolean exist;


    private Long sessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMayorEventId() {
        return mayorEventId;
    }

    public void setMayorEventId(Long mayorEventId) {
        this.mayorEventId = mayorEventId;
    }

    public Boolean isExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MayorEventsSessionDTO mayorEventsSessionDTO = (MayorEventsSessionDTO) o;
        if (mayorEventsSessionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mayorEventsSessionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MayorEventsSessionDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", mayorEventId=" + getMayorEventId() +
            ", exist='" + isExist() + "'" +
            ", sessionId=" + getSessionId() +
            "}";
    }
}
