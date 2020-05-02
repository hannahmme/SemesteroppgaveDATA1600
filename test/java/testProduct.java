import datamaskin.product.ProductValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testProduct {

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

    @Test
    public void testValidDescription() {
        assertTrue(ProductValidator.validateDescription("Beste på markedet"));
        assertTrue(ProductValidator.validateDescription("Superbra tastatur."));
        assertTrue(ProductValidator.validateDescription("Bedre enn noen andre!"));
    }

    @Test
    public void testInvalidDescription() {
        assertFalse(ProductValidator.validateDescription(""));
        assertFalse(ProductValidator.validateDescription(" "));
        assertFalse(ProductValidator.validateDescription("123"));
        assertFalse(ProductValidator.validateDescription("test@"));
    }

    @Test
    public void testValidLifetime() {
        assertTrue(ProductValidator.validateLifetime("1"));
        assertTrue(ProductValidator.validateLifetime("12"));
        assertTrue(ProductValidator.validateLifetime("23"));
    }

    @Test
    public void testInvalidLifetime() {
        assertFalse(ProductValidator.validateLifetime("-1"));
        assertFalse(ProductValidator.validateLifetime("100"));
        assertFalse(ProductValidator.validateLifetime("hei"));
    }

    @Test
    public void testValidPrice() {
        assertTrue(ProductValidator.validatePrice("100"));
        assertTrue(ProductValidator.validatePrice("149.90"));
        assertTrue(ProductValidator.validatePrice("23.5"));
        assertTrue(ProductValidator.validatePrice("9999"));
        assertTrue(ProductValidator.validatePrice("99999.99"));
    }

    @Test
    public void testInvalidPrice() {
        assertFalse(ProductValidator.validatePrice("-1"));
        assertFalse(ProductValidator.validatePrice("12,5"));
        assertFalse(ProductValidator.validatePrice("100000"));
        assertFalse(ProductValidator.validatePrice("123..4"));
        assertFalse(ProductValidator.validatePrice("123.123"));
        assertFalse(ProductValidator.validatePrice(".123"));
    }
}
