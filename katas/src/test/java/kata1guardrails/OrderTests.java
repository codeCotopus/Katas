package kata1guardrails;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderTests {

    @Mock
    OrderService orderServiceMock;

    @BeforeEach
    public void setup (){
        MockitoAnnotations.openMocks(this);
        when(orderServiceMock.isPaymentMethodValid(any())).thenReturn(true);
        when(orderServiceMock.areItemsInStock(any())).thenReturn(true);
        when(orderServiceMock.isShippingAddressValid(any())).thenReturn(true);
    }

    @Test
    public void testProcessOrder_InvalidPaymentMethod() {
        when(orderServiceMock.isPaymentMethodValid(any())).thenReturn(false);

        Order order = new Order(orderServiceMock);
        String result = order.processOrder();
        assertEquals("Invalid payment method.", result);
    }

    @Test
    public void testProcessOrder_ItemsOutOfStock() {
        when(orderServiceMock.areItemsInStock(any())).thenReturn(false);

        Order order = new Order(orderServiceMock);
        String result = order.processOrder();
        assertEquals("One or more items are out of stock.", result);
    }

    @Test
    public void testProcessOrder_InvalidShippingAddress() {
        when(orderServiceMock.isShippingAddressValid(any())).thenReturn(false);

        Order order = new Order(orderServiceMock);
        String result = order.processOrder();
        assertEquals("Invalid shipping address.", result);
    }

    @Test
    public void testProcessOrder_Success() {
        Order order = new Order(orderServiceMock);
        String result = order.processOrder();
        assertEquals("Order processed successfully.", result);
    }

    @Test
    public void testOrderConstructor_NullOrderService() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Order(null));
        assertEquals("OrderService cannot be null.", exception.getMessage());
    }
}