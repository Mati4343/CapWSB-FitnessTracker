package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

/**
 *
 * @param id
 * @param firstName
 * @param lastName
 */
public record UserDtoSimple(@Nullable Long id, String firstName, String lastName){

}
