import Datamaskin.product.ProductValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testComponentName {

    @Test
    public void testValidComponentName() {
        assertTrue(ProductValidator.validateName("Skjermkort XS123"));
        assertTrue(ProductValidator.validateName("Tastatur 3"));
        assertTrue(ProductValidator.validateName("Vaniljerød"));
    }

    @Test
    public void testInvalidComponentName() {
        assertFalse(ProductValidator.validateName(""));
        assertFalse(ProductValidator.validateName(" "));
        assertFalse(ProductValidator.validateName("123"));
        assertFalse(ProductValidator.validateName("abcdefghijklmnopqrstuvwxyzæøåab"));
        assertFalse(ProductValidator.validateName("test@"));
    }
}
