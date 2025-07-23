package com.hms.appointment.service;

import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.Appointment_Record_Dto;
import com.hms.appointment.dto.RecordDetails;
import com.hms.appointment.entity.Appointment_Record;
import com.hms.appointment.exception.HmsException;

import java.util.List;

public interface Appointment_RecordService {
    public Long createAppointmentRecord(Appointment_Record_Dto appointmentRecordDto) throws HmsException;

    public void updateAppointmentRecord(Appointment_Record_Dto appointmentRecordDto) throws HmsException;

    public Appointment_Record_Dto getAppointmentRecordByAppointmentId(Long appointmentId) throws HmsException;

    public Appointment_Record_Dto getAppointmentRecordDetailsByAppointmentId(Long appointmentId) throws HmsException;

    public Appointment_Record_Dto getAppointmentRecordById(Long recordId) throws HmsException;

    List<RecordDetails> getRecordByPatientId(Long patientId) throws HmsException;

    Boolean isRecordExists(Long appointmentId) throws HmsException;
}
