package com.hms.profile.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.profile.dto.DoctorDropdown;
import com.hms.profile.dto.DoctorDropdownDto;
import com.hms.profile.dto.DoctorDto;
import com.hms.profile.exception.HmsException;
import com.hms.profile.repository.DoctorRepo;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Override
    public Long addDoctor(DoctorDto doctorDto) throws HmsException {
        if (doctorDto.getEmail() != null && doctorRepo.findByEmail(doctorDto.getEmail()).isPresent()) {
            throw new HmsException("DOCTOR_ALREADY_EXISTS");
        }
        if (doctorDto.getLicenseNumber() != null && doctorRepo.findByLicenseNumber(doctorDto.getLicenseNumber()).isPresent()) {
            throw new HmsException("DOCTOR_LICENSE_ALREADY_EXISTS");
        }

        return doctorRepo.save(doctorDto.toEntity()).getId();
    }

    @Override
    public DoctorDto getDoctorById(Long doctorId) throws HmsException {
        return doctorRepo.findById(doctorId)
                .orElseThrow(() -> new HmsException("DOCTOR_NOT_FOUND"))
                .doctorDto();
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto) throws HmsException {
        doctorRepo.findById(doctorDto.getId()).orElseThrow(() -> new HmsException("DOCTOR_NOT_FOUND"));
        return doctorRepo.save(doctorDto.toEntity()).doctorDto();
    }

    @Override
    public Boolean isDoctorExists(Long doctorId) throws HmsException {
        return doctorRepo.existsById(doctorId);
    }

    @Override
    public List<DoctorDropdownDto> getDoctorDropdown() throws HmsException {
        List<DoctorDropdown> projections = doctorRepo.findAllDoctorDropDown();
        return projections.stream()
                .map(p -> new DoctorDropdownDto(p.getId(), p.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorDropdownDto> getDoctorsById(List<Long> ids) throws HmsException {
        List<DoctorDropdown> projections = doctorRepo.findAllDoctorDropDownByIds(ids);
        return projections.stream()
                .map(p -> new DoctorDropdownDto(p.getId(), p.getName()))
                .collect(Collectors.toList());
    }
}
