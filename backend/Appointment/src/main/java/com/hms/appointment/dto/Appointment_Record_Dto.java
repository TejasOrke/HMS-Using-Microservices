package com.hms.appointment.dto;

import com.hms.appointment.entity.Appointment;
import com.hms.appointment.entity.Appointment_Record;
import com.hms.appointment.utility.StringListConverter;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment_Record_Dto {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Long appointmentId;
    private List<String> symptoms;
    private String diagnosis;
    private List<String> tests;
    private String notes;
    private String referral;
    private PrescriptionDto prescriptionDto;
    private LocalDate followUpdate;
    private LocalDateTime createdAt;

    public Appointment_Record toEntity() {
        return new Appointment_Record(id, patientId, doctorId, new Appointment(appointmentId), StringListConverter.convertToString(symptoms), diagnosis, StringListConverter.convertToString(tests), notes, referral, followUpdate, createdAt
        );
    }
}
