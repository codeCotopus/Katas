package kata1guardrails;

public interface OrderService {
    boolean isPaymentMethodValid(Order order);

    boolean areItemsInStock(Order order);

    boolean isShippingAddressValid(Order order);

    void finalizeOrder(Order order);
}
