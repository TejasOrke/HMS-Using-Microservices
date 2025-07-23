package com.hms.appointment.repository;

import com.hms.appointment.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepo extends JpaRepository<Medicine, Long> {
    List<Medicine> findAllByPrescription_Id(Long prescriptionId);
}
