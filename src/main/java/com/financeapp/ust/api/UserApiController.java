package com.financeapp.ust.api;

import com.financeapp.ust.dto.UserDto;
import com.financeapp.ust.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://127.0.0.1:5500")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.save(userDto);
        if (userDto1 != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userDto1);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> list = userService.getAllUsers();
        if (!list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUserPassword(@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.updateUserPassword(userDto);
        if (userDto1 != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loinUser(@RequestBody UserDto userDto) {
        String str = userService.login(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(str);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetailsById(@PathVariable("id") int id) {
        UserDto userDto = userService.getUserDetailsById(id);
        if (userDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
