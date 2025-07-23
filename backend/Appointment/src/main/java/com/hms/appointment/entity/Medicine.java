package com.hms.appointment.entity;


import com.hms.appointment.dto.MedicineDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long medicineId; // Assuming this is a unique identifier for the medicine
    private String dosage;
    private String frequency;
    private Integer duration;
    private String instructions;
    private String routes;
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription; // Assuming a Medicine can belong to a Prescription

    public MedicineDto toDto(){
        return new MedicineDto(id, name, medicineId, dosage, frequency, duration, instructions, routes, type, prescription.getId());
    }
}
