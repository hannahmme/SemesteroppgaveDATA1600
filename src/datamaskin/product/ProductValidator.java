package datamaskin.product;

public class ProductValidator {
    public static boolean validateName(String name) throws IllegalArgumentException {
        if (name.matches("[a-zA-ZæøåÆØÅ]{1}[a-zA-ZæøåÆØÅ. \\-0-9]{1,29}")) {
            return true;
        }
        return false;
    }

    // Beskrivelsen kan starte eller slutte med tall, men kan ikke inneholde kun tall
    public static boolean validateDescription(String description) throws IllegalArgumentException {
        if (description.matches("[0-9-a-zA-ZæøåÆØÅ]{1}[a-zA-ZæøåÆØÅ.,! 0-9]{1,199}") && !description.matches("[0-9]{1,}")) {
            return true;
        }
        return false;
    }

    public static boolean validateLifetime (String lifetime) {
        if (lifetime.matches("[0-9]{1,2}") && Integer.parseInt(lifetime) > 0 && Integer.parseInt(lifetime) < 36) {
            return true;
        }
        return false;
    }

    public static boolean validatePrice (String price) {
        if (price.matches("[0-9]{1,5}([.][0-9]{1,2})?") && Double.parseDouble(price) > 0){
            return true;
        }
        return false;
    }

    public static boolean validateCategory (String category){
        if(category.isEmpty()){
            return false;
        }
        return true;
    }
}
