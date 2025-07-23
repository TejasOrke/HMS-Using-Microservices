package com.hms.profile.entity;


import com.hms.profile.dto.BloodGroup;
import com.hms.profile.dto.DoctorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private LocalDate dob;
    private String phone;
    private String address;
    @Column(unique = true)
    private String licenseNumber; // ✅ match the method name;
    private String specialization;
    private String department;
    private Integer totalExp;

    public DoctorDto doctorDto(){
        return new DoctorDto(this.id, this.name, this.email, this.dob, this.phone, this.address,
                             this.licenseNumber, this.specialization, this.department, this.totalExp);
    }

}
