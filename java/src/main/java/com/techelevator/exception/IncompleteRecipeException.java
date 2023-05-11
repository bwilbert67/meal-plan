package com.techelevator.exception;

public class IncompleteRecipeException extends RuntimeException{
    public IncompleteRecipeException() {
        super("Make sure all fields in your recipe are complete!");
    }
}
