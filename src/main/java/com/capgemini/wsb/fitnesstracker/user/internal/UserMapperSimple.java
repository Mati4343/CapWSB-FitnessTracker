package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserDtoSimple;
import org.springframework.stereotype.Component;

@Component
public class UserMapperSimple {
    /**
     *
     * @param user
     * @return
     */
    UserDtoSimple toSimpleDto(User user) {
        return new UserDtoSimple(user.getId(), user.getFirstName(), user.getLastName());
    }

    /**
     *
     * @param userDto
     * @return
     */
    User toSimpleEntity(UserDtoSimple userDto) {
        return new User(userDto.firstName(), userDto.lastName(), null, null);
    }
}
