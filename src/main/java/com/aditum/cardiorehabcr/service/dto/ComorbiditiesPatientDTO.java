package com.aditum.cardiorehabcr.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.aditum.cardiorehabcr.domain.ComorbiditiesPatient} entity.
 */
public class ComorbiditiesPatientDTO implements Serializable {

    private Long id;

    private String description;

    @NotNull
    private Long comorbiditietId;

    @NotNull
    private Boolean exist;


    private Long initialAssessmentId;

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

    public Long getComorbiditietId() {
        return comorbiditietId;
    }

    public void setComorbiditietId(Long comorbiditietId) {
        this.comorbiditietId = comorbiditietId;
    }

    public Boolean isExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Long getInitialAssessmentId() {
        return initialAssessmentId;
    }

    public void setInitialAssessmentId(Long initialAssessmentId) {
        this.initialAssessmentId = initialAssessmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ComorbiditiesPatientDTO comorbiditiesPatientDTO = (ComorbiditiesPatientDTO) o;
        if (comorbiditiesPatientDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), comorbiditiesPatientDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ComorbiditiesPatientDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", comorbiditietId=" + getComorbiditietId() +
            ", exist='" + isExist() + "'" +
            ", initialAssessment=" + getInitialAssessmentId() +
            "}";
    }
}
