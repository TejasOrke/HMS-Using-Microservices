package com.hms.profile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hms.profile.dto.DoctorDropdownDto;
import com.hms.profile.dto.DoctorDto;
import com.hms.profile.exception.HmsException;
import com.hms.profile.service.DoctorService;

@RestController
@CrossOrigin
@RequestMapping("/profile/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<Long> addDoctor(@RequestBody DoctorDto doctorDto) throws HmsException {
        return new ResponseEntity<>(doctorService.addDoctor(doctorDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto doctorDto) throws HmsException {
        return new ResponseEntity<>(doctorService.updateDoctor(doctorDto), HttpStatus.OK);
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> isDoctorExists(@PathVariable Long id) throws HmsException {
        return new ResponseEntity<>(doctorService.isDoctorExists(id), HttpStatus.OK);
    }

//    @GetMapping("/dropdowns")
//    public ResponseEntity<List<DoctorDropdownDto>> getDoctorDropdowns() throws HmsException {
//        List<DoctorDropdown> projections = doctorService.getDoctorDropdown();
//
//        List<DoctorDropdownDto> dtos = projections.stream()
//                .map(p -> new DoctorDropdownDto(p.id(), p.name()))
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(dtos);
//    }
    @GetMapping("/dropdowns")
    public ResponseEntity<List<DoctorDropdownDto>> getDoctorDropdowns() throws HmsException {
        return new ResponseEntity<>(doctorService.getDoctorDropdown(), HttpStatus.OK);
    }

    @GetMapping("/getDoctorsById")
    public ResponseEntity<List<DoctorDropdownDto>> getDoctorsById(@RequestParam List<Long> ids) throws HmsException {
        return new ResponseEntity<>(doctorService.getDoctorsById(ids), HttpStatus.OK);
    }

}
