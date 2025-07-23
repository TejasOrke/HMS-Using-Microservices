package com.hms.appointment.service;

import com.hms.appointment.dto.PrescriptionDetails;
import com.hms.appointment.dto.PrescriptionDto;
import com.hms.appointment.exception.HmsException;

import java.util.List;

public interface PrescriptionService {

    public Long savePrescription(PrescriptionDto prescriptionDto) throws HmsException;
    public PrescriptionDto getPrescriptionByAppointmentId(Long appointmentId) throws HmsException;
    public PrescriptionDto getPrescriptionById(Long prescriptionId) throws HmsException;
    public List<PrescriptionDetails> getPrescriptionsByPatientId(Long patientId) throws HmsException;
}
