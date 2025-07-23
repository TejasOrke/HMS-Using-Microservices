package com.hms.user.service;


import com.hms.user.dto.Roles;
import com.hms.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {


    @Autowired
    private WebClient.Builder webClientBuilder;


    public Mono<Long> addProfile(UserDto userDto){
        if(userDto.getRole().equals(Roles.DOCTOR)) {
            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8081/profile/doctor/add")
                    .bodyValue(userDto)
                    .retrieve()
                    .bodyToMono(Long.class);
        } else if(userDto.getRole().equals(Roles.PATIENT)) {
            return webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8081/profile/patient/add")
                    .bodyValue(userDto)
                    .retrieve()
                    .bodyToMono(Long.class);
        }

        return null;
    }

}
