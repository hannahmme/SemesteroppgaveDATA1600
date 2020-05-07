package datamaskin.product;

public class ProductValidator {
    public static boolean validateName(String name) throws IllegalArgumentException {
        if (name.trim().matches("[a-zA-ZæøåÆØÅ 0-9]{1}[a-zA-ZæøåÆØÅ. \\-0-9]{1,29}")
            && !name.trim().matches("[0-9]{1,}")
            && !name.matches("[ ]{1,}")) {
                return true;
            }
        return false;
    }

    // Beskrivelsen kan starte eller slutte med tall, men kan ikke inneholde kun tall
    public static boolean validateDescription (String description) throws IllegalArgumentException {
        if (description.trim().matches("[0-9-a-zA-ZæøåÆØÅ ]{1}[a-zA-ZæøåÆØÅ.,! 0-9]{1,79}")
                && !description.trim().matches("[0-9]{1,}")
                && !description.matches("[ ]{1,}")) {
            return true;
        }
        return false;
    }

    public static boolean validateLifetime (String lifetime){
        if (lifetime.trim().matches("[0-9]{1,2}")
                && Integer.parseInt(lifetime) > 0
                && Integer.parseInt(lifetime) < 36) {
            return true;
        }
        return false;
    }

    public static boolean validatePrice (String price){
        if (price.trim().matches("[0-9]{1,5}([.][0-9]{1,2})?")
                && Double.parseDouble(price) > 0) {
            return true;
        }
        return false;
    }

    public static boolean validateCategory (String category){
        if (category.isEmpty()) {
            return false;
        }
        return true;
    }
    }

