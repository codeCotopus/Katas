package kata2templatemethod;

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
        // Step 1: Validation
        if (data == null || data.isEmpty()) {
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
}