package com.hms.pharmacy.service;

import com.hms.pharmacy.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    public Long addMedicine(MedicineDto medicineDto) throws Exception;

    public MedicineDto getMedicineById(Long id) throws Exception;

    public void updateMedicine(MedicineDto medicineDto) throws Exception;

    public List<MedicineDto> getAllMedicines() throws Exception;


}
