package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.EmailUserSimpleDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDtoSimple;
import com.capgemini.wsb.fitnesstracker.user.api.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    private final UserMapperSimple userMapperSimple;
    private final UserMapperEmailSimple userMapperEmailSimple;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("/simple")
    public List<UserDtoSimple> getAllUsersSimple() {
        return userService.findAllUsers()
                .stream()
                .map(userMapperSimple::toSimpleDto)
                .toList();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
    }

    @GetMapping("/email")
    public List<EmailUserSimpleDto> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmailIgnoreCase(email)
                .stream()
                .map(userMapperEmailSimple::emailSimpleDto)
                .toList();
    }

    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate time) {
        return userService.getUsersOlderThan(time)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody UserDto userDto) throws InterruptedException {

        try {
            User user = userMapper.toEntity(userDto);
            userService.createUser(user);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("We have a problem to add user with email: " + userDto.email() + ". Error message: " + e.getMessage());
        }
        // how to use @RequestBody
        //System.out.println("User with e-mail: " + userDto.email() + "passed to the request");

        // TODO: saveUser with Service and return User
        return null;
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        try{
            User searchUser = userService.getUser(userId).orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
            User updateUser = userMapper.toUpdateEntity(userDto, searchUser);

            return userService.updateUser(updateUser);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("We have a problem to update user with id " + userId + ". Error message: " + e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId){
        try{
            userService.deleteUser(userId);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("We have a problem to delete user with id " + userId + ". Error message: " + e.getMessage());
        }
    }

}