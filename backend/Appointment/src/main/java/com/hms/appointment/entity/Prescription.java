package com.hms.appointment.entity;


import com.hms.appointment.dto.PrescriptionDetails;
import com.hms.appointment.dto.PrescriptionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    private LocalDate prescriptionDate;
    private String notes;


    public Prescription(Long id){
        this.id = id;
    }

    public PrescriptionDto toDto(){
        return new PrescriptionDto(id, patientId, doctorId, appointment.getId(), prescriptionDate, notes, null);
    }

    public PrescriptionDetails toPrescriptionDetails() {
        return new PrescriptionDetails(id, patientId, doctorId, null, appointment.getId(), prescriptionDate, notes, null);
    }

}
