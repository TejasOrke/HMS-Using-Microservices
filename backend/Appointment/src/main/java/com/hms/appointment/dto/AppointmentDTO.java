package com.hms.appointment.dto;

import com.hms.appointment.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentDateTime;
    private Status status;
    private String reason;
    private String notes;

    public Appointment toEntity(){
        return new Appointment(this.id, this.patientId, this.doctorId, this.appointmentDateTime, this.status, this.reason, this.notes);
    }
}
