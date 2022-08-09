package com.example.userbook.exceptions;

public class UserAlreadyExistException extends Exception{


    private String errorMessage;


    public UserAlreadyExistException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;

    }


    public String getErrorMessage() {
        return errorMessage;
    }
}
