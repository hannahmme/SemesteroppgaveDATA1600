package Datamaskin.Product;

import Datamaskin.Exceptions.InvalidLifetimeException;
import Datamaskin.Exceptions.InvalidPriceException;

public class ProductValidator {
    public static String validateName(String name) throws IllegalArgumentException {
        if (name.matches("[a-zA-ZæøåÆØÅ. \\-0-9]*")&& name.length()<30 && !name.equals("")) {
            return name;
        }
        throw new IllegalArgumentException("Skriv inn et gyldig navn");
    }


    public static String validateDescription(String description) throws IllegalArgumentException {
        if (description.matches("[a-zA-ZæøåÆØÅ. \\-0-9]*") && description.length()<200 && !description.equals("")) {
            return description;
        }
        throw new IllegalArgumentException("Skriv inn en gyldig beskrivelse");
    }

    public static boolean validateLifetime (int lifetime) throws InvalidLifetimeException {
        if (lifetime > 0 && lifetime < 36){
            return true;
        }
        return false;
    }

    public static Double validatePrice (double price) throws InvalidPriceException {
        if (price > 0 && price < 10000){
            return price;
        }
        throw new InvalidPriceException("Skriv inn en gyldig pris");
    }


    public static String validateCategory (String category){
        if(category.isEmpty()){
            throw new IllegalArgumentException("Vennligst velg kategori" +
                    "!");
        }
        return category;
    }
}
