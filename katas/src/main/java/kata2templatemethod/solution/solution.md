# Solution: Implementing the Template Method Pattern Using Composition and Interfaces

## Overview

In this solution, we refactor a data processing pipeline by implementing a form of **Template Method** pattern.
The original code contains duplicated logic and is tightly coupled to specific data formats (CSV and JSON). By applying this refactoring, we eliminate logic duplication, promote code reuse, and make it easier to extend the pipeline to support new data formats.

## Problem Description

### Original Implementation

The `DataProcessor` class contains two methods, `processCsvData` and `processJsonData`, which perform data validation, transformation, and storage for CSV and JSON data, respectively. Both methods represent the same algorithm, leading to code duplication and making the code more rigid.

**Original Code:**

```java
public class DataProcessor {

    private final DataStorage dataStorage;

    public DataProcessor(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public ProcessingStatus processCsvData(String data) {
        // Step 1: Validation
        if (data == null || data.isEmpty()) {
            return ProcessingStatus.VALIDATION_FAILED;
        }

        // Step 2: Transformation
        String transformedData = transformCsv(data);
        if (transformedData == null) {
            return ProcessingStatus.TRANSFORMATION_FAILED;
        }

        // Step 3: Storage
        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) {
            return ProcessingStatus.SUCCESS;
        } else {
            return ProcessingStatus.STORAGE_FAILED;
        }
    }

    public ProcessingStatus processJsonData(String data) {
        // Similar to processCsvData, but for JSON data
    }

    private String transformCsv(String data) {
        // Transformation logic for CSV
    }

    private String transformJson(String data) {
        // Transformation logic for JSON
    }
}
```

### Issues Identified

- **Logic Duplication:** The `processCsvData` and `processJsonData` methods contain duplicated logic for validation, transformation, and storage.
- **Tight Coupling:** The class is tightly coupled to CSV and JSON formats, making it difficult to add new data formats without modifying existing code.
- **Lack of Abstraction:** There's no abstraction to represent the processing steps, leading to repetitive code and reduced flexibility.
- **Violation of Single Responsibility Principle:** The `DataProcessor` class handles multiple responsibilities, including validation, transformation, and storage.

## Steps for Refactoring

### Step 1: Extract the validation logic

We start by performing extract method object over the validation condition for each class.
Although in this example the logic is the same, We will keep two different classes, one for CSV and one for Json.
Perform "extract variable" so that your validator creation ends up in a different line.

Here is the code after this step:

```java
public class DataProcessor {

    private final DataStorage dataStorage;

    public DataProcessor(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public ProcessingStatus processCsvData(String data) {
        // Step 1: Validation
        CsvValidator csvValidator = new CsvValidator(data);
        if (csvValidator.validate()) {
            return ProcessingStatus.VALIDATION_FAILED;
        }

        // Step 2: Transformation
        String transformedData = transformCsv(data);
        if (transformedData == null) {
            return ProcessingStatus.TRANSFORMATION_FAILED;
        }

        // Step 3: Storage
        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) {
            return ProcessingStatus.SUCCESS;
        } else {
            return ProcessingStatus.STORAGE_FAILED;
        }
    }

    public ProcessingStatus processJsonData(String data) {
        // Step 1: Validation
        JsonValidator jsonValidator = new JsonValidator(data);
        if (jsonValidator.validate()) {
            return ProcessingStatus.VALIDATION_FAILED;
        }

        // Step 2: Transformation
        String transformedData = transformJson(data);
        if (transformedData == null) {
            return ProcessingStatus.TRANSFORMATION_FAILED;
        }

        // Step 3: Storage
        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) {
            return ProcessingStatus.SUCCESS;
        } else {
            return ProcessingStatus.STORAGE_FAILED;
        }
    }

    private String transformCsv(String data) {
        // Dummy transformation logic for CSV
        if (data.contains("invalid")) {
            return null; // Simulate transformation failure
        }
        return data.replace(",", ";");
    }

    private String transformJson(String data) {
        // Dummy transformation logic for JSON
        if (data.contains("invalid")) {
            return null; // Simulate transformation failure
        }
        return data.replaceAll("\"", "'");
    }

    private class CsvValidator {
        private String data;

        public CsvValidator(String data) {
            this.data = data;
        }

        public boolean validate() {
            return data == null || data.isEmpty();
        }
    }

    private class JsonValidator {
        private String data;

        public JsonValidator(String data) {
            this.data = data;
        }

        public boolean validate() {
            return data == null || data.isEmpty();
        }
    }
}
```

