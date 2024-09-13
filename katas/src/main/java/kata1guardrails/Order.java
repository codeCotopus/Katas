package kata1guardrails;

public class Order {

    private final OrderService orderService;

    // Other properties and methods

    public Order(OrderService orderService) {
        if (orderService != null ){
            this.orderService = orderService;
        } else {
            throw  new IllegalArgumentException("OrderService cannot be null.");
        }
    }

    public String processOrder() {
            if (orderService.isPaymentMethodValid(this)) {
                if (orderService.areItemsInStock(this)) {
                    if (orderService.isShippingAddressValid(this)) {
                        orderService.finalizeOrder(this);
                        return "Order processed successfully.";
                    } else {
                        return "Invalid shipping address.";
                    }
                } else {
                    return "One or more items are out of stock.";
                }
            } else {
                return "Invalid payment method.";
            }
    }
}
