package com.aditum.cardiorehabcr.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aditum.cardiorehabcr.domain.Comorbiditie} entity.
 */
public class ComorbiditieDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    private Boolean deleted;


    private Long rehabilitationCenterId;

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

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getRehabilitationCenterId() {
        return rehabilitationCenterId;
    }

    public void setRehabilitationCenterId(Long rehabilitationCenterId) {
        this.rehabilitationCenterId = rehabilitationCenterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComorbiditieDTO comorbiditieDTO = (ComorbiditieDTO) o;
        if (comorbiditieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comorbiditieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComorbiditieDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", rehabilitationCenter=" + getRehabilitationCenterId() +
            "}";
    }
}
