package com.hms.appointment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.appointment.dto.Appointment_Record_Dto;
import com.hms.appointment.dto.PrescriptionDetails;
import com.hms.appointment.dto.RecordDetails;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.service.Appointment_RecordService;
import com.hms.appointment.service.PrescriptionService;

import lombok.RequiredArgsConstructor;

@RestController
@Validated
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/appointment/report")
public class Appointment_RecordController {

    private final Appointment_RecordService appointmentRecordService;
    private final PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<Long> createAppointmentReport(@RequestBody Appointment_Record_Dto appointmentRecordDto) throws Exception {
        return new ResponseEntity<>(appointmentRecordService.createAppointmentRecord(appointmentRecordDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAppointmentReport(@RequestBody Appointment_Record_Dto appointmentRecordDto) throws HmsException {
        appointmentRecordService.updateAppointmentRecord(appointmentRecordDto);
        return new ResponseEntity<>("Appointment record updated successfully.", HttpStatus.OK);
    }

    @GetMapping("/getByAppointmentId/{appointmentId}")
    public ResponseEntity<Appointment_Record_Dto> getAppointmentReportByAppointmentId(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(appointmentRecordService.getAppointmentRecordByAppointmentId(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getDetailsByAppointmentId/{appointmentId}")
    public ResponseEntity<Appointment_Record_Dto> getAppointmentReportDetailsByAppointmentId(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(appointmentRecordService.getAppointmentRecordDetailsByAppointmentId(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Appointment_Record_Dto> getAppointmentReportById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(appointmentRecordService.getAppointmentRecordById(id), HttpStatus.OK);
    }

    @GetMapping("/getRecordsByPatientId/{patientId}")
    public ResponseEntity<List<RecordDetails>> getRecordsByPatientId(@PathVariable Long patientId) throws HmsException {
        return new ResponseEntity<>(appointmentRecordService.getRecordByPatientId(patientId), HttpStatus.OK);
    }

    @GetMapping("/isRecordExists/{appointmentId}")
    public ResponseEntity<Boolean> isRecordExists(@PathVariable Long appointmentId) throws HmsException {
        return new ResponseEntity<>(appointmentRecordService.isRecordExists(appointmentId), HttpStatus.OK);
    }

    @GetMapping("/getPrescriptionByPatientId/{patientId}")
    public ResponseEntity<List<PrescriptionDetails>> getPrescriptionByPatientId(@PathVariable Long patientId) throws HmsException {
        return new ResponseEntity<>(prescriptionService.getPrescriptionsByPatientId(patientId), HttpStatus.OK);
    }
}
