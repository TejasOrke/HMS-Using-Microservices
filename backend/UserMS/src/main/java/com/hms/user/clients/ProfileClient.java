package com.hms.user.clients;


import com.hms.user.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ProfileMS")
public interface ProfileClient {
    @PostMapping("/profile/patient/add")
    Long addPatient(@RequestBody UserDto userDto);

    @PostMapping("/profile/doctor/add")
    Long addDoctor(@RequestBody UserDto userDto);
}
