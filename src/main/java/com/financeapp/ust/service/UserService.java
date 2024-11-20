package com.financeapp.ust.service;

import com.financeapp.ust.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto updateUserPassword(UserDto userDto);

    void deleteUser(int id);

    String login(UserDto userDto);

    UserDto getUserDetailsById(int id);
}
