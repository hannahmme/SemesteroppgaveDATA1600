import datamaskin.orders.OrderValidator;
import datamaskin.users.AdminValidator;
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
        assertTrue(OrderValidator.validateDate("2020-08-22"));
        assertTrue(OrderValidator.validateDate("2019-01-01"));
        assertTrue(OrderValidator.validateDate("2020-12-31"));
        assertTrue(OrderValidator.validateDate("2016-01-31"));
    }

    @Test
    public void testInvalidDate() {
        assertFalse(OrderValidator.validateDate("-2020-02-02"));
        assertFalse(OrderValidator.validateDate("20190820"));
        assertFalse(OrderValidator.validateDate("2020.01.01"));
        assertFalse(OrderValidator.validateDate("2020-02-32"));
        assertFalse(OrderValidator.validateDate("2013-08-20"));
        assertFalse(OrderValidator.validateDate("2020-13-01"));


    }



}
