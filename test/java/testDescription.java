import datamaskin.product.ProductValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testDescription {

    @Test
    public void testValidDescription() {
        assertTrue(ProductValidator.validateDescription("Beste på markedet"));
        assertTrue(ProductValidator.validateDescription("Superbra tastatur"));
        assertTrue(ProductValidator.validateDescription("Bedre enn noen andre"));
    }

    @Test
    public void testInvalidDescription() {
        assertFalse(ProductValidator.validateDescription(""));
        assertFalse(ProductValidator.validateDescription(" "));
        assertFalse(ProductValidator.validateDescription("123"));
        assertFalse(ProductValidator.validateDescription("test@"));
    }
}
