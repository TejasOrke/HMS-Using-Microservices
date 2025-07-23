package com.hms.appointment.service;

import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.AppointmentDetails;
import com.hms.appointment.exception.HmsException;

import java.util.List;

public interface AppointmentService {
    Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException;
    void cancelAppointment(Long appointmentId) throws HmsException;
    void completeAppointmentId(Long appointmentId);
    void rescheduleAppointment(Long appointmentId, String newDateTime);
    AppointmentDTO getAppointmentIdDetails(Long appointmentId) throws HmsException;
    AppointmentDetails getAppointmentIdDetailsWithName(Long appointmentId) throws HmsException;
    List<AppointmentDetails> getAllAppointmentByPatientId(Long patientId) throws HmsException;
    List<AppointmentDetails> getAllAppointmentByDoctorId(Long doctorId) throws HmsException;
}
