package com.hms.appointment.service;

import com.hms.appointment.dto.DoctorDto;
import com.hms.appointment.dto.PatientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ApiService {


    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Boolean> isDoctorExists(Long doctorId){
        return webClientBuilder.build().get()
                .uri("http://localhost:8081/profile/doctor/exists/" +  doctorId)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    public Mono<Boolean> isPatientExists(Long patientId){
        return webClientBuilder.build().get()
                .uri("http://localhost:8081/profile/patient/exists/" +  patientId)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    public Mono<DoctorDto> getDoctorById(Long doctorId) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8081/profile/doctor/get/" + doctorId)
                .retrieve()
                .bodyToMono(DoctorDto.class);
    }

    public Mono<PatientDto> getPatientById(Long patientId) {
        return webClientBuilder.build().get()
                .uri("http://localhost:8081/profile/patient/get/" + patientId)
                .retrieve()
                .bodyToMono(PatientDto.class);
    }
}
