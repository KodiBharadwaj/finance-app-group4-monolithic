package com.financeapp.ust.service;

import com.financeapp.ust.dto.UserDto;
import com.financeapp.ust.model.User;
import com.financeapp.ust.repository.*;
import com.financeapp.ust.util.UserEntityConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private GoalsRepository goalsRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public UserDto save(UserDto userDto) {
        User user = UserEntityConversion.DtoToEntityConversion(userDto);

        if (userRepository.existsById(user.getId())) {
            throw new RuntimeException("User with id " + user.getId() + " is already exists!");
        }
        String rawPassword = user.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(hashedPassword);
        user.setIncomes(null);
        User user1 = userRepository.save(user);
        return UserEntityConversion.EntityToDtoConversion(user1);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(i -> UserEntityConversion.EntityToDtoConversion(i))
                .sorted((a, b) -> a.id() - b.id())
                .toList();
    }

    @Override
    public UserDto updateUserPassword(UserDto userDto) {
        if (!userRepository.existsById(userDto.id())) {
            return null;
        }

        User user = userRepository.findById(userDto.id()).get();
        user.setPassword(userDto.password());
        return save(UserEntityConversion.EntityToDtoConversion(user));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public String login(UserDto userDto) {
        User user = userRepository.findById(userDto.id()).get();
        System.out.println(user);
        if (!user.getEmail().equals(userDto.email())) {
            return "Email is incorrect!";
        }
        if (!user.getPhoneNo().equals(userDto.phoneNo())) {
            return "Phone Number is incorrect";
        }
        boolean match = passwordEncoder.matches(userDto.password(), user.getPassword());
        if (!match) {
            return "Password is incorrect";
        }
        return "Login Successful";
    }

    @Override
    public UserDto getUserDetailsById(int id) {
        return userRepository.findById(id).map(i -> UserEntityConversion.EntityToDtoConversion(i)).orElse(null);
    }
}
