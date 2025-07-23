package com.hms.profile.service;

import java.util.List;

import com.hms.profile.dto.DoctorDropdownDto;
import com.hms.profile.dto.DoctorDto;
import com.hms.profile.exception.HmsException;

public interface DoctorService {

    public Long addDoctor(DoctorDto doctorDto) throws HmsException;

    public DoctorDto getDoctorById(Long doctorId) throws HmsException;

    public DoctorDto updateDoctor(DoctorDto doctorDto) throws HmsException;

    public Boolean isDoctorExists(Long doctorId) throws HmsException;

    public List<DoctorDropdownDto> getDoctorDropdown() throws HmsException;

    public List<DoctorDropdownDto> getDoctorsById(List<Long> ids) throws HmsException;
}
