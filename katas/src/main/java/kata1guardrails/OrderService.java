package kata1guardrails;

public class OrderService {

    protected boolean isPaymentMethodValid(Order order) {
        // Implementation goes here
        return true;
    }

    protected boolean areItemsInStock(Order order) {
        // Implementation goes here
        return true;
    }

    protected boolean isShippingAddressValid(Order order) {
        // Implementation goes here
        return true;
    }

    protected void finalizeOrder(Order order) {
        // Implementation goes here
    }
}
