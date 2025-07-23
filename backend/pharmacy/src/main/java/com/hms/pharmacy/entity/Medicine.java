package com.hms.pharmacy.entity;

import com.hms.pharmacy.dto.MedicineDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage;
    private MedicineCategory category;
    private MedicineType type;
    private String manufacturer;
    private Integer unitPrice;
    private LocalDateTime createdAt;

    public Medicine(Long id) {
        this.id = id;
    }

    public MedicineDto toDto() {
        return new MedicineDto(id, name, dosage, category, type, manufacturer, unitPrice, createdAt);
    }
}
