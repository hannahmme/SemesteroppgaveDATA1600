import Datamaskin.exceptions.InvalidPriceException;
import Datamaskin.product.ProductValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testPrice {

    @Test
    public void testValidPrice() throws InvalidPriceException {
        assertTrue(ProductValidator.validatePrice("100"));
        assertTrue(ProductValidator.validatePrice("149.90"));
        assertTrue(ProductValidator.validatePrice("23.5"));
        assertTrue(ProductValidator.validatePrice("9999"));
    }

    @Test
    public void testInvalidPrice() throws InvalidPriceException {
        assertFalse(ProductValidator.validatePrice("-1"));
        assertFalse(ProductValidator.validatePrice("12,5"));
        assertFalse(ProductValidator.validatePrice("10000"));
        assertFalse(ProductValidator.validatePrice("123..4"));
        assertFalse(ProductValidator.validatePrice("123.123"));
        assertFalse(ProductValidator.validatePrice(".123"));
    }
}
