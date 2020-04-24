package Datamaskin.exceptions;

public class InvalidPriceException extends Exception{
    public InvalidPriceException (String msg){
        super(msg);     //kaller på konstruktøren til klassen den arver fra
    }
}
