package com.aditum.cardiorehabcr.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aditum.cardiorehabcr.domain.NonSpecificPain} entity.
 */
public class NonSpecificPainDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

        NonSpecificPainDTO nonSpecificPainDTO = (NonSpecificPainDTO) o;
        if (nonSpecificPainDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nonSpecificPainDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NonSpecificPainDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", code='" + getCode() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", rehabilitationCenterId=" + getRehabilitationCenterId() +
            "}";
    }
}
