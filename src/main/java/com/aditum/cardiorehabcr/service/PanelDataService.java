package com.aditum.cardiorehabcr.service;

import com.aditum.cardiorehabcr.domain.FinalAssessment;
import com.aditum.cardiorehabcr.domain.Patient;
import com.aditum.cardiorehabcr.service.dto.*;
import com.aditum.cardiorehabcr.service.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PanelDataService {

    private final Logger log = LoggerFactory.getLogger(PanelDataService.class);

    private PatientServiceImpl patientService;

    private RehabilitationGroupServiceImpl rehabilitationGroupService;

    private InitialAssessmentServiceImpl initialAssessmentService;

    private FinalAssessmentServiceImpl finalAssessmentService;

    private SessionServiceImpl sessionService;

    public PanelDataService(PatientServiceImpl patientService,
                            RehabilitationGroupServiceImpl rehabilitationGroupService,
                            InitialAssessmentServiceImpl initialAssessmentService,
                            FinalAssessmentServiceImpl finalAssessmentService,
                            SessionServiceImpl sessionService) {
        this.patientService = patientService;
        this.rehabilitationGroupService = rehabilitationGroupService;
        this.initialAssessmentService = initialAssessmentService;
        this.finalAssessmentService = finalAssessmentService;
        this.sessionService = sessionService;
    }

    public PanelDataDTO calculatePanelDataPerGroup(RehabilitationGroupDTO rehabilitationGroup) {
        PanelDataDTO panelDataDTO = new PanelDataDTO();
        Set<PatientDTO> patients = rehabilitationGroup.getPatients();
        Integer patientsTotal = patients.size();
        Integer abandonmentNonMedicalCauseTotal = 0;
        Integer assessmentIndividualGroupSupportTotal = 0;
        Integer improvementFunctionalCapacityTotal = 0;
        Integer improvementLipidicProfileTotal = 0;
        Integer improvementGlycemicControlTotal = 0;
        Integer weightReductionTotal = 0;
        Integer suspendedSmokingTotal = 0;
        Integer returnWorkActivityTotal = 0;

//        ?????
        Integer exerciseAdherenceTotal = 0;
        Integer followUpExternalTotal = 0;

        for (PatientDTO patient : patients) {
            List<FinalAssessmentDTO> finalAssessments = this.finalAssessmentService.findAllByPatient(null, patient.getId()).getContent();
            FinalAssessmentDTO finalAssessment = finalAssessments.get(finalAssessments.size() - 1);
            InitialAssessmentDTO initialAssessment = this.initialAssessmentService.findOneByPatient(patient.getId()).get();
            abandonmentNonMedicalCauseTotal += this.abandonmentNonMedicalCausePercentage(patient, initialAssessment, finalAssessment);
            assessmentIndividualGroupSupportTotal += this.assessmentIndividualGroupSupportPercentage(patient);
            improvementFunctionalCapacityTotal += this.improvementFunctionalCapacityPercentage(patient, initialAssessment, finalAssessment);
            improvementLipidicProfileTotal += this.improvementLipidicProfilePercentage(patient, initialAssessment, finalAssessment);
            improvementGlycemicControlTotal += this.improvementGlycemicControlPercentage(patient, initialAssessment, finalAssessment);
            weightReductionTotal += this.weightReductionPercentage(patient, initialAssessment, finalAssessment);
            suspendedSmokingTotal += this.suspendedSmokingPercentage(patient, initialAssessment, finalAssessment);
            returnWorkActivityTotal += this.returnWorkActivityPercentage(patient, initialAssessment, finalAssessment);
        }

        panelDataDTO.setPatientsTotal(patientsTotal);

        panelDataDTO.setAbandonmentNonMedicalCausePercentage(calculatePercentaje(patientsTotal, abandonmentNonMedicalCauseTotal));
        panelDataDTO.setAbandonmentNonMedicalCauseRestPercentage(calculatePercentajeRest(patientsTotal, abandonmentNonMedicalCauseTotal));

        panelDataDTO.setAssessmentIndividualGroupSupportPercentage(calculatePercentaje(patientsTotal, assessmentIndividualGroupSupportTotal));
        panelDataDTO.setAssessmentIndividualGroupSupportRestPercentage(calculatePercentajeRest(patientsTotal, assessmentIndividualGroupSupportTotal));

        panelDataDTO.setImprovementFunctionalCapacityPercentage(calculatePercentaje(patientsTotal,improvementFunctionalCapacityTotal));
        panelDataDTO.setImprovementFunctionalCapacityRestPercentage(calculatePercentajeRest(patientsTotal,improvementFunctionalCapacityTotal));

        panelDataDTO.setImprovementLipidicProfilePercentage(calculatePercentaje(patientsTotal,improvementLipidicProfileTotal));
        panelDataDTO.setImprovementLipidicProfileRestPercentage(calculatePercentajeRest(patientsTotal,improvementLipidicProfileTotal));

        panelDataDTO.setImprovementGlycemicControlPercentage(calculatePercentaje(patientsTotal,improvementGlycemicControlTotal));
        panelDataDTO.setImprovementGlycemicControlPercentage(calculatePercentaje(patientsTotal,improvementGlycemicControlTotal));

        panelDataDTO.setWeightReductionPercentage(calculatePercentaje(patientsTotal,weightReductionTotal));
        panelDataDTO.setWeightReductionRestPercentage(calculatePercentajeRest(patientsTotal,weightReductionTotal));

        panelDataDTO.setSuspendedSmokingPercentage(calculatePercentaje(patientsTotal,suspendedSmokingTotal));
        panelDataDTO.setSuspendedSmokingRestPercentage(calculatePercentajeRest(patientsTotal,suspendedSmokingTotal));

        panelDataDTO.setReturnWorkActivityPercentage(calculatePercentaje(patientsTotal,returnWorkActivityTotal));
        panelDataDTO.setReturnWorkActivityRestPercentage(calculatePercentajeRest(patientsTotal,returnWorkActivityTotal));

        return panelDataDTO;
    }

    private double calculatePercentaje(int total, int number) {
        double percentage = ((number * 100) / total);
        return percentage;
    }

    private double calculatePercentajeRest(int total, int number) {
        int rest = total - number;
        double percentage = (rest * 100) / total;
        return percentage;
    }


    private void fullSelectionCriteriaPercentage(Patient patient) {

    }

    private Integer assessmentIndividualGroupSupportPercentage(PatientDTO patient) {
        if (patient.getRehabStatus() >= 1) {
            List<SessionDTO> sessions = this.sessionService.findAllByPatient(null, patient.getId()).getContent();
            if (sessions.size() >= 2) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private Integer abandonmentNonMedicalCausePercentage(PatientDTO patient, InitialAssessmentDTO initialAssessment, FinalAssessmentDTO finalAssessment) {
        if (patient.getRehabStatus() >= 2) {
            if (finalAssessment.isAbandonmentMedicCause()) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }

    private Integer improvementFunctionalCapacityPercentage(PatientDTO patient, InitialAssessmentDTO initialAssessment, FinalAssessmentDTO finalAssessment) {
        if (patient.getRehabStatus() >= 2) {
            return improve(initialAssessment.getBaselineFunctionalCapacity(), finalAssessment.getBaselineFunctionalCapacity());
        }
        return 0;
    }

    private Integer improvementLipidicProfilePercentage(PatientDTO patient, InitialAssessmentDTO initialAssessment, FinalAssessmentDTO finalAssessment) {
        if (patient.getRehabStatus() >= 2) {
            return improve(initialAssessment.gethDL(), finalAssessment.gethDL());
        }
        return 0;
    }

    private Integer improvementGlycemicControlPercentage(PatientDTO patient, InitialAssessmentDTO initialAssessment, FinalAssessmentDTO finalAssessment) {
        if (patient.getRehabStatus() >= 2) {
            return improve(initialAssessment.getHbiac(), finalAssessment.getHbiac());
        }
        return 0;
    }

    private Integer weightReductionPercentage(PatientDTO patient, InitialAssessmentDTO initialAssessment, FinalAssessmentDTO finalAssessment) {
        if (patient.getRehabStatus() >= 2) {
            return improve(finalAssessment.getWeight(), initialAssessment.getWeight());
        }
        return 0;
    }

    private Integer suspendedSmokingPercentage(PatientDTO patient, InitialAssessmentDTO initialAssessment, FinalAssessmentDTO finalAssessment) {
        if (patient.getRehabStatus() >= 2) {
            Boolean smokingBefore = initialAssessment.getSmoking() == "Activo";
            Boolean smokingAfter = finalAssessment.getSmoking() == "Activo";
            if (smokingBefore && !smokingAfter) {
                return 1;
            }
        }
        return 0;
    }

    private Integer returnWorkActivityPercentage(PatientDTO patient, InitialAssessmentDTO initialAssessment, FinalAssessmentDTO finalAssessment) {
        if (patient.getRehabStatus() >= 2) {
            if (patient.getOcupation() != "Asalariado") {
                return finalAssessment.isIsWorking() ? 1 : 0;
            }
        }
        return 0;
    }

    private Integer improve(String value1, String value2) {
        return Double.parseDouble(value1) < Double.parseDouble(value2) ? 1 : 0;
    }
}
