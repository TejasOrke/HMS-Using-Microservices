package com.hms.pharmacy.controller;

import com.hms.pharmacy.dto.MedicineDto;
import com.hms.pharmacy.dto.ResponseDto;
import com.hms.pharmacy.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pharmacy/medicines")
@RequiredArgsConstructor
@CrossOrigin
public class MedicineController {

    @Autowired
    private final MedicineService medicineService;

    @PostMapping("/add")
    public ResponseEntity<Long> addMedicine(@RequestBody MedicineDto medicineDto) throws Exception {
        return new ResponseEntity<>(medicineService.addMedicine(medicineDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MedicineDto> getMedicineById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(medicineService.getMedicineById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateMedicine(@RequestBody MedicineDto medicineDto) throws Exception {
        medicineService.updateMedicine(medicineDto);
        return new ResponseEntity<>( new ResponseDto("Medicine Update"),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicineDto>> getAllMedicine() throws Exception {
        return new ResponseEntity<>(medicineService.getAllMedicines(), HttpStatus.OK);
    }
}


