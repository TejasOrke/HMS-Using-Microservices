package com.hms.profile.service;

import com.hms.profile.dto.PatientDto;
import com.hms.profile.entity.Patient;
import com.hms.profile.exception.HmsException;
import org.springframework.stereotype.Service;

public interface PatientService {
    public Long addPatient(PatientDto patientDto) throws HmsException;
    public PatientDto getPatientById(Long patientId) throws HmsException;
    public PatientDto updatePatient(PatientDto patientDto) throws HmsException;
    public Boolean isPatientExists(Long patientId) throws HmsException;
}
