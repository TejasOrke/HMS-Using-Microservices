package com.hms.user.service;


import com.hms.user.dto.UserDto;
import com.hms.user.exception.HmsException;

public interface UserService {
    public void registerUser(UserDto userDto) throws HmsException;
    public UserDto loginUser(UserDto userDto) throws HmsException;
    public UserDto getUserById(Long userId) throws HmsException;
    public void updateUser(UserDto userDto);
    public UserDto getUser(String email) throws HmsException;
}
