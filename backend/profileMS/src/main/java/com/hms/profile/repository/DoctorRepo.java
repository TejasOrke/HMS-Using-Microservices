package com.hms.profile.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hms.profile.dto.DoctorDropdown;
import com.hms.profile.entity.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

    Optional<Doctor> findByLicenseNumber(String licenseNumber);

    @Query("SELECT d.id AS id, d.name AS name FROM Doctor d")
    List<DoctorDropdown> findAllDoctorDropDown();

    @Query("SELECT d.id AS id, d.name AS name FROM Doctor d WHERE d.id IN ?1")
    List<DoctorDropdown> findAllDoctorDropDownByIds(List<Long> ids);
}
