package com.capgemini.wsb.fitnesstracker.user.internal;

import jakarta.annotation.Nullable;

/**
 *
 * @param id
 * @param firstName
 * @param lastName
 */
public record UserSimpleDto(@Nullable Long id, String firstName, String lastName) {}