Also, note that in general any class that has a verb-noun as as name (like validator for example), is a very poor idea. For the purpose of this Kata we'll live with this. 

### Step 2: Extract the processing logic and move classes out. 
We will apply a similar process to the validation logic. Introduce Method object at the `transformCsv`, and `transformJson` methods respectively, followed by inlining of these methods. 
Proceed to extract variable so that the declaration and usage of the two classes are in two lines. Finally, move the declaration of th transformer classes to the top.

We will then proceed to move all the new classes out with "Move inner class to upper level".

This is how the code should look after this step: 

```java
public class DataProcessor {

    private final DataStorage dataStorage;

    public DataProcessor(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public ProcessingStatus processCsvData(String data) {
        // Step 1: Validation
        CsvValidator csvValidator = new CsvValidator(data);
        CsvTransformer csvTransformer = new CsvTransformer(data);
        
        if (csvValidator.validate()) {
            return ProcessingStatus.VALIDATION_FAILED;
        }

        // Step 2: Transformation
        String transformedData = csvTransformer.transform();
        if (transformedData == null) {
            return ProcessingStatus.TRANSFORMATION_FAILED;
        }

        // Step 3: Storage
        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) {
            return ProcessingStatus.SUCCESS;
        } else {
            return ProcessingStatus.STORAGE_FAILED;
        }
    }

    public ProcessingStatus processJsonData(String data) {
        // Step 1: Validation
        JsonValidator jsonValidator = new JsonValidator(data);
        JsonTransfomer jsonTransfomer = new JsonTransfomer(data);
        
        if (jsonValidator.validate()) {
            return ProcessingStatus.VALIDATION_FAILED;
        }

        // Step 2: Transformation
        String transformedData = jsonTransfomer.transform();
        if (transformedData == null) {
            return ProcessingStatus.TRANSFORMATION_FAILED;
        }

        // Step 3: Storage
        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) {
            return ProcessingStatus.SUCCESS;
        } else {
            return ProcessingStatus.STORAGE_FAILED;
        }
    }
}
```

### Step 3: Extract Interfaces for validators and transformers.
Now that we have moved the classes out, we can proceed to extract interfaces from them. Here are the interface definitions:

```java
public interface Validator {
    boolean validate();
}


```
```java
public interface Transformer {
    String transform();
}
```

Proceed to replace concrete class variables for their interface in the `DataProcessorClass`.
Also, at this stage those comments are pretty annoying, and the code speaks for itself. Remove them. 

Here is what the code looks like. 
```java
public class DataProcessor {

    private final DataStorage dataStorage;

    public DataProcessor(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public ProcessingStatus processCsvData(String data) {        
        Validator csvValidator = new CsvValidator(data);
        Transformer csvTransformer = new CsvTransformer(data);

        if (csvValidator.validate()) {
            return ProcessingStatus.VALIDATION_FAILED;
        }

        String transformedData = csvTransformer.transform();
        if (transformedData == null) {
            return ProcessingStatus.TRANSFORMATION_FAILED;
        }

        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) {
            return ProcessingStatus.SUCCESS;
        } else {
            return ProcessingStatus.STORAGE_FAILED;
        }
    }

    public ProcessingStatus processJsonData(String data) {        
        Validator jsonValidator = new JsonValidator(data);
        Transformer jsonTransformer = new JsonTransformer(data);

        if (jsonValidator.validate()) {
            return ProcessingStatus.VALIDATION_FAILED;
        }
        
        String transformedData = jsonTransformer.transform();
        if (transformedData == null) {
            return ProcessingStatus.TRANSFORMATION_FAILED;
        }
        
        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) {
            return ProcessingStatus.SUCCESS;
        } else {
            return ProcessingStatus.STORAGE_FAILED;
        }
    }
}
```

