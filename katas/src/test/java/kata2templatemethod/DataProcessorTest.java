package kata2templatemethod;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DataProcessorTest {

    // Tests for processCsvData

    @Test
    public void testProcessCsvData_Success() {
        DataStorage mockStorage = mock(DataStorage.class);
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.SUCCESS);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "name,age,location";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.SUCCESS, result);
        verify(mockStorage).storeData("name;age;location");
    }

    @Test
    public void testProcessCsvData_ValidationFailed_NullData() {
        DataStorage mockStorage = mock(DataStorage.class);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = null;
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessCsvData_ValidationFailed_EmptyData() {
        DataStorage mockStorage = mock(DataStorage.class);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessCsvData_TransformationFailed() {
        DataStorage mockStorage = mock(DataStorage.class);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "invalid,data";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.TRANSFORMATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessCsvData_StorageFailed() {
        DataStorage mockStorage = mock(DataStorage.class);
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.FAILURE);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "name,age,location";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.STORAGE_FAILED, result);
        verify(mockStorage).storeData("name;age;location");
    }

    // Tests for processJsonData

    @Test
    public void testProcessJsonData_Success() {
        DataStorage mockStorage = mock(DataStorage.class);
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.SUCCESS);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "{\"name\":\"John\",\"age\":30}";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.SUCCESS, result);
        verify(mockStorage).storeData("{'name':'John','age':30}");
    }

    @Test
    public void testProcessJsonData_ValidationFailed_NullData() {
        DataStorage mockStorage = mock(DataStorage.class);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = null;
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessJsonData_ValidationFailed_EmptyData() {
        DataStorage mockStorage = mock(DataStorage.class);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessJsonData_TransformationFailed() {
        DataStorage mockStorage = mock(DataStorage.class);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "{\"invalid\":\"data\"}";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.TRANSFORMATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessJsonData_StorageFailed() {
        DataStorage mockStorage = mock(DataStorage.class);
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.FAILURE);

        DataProcessor processor = new DataProcessor(mockStorage);
        String data = "{\"name\":\"John\",\"age\":30}";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.STORAGE_FAILED, result);
        verify(mockStorage).storeData("{'name':'John','age':30}");
    }
}
