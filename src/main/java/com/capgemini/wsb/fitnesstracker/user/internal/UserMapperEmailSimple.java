package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.EmailUserSimpleDto;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

@Component
class UserMapperEmailSimple {
    /**
     *
     * @param user
     * @return
     */
    EmailUserSimpleDto emailSimpleDto(User user) {
        return new EmailUserSimpleDto(user.getId(), user.getEmail());
    }

    /**
     * 
     * @param userDto
     * @return
     */
    User toEmailSimpleEntity(EmailUserSimpleDto userDto) {
        return new User(null, null, null, userDto.email());
    }
}