#### Step 4: Extract method, and introduce method template.

Our two "process" methods are now identical except for the validator and processor.
If you extract method after the validator and processors are declared, your IDE should be smart enough to identify this and suggest a replacement. 

After some clean up and some small changes, here is the code. And yes... I love to see Java code without curly braces.

```java
public class DataProcessor {

    private final DataStorage dataStorage;

    public DataProcessor(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public ProcessingStatus processCsvData(String data) {
        final Validator csvValidator = new CsvValidator(data);
        final Transformer csvTransformer = new CsvTransformer(data);

        return getProcessingStatus(csvValidator, csvTransformer);
    }

    public ProcessingStatus processJsonData(String data) {
        final Validator jsonValidator = new JsonValidator(data);
        final Transformer jsonTransformer = new JsonTransformer(data);

        return getProcessingStatus(jsonValidator, jsonTransformer);
    }

    private ProcessingStatus getProcessingStatus(final Validator jsonValidator, final Transformer jsonTransformer) {
        if (jsonValidator.validate()) return ProcessingStatus.VALIDATION_FAILED;

        String transformedData = jsonTransformer.transform();
        if (transformedData == null) return ProcessingStatus.TRANSFORMATION_FAILED;

        StorageResult storageResult = dataStorage.storeData(transformedData);
        if (storageResult == StorageResult.SUCCESS) return ProcessingStatus.SUCCESS;
        else return ProcessingStatus.STORAGE_FAILED;        
    }
}

```

For the purpose of identifying where we can use template method, we are done at this stage. 
The code still can get some improvements.The biggest one would be Injection of the validator and processors.
This could be taken one step further by making `data` a proper class rather than a primitive, and inject these behaviours there, maybe as strategies.
 

### **Unit Tests**

*(Remain unchanged)*

## Benefits of the Refactoring

1. **Eliminated Code Duplication:**
    - Common logic is centralized in the `processData` method, reducing repetition.

2. **Improved Flexibility and Extensibility:**
    - New data formats can be added by implementing `Validator` and `Tanfsormer` interfaces without modifying the existing algorithm. 

3. **Adherence to Design Principles:**
    - **Single Responsibility Principle:** Each class has a single responsibility.
    - **Open/Closed Principle:** The template method is open for extension while closed for modification.
    - **Dependency Inversion Principle:** High-level modules depend on abstractions.

4. **Enhanced Testability:**
    - Individual components (validators and transformers) can be tested separately. 

## Conclusion

By applying the Template Method pattern we've refactored the data processing pipeline to be more maintainable, extensible, and aligned with SOLID principles. This approach enhances code readability and allows for easy integration of new data formats without impacting existing functionality.

## Next Steps to try on your own.

- **Inject the correct Validator and Processor to DataProcessor:**
    - Since the client is already choosing from `ProcessCsvData` and `ProcessJsonData` it is in a position to decide which implementations to inject. Do this and remove the concrete methods.

- **Implement Additional Data Formats:**
    - Extend the pipeline to support XML, YAML, or other data formats by creating new implementations of `Validator` and `Transformer`.

- **Naming:**
    - Process is a terrible name, same as Manager. Irrespectively of it being use on a method or class, it should be changed to a name that tells the reader what is going on.
    - Classes in this example contain verbs made nouns (To validate -> Validator, as an example). In general class names are better as pure nouns. If I were force to change this, I would probably replace Validator for ValidatorStrategy (or Validator Behaviour), which would immediately point me to Strategy pattern.
 