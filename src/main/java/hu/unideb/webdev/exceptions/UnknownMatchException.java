package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Matches;

public class UnknownMatchException extends Exception{
    private Matches match;

    public UnknownMatchException(Matches match){this.match=match;}

    public UnknownMatchException(String message, Matches match){
        super(message);
        this.match=match;
    }

    public UnknownMatchException(String message){super(message);}
}
