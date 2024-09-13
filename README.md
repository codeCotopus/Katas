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
- Familiarity with concepts from:
  - "Refactoring" by Martin Fowler.
  - "Working Effectively with Legacy Code" by Michael Feathers.
  - Design patterns from the Gang of Four (GoF).
- An understanding of unit testing with JUnit 5.

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

- [`Order.java`](kata1-guard-clauses/Order.java)
- [`OrderService.java`](kata1-guard-clauses/OrderService.java)
- [`OrderTest.java`](kata1-guard-clauses/OrderTest.java)

**Description:**

You are tasked with refactoring an `Order` class in an e-commerce application. The `processOrder` method contains deeply nested conditional statements that make the code difficult to read and maintain. The goal is to refactor this method using guard clauses and ensure that the `OrderService` is properly injected as a dependency. Unit tests are provided to validate the behavior before and after refactoring.

## Getting Started

Follow these steps to set up the project on your local machine:

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/yourusername/code-katas.git
   ```

2. **Navigate to the Kata Directory:**

   ```bash
   cd code-katas/kata1-guard-clauses
   ```

3. **Import the Project into Your IDE:**

   - Open your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
   - Import the project as a Maven project.

4. **Install Dependencies:**

   - Ensure that Maven or your IDE handles the dependencies specified in the `pom.xml` file.
   - JUnit 5 should be included as a dependency for unit testing.

## Running the Tests

To verify your implementation and ensure that your refactoring maintains the desired functionality, run the unit tests provided.

**Using Maven:**

```bash
mvn test
```

**Using Your IDE:**

- Right-click on the `OrderTest.java` file.
- Select **Run 'OrderTest'** or the equivalent option in your IDE.

**Expected Results:**

All tests should pass, confirming that the refactored code behaves as intended.

## Contributing

Contributions are clossed as of now! If you have a kata you'd like to add or improvements to existing ones, please reach out:


## License

This repository is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

If you have any questions or need further assistance with the katas, feel free to reach out. Happy coding!
