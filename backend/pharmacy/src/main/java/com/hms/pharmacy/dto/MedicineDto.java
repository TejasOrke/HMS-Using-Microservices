package com.hms.pharmacy.dto;

import com.hms.pharmacy.entity.Medicine;
import com.hms.pharmacy.entity.MedicineCategory;
import com.hms.pharmacy.entity.MedicineType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
    private Long id;
    private String name;
    private String dosage;
    private MedicineCategory category;
    private MedicineType type;
    private String manufacturer;
    private Integer unitPrice;
    private LocalDateTime createdAt;

    public Medicine toEntity() {
        return new Medicine(id, name, dosage, category, type, manufacturer, unitPrice, createdAt);
    }
}
