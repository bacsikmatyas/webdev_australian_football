package hu.unideb.webdev.exceptions;

import lombok.Data;

@Data
public class WrongValueException extends Exception{
    public WrongValueException(String message){super(message);}
}
