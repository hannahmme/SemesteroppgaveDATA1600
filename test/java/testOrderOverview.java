import datamaskin.orders.OrderValidator;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testOrderOverview {
    @Test
    public void testValidOrderID() {
        assertTrue(OrderValidator.validateOrderID("ordre-1"));
        assertTrue(OrderValidator.validateOrderID("ordre-11"));
        assertTrue(OrderValidator.validateOrderID("ordre-111"));
        assertTrue(OrderValidator.validateOrderID("ordre-1111"));
        assertTrue(OrderValidator.validateOrderID("ordre-11111"));
    }

    @Test
    public void testInvalidOrderID() {
        assertFalse(OrderValidator.validateOrderID("-11"));
        assertFalse(OrderValidator.validateOrderID("ordre"));
        assertFalse(OrderValidator.validateOrderID("ordre-11111s"));
    }

    @Test
    public void testValidDate() {
        assertTrue(OrderValidator.validateDate("2020-02-29"));
        assertTrue(OrderValidator.validateDate("2019-01-01"));
        assertTrue(OrderValidator.validateDate("2020-12-31"));
        assertTrue(OrderValidator.validateDate("2010-01-01"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(OrderValidator.validateDate("-2020-02-02"));
        assertFalse(OrderValidator.validateDate("20190820"));
        assertFalse(OrderValidator.validateDate("2020.01.01"));
        assertFalse(OrderValidator.validateDate("2019-02-29"));
        assertFalse(OrderValidator.validateDate("2009-08-20"));
        assertFalse(OrderValidator.validateDate("2020-13-01"));
    }


    @Test
    public void testValidTotalprice() {
        assertTrue(OrderValidator.validateTotalPrice(2000, 2000));
        assertTrue(OrderValidator.validateTotalPrice(99999.99, 99999.99));
        assertTrue(OrderValidator.validateTotalPrice(1,1));
    }

    @Test
    public void testInvalidTotalprice() {
        assertFalse(OrderValidator.validateTotalPrice(-1,-1));
        assertFalse(OrderValidator.validateTotalPrice(2000,2000.01));
        assertFalse(OrderValidator.validateTotalPrice(100000,10000));
    }
}
