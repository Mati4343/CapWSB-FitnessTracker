package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import com.capgemini.wsb.fitnesstracker.user.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User updateUser(final User user) {
        log.info("Updating User {}", user);
        if (user.getId() == null) {
            throw new IllegalArgumentException("User has no DB ID, create is not permitted!");
        }
        return userRepository.save(user);
    }

    /**
     *
     * @param userId
     */
    @Override
    public void removeUser(final Long userId) {
        log.info("Deleting User with ID {}", userId);
        userRepository.deleteById(userId);
    }

    /**
     *
     * @param userId id of the user to be searched
     * @return
     */
    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    /**
     *
     * @param date
     * @return
     */
    @Override
    public List<User> getUsersOlderThan(final LocalDate date) {
        return userRepository.findByBirthDateBefore(date);
    }

    /**
     *
     * @param email The email of the user to be searched
     * @return
     */
    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public List<User> getUserByEmailIgnoreCase(final String email) {
        return userRepository.findByEmailFragmentIgnoreCase(email);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}