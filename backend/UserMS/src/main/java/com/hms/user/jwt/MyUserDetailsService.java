package com.hms.user.jwt;

import com.hms.user.dto.UserDto;
import com.hms.user.service.UserService;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDto userDto = userService.getUser(email);
            return new CustomUserDetails(
                    userDto.getId(),
                    userDto.getEmail(),
                    userDto.getPassword(),
                    userDto.getEmail(),
                    userDto.getRole(),
                    userDto.getName(),
                    userDto.getProfileId(),
                    null
            );
        } catch (Exception e) {
//            throw new UsernameNotFoundException("User not found with email: " + email, e);
            e.printStackTrace();
        }

        return null;
    }
}
