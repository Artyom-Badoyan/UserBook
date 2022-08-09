package com.example.userbook.exceptions;

public class UserNotFoundException extends Exception{


    private String errorMessage;


    public UserNotFoundException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;

    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
