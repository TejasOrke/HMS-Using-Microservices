package com.hms.user.service;

import com.hms.user.clients.ProfileClient;
import com.hms.user.dto.Roles;
import com.hms.user.dto.UserDto;
import com.hms.user.entity.User;
import com.hms.user.exception.HmsException;
import com.hms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApiService apiService;

    @Autowired
    private ProfileClient profileClient;

    @Override
    public void registerUser(UserDto userDto) throws HmsException {
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if (byEmail.isPresent()) {
            throw new HmsException("USER_ALREADY_EXISTS");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        Long block = apiService.addProfile(userDto).block();
        Long block = null;
        if(userDto.getRole().equals(Roles.DOCTOR)){
            block = profileClient.addDoctor(userDto);
        }else if(userDto.getRole().equals(Roles.PATIENT)) {
            block = profileClient.addPatient(userDto);
        }
        System.out.println(block);
        userDto.setProfileId(block);
        userRepository.save(userDto.toEntity());
    }

    @Override
    public UserDto loginUser(UserDto userDto) throws HmsException {
//        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new HmsException("USER_NOT_FOUND"));
        if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new HmsException("INVALID_CREDENTIALS");
        }
        user.setPassword(null);
        return user.toDto();
    }

    @Override
    public UserDto getUserById(Long userId) throws HmsException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new HmsException("USER_NOT_FOUND"))
                .toDto();
    }

    @Override
    public void updateUser(UserDto userDto) {

    }

    @Override
    public UserDto getUser(String email) throws HmsException {
        return userRepository.findByEmail(email).orElseThrow(() -> new HmsException("USER_NOT_FOUND"))
                .toDto();

    }
}
