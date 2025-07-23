package com.hms.pharmacy.repository;

import com.hms.pharmacy.entity.MedicineInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineInventoryRepo extends JpaRepository<MedicineInventory, Long> {

}
