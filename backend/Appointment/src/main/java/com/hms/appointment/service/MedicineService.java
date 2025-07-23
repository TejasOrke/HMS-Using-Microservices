package com.hms.appointment.service;

import com.hms.appointment.dto.MedicineDto;
import com.hms.appointment.exception.HmsException;

import java.util.List;

public interface MedicineService {

    public Long saveMedicine(MedicineDto medicineDto) throws HmsException;
    public List<MedicineDto> saveAllMedicines(List<MedicineDto> medicineDto) throws HmsException;
    public List<MedicineDto> getAllMedicinesByPrescriptionId(Long prescriptionId) throws HmsException;
}
