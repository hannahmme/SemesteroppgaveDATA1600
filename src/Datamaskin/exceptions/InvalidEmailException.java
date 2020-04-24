package Datamaskin.exceptions;

import java.io.IOException;

public class InvalidEmailException extends IOException {
    public InvalidEmailException (String msg){
        super(msg);
    }
}

