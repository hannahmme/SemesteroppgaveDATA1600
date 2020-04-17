package Datamaskin.Exceptions;

import java.io.IOException;

public class InvalidEmailException extends IOException {
    public InvalidEmailException (String msg){
        super(msg);
    }
}

