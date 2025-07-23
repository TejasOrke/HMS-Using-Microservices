package com.hms.appointment.entity;

import com.hms.appointment.dto.Appointment_Record_Dto;
import com.hms.appointment.dto.RecordDetails;
import com.hms.appointment.utility.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Appointment_Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
    private String symptoms;
    private String diagnosis;
    private String tests;
    private String notes;
    private String referral;
    private LocalDate followUpdate;
    private LocalDateTime createdAt;

    public Appointment_Record_Dto toDto(){
        return new Appointment_Record_Dto(id, patientId, doctorId, appointment.getId(), StringListConverter.convertToList(symptoms), diagnosis, StringListConverter.convertToList(tests), notes, referral,null, followUpdate, createdAt);
    }

    public RecordDetails toRecordDetails() {
        return new RecordDetails(id, patientId, doctorId, null,  appointment.getId(), StringListConverter.convertToList(symptoms), diagnosis, StringListConverter.convertToList(tests), notes, referral, followUpdate, createdAt);
    }
}
