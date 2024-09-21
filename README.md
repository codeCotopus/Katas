# Code Katas 

Welcome to the **Code Katas **. This repository contains a series of coding exercises (katas) designed to improve your coding skills through practice and repetition. Each kata focuses on specific programming concepts, design patterns, and best practices.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Katas](#katas)
  - [1. Improving Code Readability with Guard Clauses](#1-improving-code-readability-with-guard-clauses)
- [Getting Started](#getting-started)
- [Running the Tests](#running-the-tests)
- [Contributing](#contributing)
- [License](#license)

## Introduction

This repository aims to provide developers with practical exercises to refine their coding techniques. The katas cover various topics such as refactoring, design patterns, and code optimization. By working through these exercises, you will enhance your understanding of clean code principles and improve your problem-solving skills.

## Prerequisites

To get the most out of these katas, you should have:

- Basic to intermediate knowledge of Java programming.
- Familiarity with refactoring and design patterns concepts.  
- An understanding of unit testing.

## Katas

### 1. Improving Code Readability with Guard Clauses

**Objective:** Refactor a legacy codebase to improve readability and maintainability using guard clauses.

**Concepts Covered:**

- Guard Clauses
- Simplifying Conditional Logic
- Enhancing Code Readability
- Early Return Strategy
- Unit Testing with Assertions
- Dependency Injection

**Files:**

- [`Order.java`](katas/src)
- [`OrderService.java`](kata1-guard-clauses/OrderService.java)
- [`OrderTest.java`](kata1-guard-clauses/OrderTest.java)

**Description:**

This kata focuses on refactoring the `processOrder` method within the `Order` class of an e-commerce application. The original method uses deeply nested conditional statements to validate an order before processing it. The goal of this kata is to simplify the method by applying **guard clauses**, which allow the code to return early if conditions aren't met, thus improving readability and maintainability.

**Steps:**

1. Start by examining the original code, which uses multiple nested `if` statements to validate conditions such as:
   - Payment method validity.
   - Item stock availability.
   - Shipping address validity.

2. Apply **guard clauses** by refactoring the code to return early if any condition fails. This eliminates the need for nested `if` statements, making the code easier to follow.

3. Ensure that **dependency injection** is properly implemented by injecting `OrderService` into the `Order` class.

4. Run unit tests to ensure the behavior remains consistent before and after the refactor.

**Scenario:**

Refer to the [solution file](kata1-guard-clauses/solution.md) for full details on the refactoring steps and final implementation.

**Expected Results:**

- Improved readability due to the simplified, linear structure of the code.
- Early returns for invalid conditions, preventing unnecessary processing.
- Passing unit tests to validate the refactored behavior.

 

## Contributing

Contributions are clossed as of now! If you have a kata you'd like to add or improvements to existing ones, please reach out:


## License

This repository is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

If you have any questions or need further assistance with the katas, feel free to reach out. Happy coding!
