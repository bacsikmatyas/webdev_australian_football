package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Matches;
import lombok.Data;

@Data
public class WrongValueException extends Exception{
    public WrongValueException(String message){super(message);}
}
