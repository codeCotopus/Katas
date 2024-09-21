package kata2templatemethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DataProcessorTest {

    @Mock
    private DataStorage mockStorage;
    private DataProcessor processor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        processor = new DataProcessor(mockStorage);
    }

    @Test
    public void testProcessCsvData_Success() {
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.SUCCESS);

        String data = "name,age,location";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.SUCCESS, result);
        verify(mockStorage).storeData("name;age;location");
    }

    @Test
    public void testProcessCsvData_ValidationFailed_NullData() {
        ProcessingStatus result = processor.processCsvData(null);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessCsvData_ValidationFailed_EmptyData() {
        String data = "";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessCsvData_TransformationFailed() {
        String data = "invalid,data";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.TRANSFORMATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessCsvData_StorageFailed() {
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.FAILURE);

        String data = "name,age,location";
        ProcessingStatus result = processor.processCsvData(data);

        assertEquals(ProcessingStatus.STORAGE_FAILED, result);
        verify(mockStorage).storeData("name;age;location");
    }

    // Tests for processJsonData

    @Test
    public void testProcessJsonData_Success() {
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.SUCCESS);

        String data = "{\"name\":\"John\",\"age\":30}";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.SUCCESS, result);
        verify(mockStorage).storeData("{'name':'John','age':30}");
    }

    @Test
    public void testProcessJsonData_ValidationFailed_NullData() {
        ProcessingStatus result = processor.processJsonData(null);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessJsonData_ValidationFailed_EmptyData() {
        String data = "";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.VALIDATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessJsonData_TransformationFailed() {
        String data = "{\"invalid\":\"data\"}";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.TRANSFORMATION_FAILED, result);
        verifyNoInteractions(mockStorage);
    }

    @Test
    public void testProcessJsonData_StorageFailed() {
        when(mockStorage.storeData(anyString())).thenReturn(StorageResult.FAILURE);

        String data = "{\"name\":\"John\",\"age\":30}";
        ProcessingStatus result = processor.processJsonData(data);

        assertEquals(ProcessingStatus.STORAGE_FAILED, result);
        verify(mockStorage).storeData("{'name':'John','age':30}");
    }
}
