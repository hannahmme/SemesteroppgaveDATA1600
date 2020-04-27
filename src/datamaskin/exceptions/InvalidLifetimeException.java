package datamaskin.exceptions;

import java.io.IOException;

public class InvalidLifetimeException extends IOException {
    public InvalidLifetimeException(String msg){
        super(msg);     //kaller på konstruktøren til klassen den arver fra
    }
}

