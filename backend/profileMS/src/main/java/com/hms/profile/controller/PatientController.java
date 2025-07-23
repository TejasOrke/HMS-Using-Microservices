package com.hms.profile.controller;


import com.hms.profile.dto.PatientDto;
import com.hms.profile.entity.Patient;
import com.hms.profile.exception.HmsException;
import com.hms.profile.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile/patient")
@Validated
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<Long> addPatient(@RequestBody PatientDto patientDto) throws HmsException {
        return new ResponseEntity<>(patientService.addPatient(patientDto), HttpStatus.CREATED);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto) throws HmsException{
        return new ResponseEntity<>(patientService.updatePatient(patientDto), HttpStatus.OK);
    }
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> isPatientExists(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(patientService.isPatientExists(id), HttpStatus.OK);
    }
}
