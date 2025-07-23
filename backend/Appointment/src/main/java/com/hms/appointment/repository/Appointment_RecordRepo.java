package com.hms.appointment.repository;

import com.hms.appointment.entity.Appointment_Record;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Appointment_RecordRepo extends JpaRepository<Appointment_Record, Long> {
    Optional<Appointment_Record> findByAppointment_Id(Long appointmentId);

    List<Appointment_Record> findByPatientId(Long patientId);

    Boolean existsByAppointment_Id(Long appointmentId);
}
