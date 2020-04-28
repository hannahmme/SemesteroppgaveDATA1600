import datamaskin.users.AdminValidator;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testOrderID {
    @Test
    public void testValidOrderID() {
        assertTrue(AdminValidator.validateAdmin("admin", "admin"));
        assertTrue(AdminValidator.validateAdmin("admin", "admin"));
    }

    @Test
    public void testInvalidOrderID() {
        assertFalse(AdminValidator.validateAdmin("", "admin"));
        assertFalse(AdminValidator.validateAdmin("admin", ""));
    }
}
