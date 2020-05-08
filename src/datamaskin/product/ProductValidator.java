package datamaskin.product;

public class ProductValidator {
    public static boolean validateName(String name) throws IllegalArgumentException {
        if (name.trim().matches("[a-zA-ZæøåÆØÅ 0-9]{1}[a-zA-ZæøåÆØÅ. \\-0-9]{1,29}")
            && !name.trim().matches("[0-9]+")
            && !name.matches("[ ]+")) {
                return true;
            }
        return false;
    }

    // Beskrivelsen kan starte eller slutte med tall, men kan ikke inneholde kun tall
    public static boolean validateDescription (String description) throws IllegalArgumentException {
        if (description.trim().matches("[0-9-a-zA-ZæøåÆØÅ ]{1}[a-zA-ZæøåÆØÅ.,! 0-9]{1,79}")
                && !description.trim().matches("[0-9]+")
                && !description.matches("[ ]+")) {
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

    public static boolean validateCategory (String category) {
        if (category.trim().toLowerCase().equals("skjermkort") || category.trim().toLowerCase().equals("minnekort") ||
                category.trim().toLowerCase().equals("harddisk") || category.trim().toLowerCase().equals("lydkort") ||
                category.trim().toLowerCase().equals("strømforsyning") || category.trim().toLowerCase().equals("optisk disk") ||
                category.trim().toLowerCase().equals("farge") || category.trim().toLowerCase().equals("andre produkter") ||
                category.trim().toLowerCase().equals("prosessor")) {
            return true;
        }
        return false;
    }
}

