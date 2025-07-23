package com.hms.appointment.service;


import com.hms.appointment.clients.ProfileClient;
import com.hms.appointment.dto.*;
import com.hms.appointment.entity.Appointment;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ProfileClient profileClient;

    @Override
    public Long scheduleAppointment(AppointmentDTO appointmentDTO) throws HmsException {
//        Boolean patientExist = apiService.isPatientExists(appointmentDTO.getPatientId()).block();
        Boolean patientExist = profileClient.patientExists(appointmentDTO.getPatientId());
        if (!patientExist) {
            throw new HmsException("PATIENT_NOT_FOUND");
        }
//        Boolean doctorExist = apiService.isDoctorExists(appointmentDTO.getDoctorId()).block();
        Boolean doctorExist = profileClient.doctorExists(appointmentDTO.getDoctorId());

        if (!doctorExist) {
            throw new HmsException("DOCTOR_NOT_FOUND");
        }
        return appointmentRepo.save(appointmentDTO.toEntity()).getId();
    }

    @Override
    public void cancelAppointment(Long appointmentId) throws HmsException {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow(() -> new HmsException("APPOINTMENT_NOT_FOUND"));
        if (appointment.getStatus().equals(Status.CANCELLED)) {
            throw new HmsException("APPOINTMENT_ALREADY_CANCELLED");
        }

        appointment.setStatus(Status.CANCELLED);
        appointmentRepo.save(appointment);
    }

    @Override
    public void completeAppointmentId(Long appointmentId) {

    }

    @Override
    public void rescheduleAppointment(Long appointmentId, String newDateTime) {

    }

    @Override
    public AppointmentDTO getAppointmentIdDetails(Long appointmentId) throws HmsException {
        return appointmentRepo.findById(appointmentId).orElseThrow(() -> new HmsException("APPOINTMENT_NOT_FOUND")).toDto();
    }

    @Override
    public AppointmentDetails getAppointmentIdDetailsWithName(Long appointmentId) throws HmsException {
        AppointmentDTO appointmentDTO = appointmentRepo.findById(appointmentId).orElseThrow(() -> new HmsException("APPOINTMENT_NOT_FOUND")).toDto();
//        DoctorDto doctorDto = apiService.getDoctorById(appointmentId).block();
        DoctorDto doctorDto = profileClient.getDoctorById(appointmentDTO.getDoctorId());
//        PatientDto patientDto = apiService.getPatientById(appointmentId).block();
        PatientDto patientDto = profileClient.getPatientById(appointmentDTO.getPatientId());
        return new AppointmentDetails(appointmentDTO.getId(), appointmentDTO.getPatientId(), patientDto.getName(), patientDto.getEmail(), patientDto.getPhone() ,appointmentDTO.getDoctorId(), doctorDto.getName(),
                appointmentDTO.getAppointmentDateTime(), appointmentDTO.getStatus(), appointmentDTO.getReason(), appointmentDTO.getNotes());

    }

    @Override
    public List<AppointmentDetails> getAllAppointmentByPatientId(Long patientId) throws HmsException {
        return appointmentRepo.findByPatientId(patientId)
                .stream()
                .map(appointment -> {
                    DoctorDto doctorDto = profileClient.getDoctorById(appointment.getDoctorId());
                    appointment.setDoctorName(doctorDto.getName());
                    return  appointment;
                }).toList();
    }

    @Override
    public List<AppointmentDetails> getAllAppointmentByDoctorId(Long doctorId) throws HmsException {
        return appointmentRepo.findByDoctorId(doctorId)
                .stream()
                .map(appointment -> {
                    PatientDto patientDto = profileClient.getPatientById(appointment.getPatientId());
                    appointment.setPatientName(patientDto.getName());
                    appointment.setPatientEmail(patientDto.getEmail());
                    appointment.setPatientPhone(patientDto.getPhone());
                    return appointment;
                }).toList();
    }
}
