package dev.abhisek.userservice.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Selected Resource not found...");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
