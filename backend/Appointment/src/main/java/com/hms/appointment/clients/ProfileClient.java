package com.hms.appointment.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.appointment.dto.DoctorDto;
import com.hms.appointment.dto.DoctorName;
import com.hms.appointment.dto.PatientDto;

@FeignClient(name = "profileMS")
public interface ProfileClient {

    @GetMapping("/profile/doctor/exists/{id}")
    Boolean doctorExists(@PathVariable("id") Long id);

    @GetMapping("/profile/patient/exists/{id}")
    Boolean patientExists(@PathVariable("id") Long id);

    @GetMapping("/profile/patient/get/{id}")
    PatientDto getPatientById(@PathVariable("id") Long id);

    @GetMapping("/profile/doctor/get/{id}")
    DoctorDto getDoctorById(@PathVariable("id") Long id);

    @GetMapping("/profile/doctor/getDoctorsById")
    List<DoctorName> getDoctorsById(@RequestParam List<Long> ids);
}
