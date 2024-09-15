# Solution: Refactoring with Guard Clauses

## Overview

This solution demonstrates the process of refactoring a legacy `processOrder` method to improve its readability and maintainability using **guard clauses**. Guard clauses are early return statements that handle edge cases or failures upfront, allowing the rest of the method to follow a clean, linear flow.

## Problem Description

The original implementation of the `processOrder` method contains nested conditional statements. While the method works, the nesting makes the code harder to read and maintain. By refactoring the method to use guard clauses, we can simplify the structure and make future changes easier to implement.

---

## Steps for Refactoring

### Step 1: Analyze the Original Code

Here is the original implementation of the `processOrder` method, which contains nested `if` statements:

```java
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
```

**Issues:**
- The method has multiple levels of nested conditionals.
- The deep nesting reduces readability and increases complexity.

### Step 2: Apply Guard Clauses 

In refactoring, we use guard clauses to handle invalid conditions early and exit the method immediately when a condition is not met. This reduces nesting and improves code clarity.

#### Start with the constructor:
```java
    public Order(OrderService orderService) {
        if (orderService != null ){
            this.orderService = orderService;
        } else {
            throw  new IllegalArgumentException("OrderService cannot be null.");
        }
    }
```
Proceed to apply 'Invert if condition'
```java
    public Order(OrderService orderService) {
        if (orderService == null) {
            throw  new IllegalArgumentException("OrderService cannot be null.");
        } else {
            this.orderService = orderService;
        }
    }
```
You can now remove the else block. Removing curly braces is optional depending on the conventions you and your team follow.

```java
    public Order(OrderService orderService) {
        if (orderService == null) throw new IllegalArgumentException("OrderService cannot be null.");        
        this.orderService = orderService;
    }
```

#### On the process order method
Look at the top conditional statement. Copy it, revert it, and return the expected value.
Then return null from the method as a guide. Remember that Removing curly braces is optional.
Here is the code after this step:
```java
 public String processOrder() {
        if (!orderService.isPaymentMethodValid(this)) return "Invalid payment method.";

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
        }
        return null;
    }
```
Apply the same technique to the next conditional statement. Remember to execute the Unit tests after each step.
```java
   public String processOrder() {
        if (!orderService.isPaymentMethodValid(this)) return "Invalid payment method.";
        if (!orderService.areItemsInStock(this)) return "One or more items are out of stock.";

        if (orderService.isPaymentMethodValid(this)) {
            if (orderService.areItemsInStock(this)) {
                if (orderService.isShippingAddressValid(this)) {
                    orderService.finalizeOrder(this);
                    return "Order processed successfully.";
                } else {
                    return "Invalid shipping address.";
                }
            }  
        }
        return null;
    }
```
And again...
```java
 public String processOrder() {
        if (!orderService.isPaymentMethodValid(this)) return "Invalid payment method.";
        if (!orderService.areItemsInStock(this)) return "One or more items are out of stock.";
        if (!orderService.isShippingAddressValid(this)) return "Invalid shipping address.";

        if (orderService.isPaymentMethodValid(this)) {
            if (orderService.areItemsInStock(this)) {
                if (orderService.isShippingAddressValid(this)) {
                    orderService.finalizeOrder(this);
                    return "Order processed successfully.";
                }
            }
        }
        return null;
    }
```
Finally, we have covered all the conditions, and can complete the process by removing the nested if statements.
```java

    public String processOrder() {
        if (!orderService.isPaymentMethodValid(this)) return "Invalid payment method.";
        if (!orderService.areItemsInStock(this)) return "One or more items are out of stock.";
        if (!orderService.isShippingAddressValid(this)) return "Invalid shipping address.";

        orderService.finalizeOrder(this);
        return "Order processed successfully.";
    
    }
```

### Step 3: Refactored Code with Guard Clauses

Here’s the refactored version of the `processOrder` method using guard clauses:

```java
public String processOrder() {
    if (!orderService.isPaymentMethodValid(this)) {
        return "Invalid payment method.";
    }

    if (!orderService.areItemsInStock(this)) {
        return "One or more items are out of stock.";
    }

    if (!orderService.isShippingAddressValid(this)) {
        return "Invalid shipping address.";
    }

    orderService.finalizeOrder(this);
    return "Order processed successfully.";
}
```

---

## Benefits of the Refactor

- **Simplified Flow:** Each condition is handled individually without unnecessary nesting.
- **Improved Readability:** The structure is easier to follow, with a clear linear flow.
- **Easier Maintenance:** Guard clauses make it easier to add or modify conditions in the future without introducing additional complexity.
- **Early Returns:** The method exits early when a condition is not met, preventing further unnecessary processing.

---

## Final Refactored Code

```java
package kata1guardrails;

public class Order {

    private final OrderService orderService;

    // Other properties and methods

    public Order(OrderService orderService) {
        if (orderService == null) throw new IllegalArgumentException("OrderService cannot be null.");
        this.orderService = orderService;
    }

    public String processOrder() {
        if (!orderService.isPaymentMethodValid(this)) {
            return "Invalid payment method.";
        }

        if (!orderService.areItemsInStock(this)) {
            return "One or more items are out of stock.";
        }

        if (!orderService.isShippingAddressValid(this)) {
            return "Invalid shipping address.";
        }

        orderService.finalizeOrder(this);
        return "Order processed successfully.";
    }
}
```

---

By applying guard clauses, we have significantly improved the structure and maintainability of the `processOrder` method. This approach reduces complexity, ensures early exits on failure conditions, and improves code readability.