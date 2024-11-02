package com.capgemini.wsb.fitnesstracker.user.api;

import jakarta.annotation.Nullable;

public record EmailUserSimpleDto(@Nullable Long id, String email) {
}
