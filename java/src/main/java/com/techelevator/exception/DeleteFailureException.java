package com.techelevator.exception;

public class DeleteFailureException extends RuntimeException {

    public DeleteFailureException(String message) {
        super(message);
    }
}
