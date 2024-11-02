package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDtoSimple;
import org.springframework.stereotype.Component;

@Component
public class UserMapperSimple {
    UserDtoSimple toSimpleDto(User user) {
        return new UserDtoSimple(user.getId(), user.getFirstName(), user.getLastName());
    }
    User toSimpleEntity(UserDtoSimple userDto) {
        return new User(userDto.firstName(), userDto.lastName(), null, null);
    }
}
