package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapper {
    /**
     *
     * @param user
     * @return
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getFirstName(),
                           user.getLastName(),
                           user.getBirthdate(),
                           user.getEmail());
    }

    /**
     *
     * @param userDto
     * @return
     */
    User toEntity(UserDto userDto) {
        return new User(
                        userDto.firstName(),
                        userDto.lastName(),
                        userDto.birthdate(),
                        userDto.email());
    }

    /**
     * 
     * @param userDto
     * @param user
     * @return
     */
    User toUpdateEntity(UserDto userDto, User user) {
        if(userDto.firstName() != null) {
            user.setFirstName(userDto.firstName());
        }

        if(userDto.lastName() != null) {
            user.setLastName(userDto.lastName());
        }

        if(userDto.birthdate() != null) {
            user.setBirthdate(userDto.birthdate());
        }

        if(userDto.email() != null) {
            user.setEmail(userDto.email());
        }

        return user;
    }
}
