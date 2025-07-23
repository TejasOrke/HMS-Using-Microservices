package com.hms.appointment.service;

import com.hms.appointment.dto.MedicineDto;
import com.hms.appointment.entity.Medicine;
import com.hms.appointment.exception.HmsException;
import com.hms.appointment.repository.MedicineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MedicineServiceImpl implements MedicineService{
    private final MedicineRepo medicineRepo;

    @Override
    public Long saveMedicine(MedicineDto medicineDto) throws HmsException {
        return medicineRepo.save(medicineDto.toEntity()).getId();
    }

    @Override
    public List<MedicineDto> saveAllMedicines(List<MedicineDto> medicineDto) throws HmsException {
        return ((List<Medicine>) medicineRepo.saveAll(medicineDto.stream().map(MedicineDto::toEntity).toList()))
                .stream().map(Medicine::toDto).toList();
    }

    @Override
    public List<MedicineDto> getAllMedicinesByPrescriptionId(Long prescriptionId) throws HmsException {
        return ((List<Medicine>) medicineRepo.findAllByPrescription_Id(prescriptionId))
                .stream()
                .map(Medicine::toDto).toList();
    }
}
