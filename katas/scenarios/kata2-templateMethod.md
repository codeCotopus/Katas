# Kata 2: Refactoring a Data Processing Pipeline Using the Template Method Pattern

---

### **1. Concepts and Patterns Addressed**

- **Template Method Pattern**
- **Code Duplication Elimination**
- **Interfaces and Abstraction**
- **SOLID Principles (Single Responsibility, Open/Closed, Dependency Inversion)**
- **Unit Testing**

---

### **2. Scenario Description and Goals**

**Scenario:**

You are working on a data processing pipeline that handles different data formats, specifically CSV and JSON. The pipeline performs the following steps for each data format:

- **Validation**: Check if the data meets certain criteria.
- **Transformation**: Convert the data into a standardized format.
- **Storage**: Store the transformed data using a storage mechanism.

Currently, the `DataProcessor` class contains two methods, `processCsvData` and `processJsonData`, which perform these steps for CSV and JSON data, respectively. Both methods contain similar logic, leading to code duplication and making the code rigid and hard to maintain.

**Goals:**

- Refactor the `DataProcessor` class to eliminate code duplication by implementing a form of the **Template Method** pattern.
- Use interfaces to promote flexibility and extensibility.
- Ensure that the refactored code adheres to SOLID principles, particularly the Single Responsibility Principle.
- Maintain the existing functionality of the program after refactoring.
- Do not modify the unit tests; they should remain as they are throughout this exercise.

---

### **3. Code to Download**

**Files:**

- [`DataProcessor.java`](../src/main/java/kata2templatemethod/DataProcessor.java)
- [`DataStorage.java`](../src/main/java/kata2templatemethod/DataStorage.java)
- [`ProcessingStatus.java`](../src/main/java/kata2templatemethod/ProcessingStatus.java)
- [`StorageResult.java`](../src/main/java/kata2templatemethod/StorageResult.java)
- [`DataProcessorTest.java`](../src/test/java/kata2templatemethod/DataProcessorTest.java)

You can download the starter code and unit tests from [here](../).

---

**Good luck with the refactoring!** Remember to refactor incrementally, running tests frequently to ensure that your changes maintain the desired functionality.