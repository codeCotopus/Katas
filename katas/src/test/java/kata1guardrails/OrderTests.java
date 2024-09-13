package kata1guardrails;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTests {

    @Test
    public void testProcessOrder_InvalidPaymentMethod() {
        OrderService orderService = new OrderService() {
            @Override
            protected boolean isPaymentMethodValid(Order order) {
                return false;
            }
        };
        Order order = new Order(orderService);
        String result = order.processOrder();
        assertEquals("Invalid payment method.", result);
    }

    @Test
    public void testProcessOrder_ItemsOutOfStock() {
        OrderService orderService = new OrderService() {
            @Override
            protected boolean areItemsInStock(Order order) {
                return false;
            }
        };
        Order order = new Order(orderService);
        String result = order.processOrder();
        assertEquals("One or more items are out of stock.", result);
    }

    @Test
    public void testProcessOrder_InvalidShippingAddress() {
        OrderService orderService = new OrderService() {
            @Override
            protected boolean isShippingAddressValid(Order order) {
                return false;
            }
        };
        Order order = new Order(orderService);
        String result = order.processOrder();
        assertEquals("Invalid shipping address.", result);
    }

    @Test
    public void testProcessOrder_Success() {
        OrderService orderService = new OrderService();
        Order order = new Order(orderService);
        String result = order.processOrder();
        assertEquals("Order processed successfully.", result);
    }

    @Test
    public void testOrderConstructor_NullOrderService() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Order(null);
        });
        assertEquals("OrderService cannot be null.", exception.getMessage());
    }
}