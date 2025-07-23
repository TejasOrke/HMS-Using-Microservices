package com.hms.pharmacy.service;

import com.hms.pharmacy.dto.MedicineDto;
import com.hms.pharmacy.entity.Medicine;
import com.hms.pharmacy.repository.MedicineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService{

    @Autowired
    private final MedicineRepo medicineRepo;

    @Override
    public Long addMedicine(MedicineDto medicineDto) throws Exception {
        Optional<Medicine> byNameIgnoreCaseAndDosageIgnoreCase = medicineRepo.findByNameIgnoreCaseAndDosageIgnoreCase(medicineDto.getName(), medicineDto.getDosage());
        if(byNameIgnoreCaseAndDosageIgnoreCase.isPresent()) {
            throw new Exception("MEDICINE_ALREADY_EXISTS");
        }
        medicineDto.setCreatedAt(LocalDateTime.now());
        return medicineRepo.save(medicineDto.toEntity()).getId();
    }

    @Override
    public MedicineDto getMedicineById(Long id) throws Exception {
        return medicineRepo.findById(id).orElseThrow(() -> new Exception("MEDICINE_NOT_FOUND")).toDto();
    }

    @Override
    public void updateMedicine(MedicineDto medicineDto) throws Exception {
        Medicine medicineNotFound = medicineRepo.findById(medicineDto.getId()).orElseThrow(() -> new Exception("MEDICINE_NOT_FOUND"));
        if(!medicineDto.getName().equalsIgnoreCase(medicineNotFound.getName())
        && !medicineDto.getDosage().equalsIgnoreCase(medicineNotFound.getDosage())){
            Optional<Medicine> byNameIgnoreCaseAndDosageIgnoreCase = medicineRepo.findByNameIgnoreCaseAndDosageIgnoreCase(medicineDto.getName(), medicineDto.getDosage());
            if(byNameIgnoreCaseAndDosageIgnoreCase.isPresent()) {
                throw new Exception("MEDICINE_ALREADY_EXISTS");
            }
        }

        medicineNotFound.setName(medicineDto.getName());
        medicineNotFound.setDosage(medicineDto.getDosage());
        medicineNotFound.setCategory(medicineDto.getCategory());
        medicineNotFound.setType(medicineDto.getType());
        medicineNotFound.setManufacturer(medicineDto.getManufacturer());
        medicineNotFound.setUnitPrice(medicineDto.getUnitPrice());
        medicineNotFound.setCreatedAt(medicineDto.getCreatedAt());
        medicineRepo.save(medicineNotFound);
    }

    @Override
    public List<MedicineDto> getAllMedicines() throws Exception {
        return ((List<Medicine>) medicineRepo.findAll())
                .stream()
                .map(Medicine::toDto)
                .toList();
    }
}
