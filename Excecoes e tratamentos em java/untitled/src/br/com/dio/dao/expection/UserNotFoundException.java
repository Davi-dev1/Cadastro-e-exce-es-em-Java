package br.com.dio.dao.expection;

public class UserNotFoundException extends RuntimeException{

    public  UserNotFoundException(final String message){
        super(message);
    }

}
