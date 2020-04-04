package Datamaskin.Exceptions;

public class InvalidLifetimeException extends Exception{
    public InvalidLifetimeException(String msg){
        super(msg);     //kaller på konstruktøren til klassen den arver fra
    }
}

