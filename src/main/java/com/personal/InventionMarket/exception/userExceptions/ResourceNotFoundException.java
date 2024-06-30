package com.personal.InventionMarket.exception.userExceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
