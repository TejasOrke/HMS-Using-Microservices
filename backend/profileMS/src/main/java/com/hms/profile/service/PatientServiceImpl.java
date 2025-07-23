package com.hms.profile.service;

import com.hms.profile.dto.PatientDto;
import com.hms.profile.exception.HmsException;
import com.hms.profile.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public Long addPatient(PatientDto patientDto) throws HmsException {
        if(patientDto.getEmail() != null && patientRepo.findByEmail(patientDto.getEmail()).isPresent()) throw new HmsException("PATIENT_ALREADY_EXISTS");
        if(patientDto.getAadharNo() != null && patientRepo.findByAadharNo(patientDto.getAadharNo()).isPresent()) throw new HmsException("PATIENT_AADHAR_ALREADY_EXISTS");
        return patientRepo.save(patientDto.toEntity()).getId();

    }

    @Override
    public PatientDto getPatientById(Long patientId) throws HmsException {
        return patientRepo.findById(patientId).orElseThrow(() -> new  HmsException("PATIENT_NOT_FOUND"))
                .patientDto();
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto) throws HmsException {
        patientRepo.findById(patientDto.getId()).orElseThrow(() -> new HmsException("PATIENT_NOT_FOUND"));
        return patientRepo.save(patientDto.toEntity()).patientDto();
    }

    @Override
    public Boolean isPatientExists(Long patientId) throws HmsException {
        return patientRepo.existsById(patientId);
    }
}
