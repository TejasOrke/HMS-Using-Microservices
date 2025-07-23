package com.hms.profile.dto;

public class DoctorDropdownDto {
    private Long id;
    private String name;

    public DoctorDropdownDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
