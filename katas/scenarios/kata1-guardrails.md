

 # Kata 1: Improving Code Readability with Guard Clauses

---

### **1. Concepts and Patterns Addressed**

- **Guard Clauses**
- **Simplifying Conditional Logic**
- **Enhancing Code Readability**
- **Early Return Strategy**
- **Unit Testing with Assertions**
- **Dependency Injection**

---

### **2. Scenario Description and Goals**

**Scenario:**

The `Order` class performs several checks before finalizing an order:

- Checks if the customer's payment method is valid.
- Ensures that the items are in stock.
- Confirms that the shipping address is valid.

These checks are currently implemented using nested `if` statements within the `Order` class. The `OrderService` provides methods to perform these checks and finalize the order. However, due to the nested structure, the code is hard to read and maintain.

**Goals:**

- Refactor the nested conditional statements into guard clauses within the `Order` class.
- Simplify the code to improve readability and maintainability.
- Ensure the program's functionality remains the same after refactoring.
- Do not modify unit tests they should remain as they are through this exercise..

---

### **3. Code to Download**
**Files:**

- [`Order.java`](../src/main/java/kata1guardrails/Order.java)
- [`OrderService.java`](../src/main/java/kata1guardrails/OrderService.java)
- [`OrderTest.java`](../src/test/java/kata1guardrails/OrderTests.java)

You can download the starter code and unit tests from the [here](../).

