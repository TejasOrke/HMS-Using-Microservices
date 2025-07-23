package com.hms.appointment.repository;

import com.hms.appointment.entity.Prescription;
import com.hms.appointment.exception.HmsException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepo extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findByAppointment_Id(Long appointmentId) throws HmsException;
    List<Prescription> findByPatientId(Long patientId) throws HmsException ;

   List<Prescription> findAllByPatientId(Long patientId) throws HmsException;
}
