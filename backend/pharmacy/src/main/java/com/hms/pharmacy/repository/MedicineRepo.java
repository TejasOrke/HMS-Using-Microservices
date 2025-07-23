package com.hms.pharmacy.repository;

import com.hms.pharmacy.entity.Medicine;
import com.hms.pharmacy.exception.HmsException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicineRepo extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByNameIgnoreCaseAndDosageIgnoreCase(String Name, String Dosage) throws HmsException;

}
