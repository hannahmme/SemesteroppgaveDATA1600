
//todo: Brukes denne klassen, eller er den byttet ut med OrderFormatter?

/*package datamaskin.filbehandling;

import datamaskin.product.Product;

import java.util.List;

public class ProductFormatter {
    private static String DELIMITER = ";";

    //Metode som skriver et produkt til en String med en DELIMITER som skiller hver attributt-verdi.
    private static String formatProductToString(Product product) {
        return product.getName() + DELIMITER + product.getDescription() + DELIMITER + product.getLifetime() +
                DELIMITER + product.getPrice() + DELIMITER + product.getCategory() + DELIMITER + product.getImageUri();
    }

    //Metode som tar i mot listen over alle produkter/komponenter registrert og legger alle produktene som
    // strenger inn i en større string
    public static String formatListOfProductsToString(List<Product> productReg) {
        StringBuffer stringOfProducts = new StringBuffer();
        for(Product product : productReg) {
            stringOfProducts.append(formatProductToString(product));
            stringOfProducts.append("\n");
        }

        return stringOfProducts.toString();
    }
}*/
