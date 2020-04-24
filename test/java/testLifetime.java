import Datamaskin.exceptions.InvalidLifetimeException;
import Datamaskin.product.ProductValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testLifetime {

    @Test
    public void testValidLifetime() throws InvalidLifetimeException {
        assertTrue(ProductValidator.validateLifetime("1"));
        assertTrue(ProductValidator.validateLifetime("12"));
        assertTrue(ProductValidator.validateLifetime("23"));
    }

    @Test
    public void testInvalidLifetime() throws InvalidLifetimeException {
        assertFalse(ProductValidator.validateLifetime("-1"));
        assertFalse(ProductValidator.validateLifetime("100"));
        assertFalse(ProductValidator.validateLifetime("hei"));
    }
}
