package com.hms.appointment.dto;


import com.hms.appointment.entity.Medicine;
import com.hms.appointment.entity.Prescription;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {
    private Long id;
    private String name;
    private Long medicineId; // Unique identifier for the medicine
    private String dosage;
    private String frequency;
    private Integer duration;
    private String instructions;
    private String routes;
    private String type;
    private Long prescriptionId; //

    public Medicine toEntity(){
        return new Medicine(id, name, medicineId, dosage, frequency, duration, instructions, routes, type, new Prescription(prescriptionId));
    }
}
