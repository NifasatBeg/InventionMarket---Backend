package com.personal.InventionMarket.exception.userExceptions;

public class ResourceDetailsInvalidException extends RuntimeException{
    public ResourceDetailsInvalidException(String errorMessage){
        super(errorMessage);
    }
}
