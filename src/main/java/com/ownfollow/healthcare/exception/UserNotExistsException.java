package com.ownfollow.healthcare.exception;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException(String username) {
        super(String.format("UserInfo with username `%s` not exists", username));
    }
}
