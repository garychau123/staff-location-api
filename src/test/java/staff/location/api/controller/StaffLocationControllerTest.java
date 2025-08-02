package staff.location.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import staff.location.api.model.StaffDetails;
import staff.location.api.provider.employeeapi.Employee;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StaffLocationControllerTest {

    private StaffLocationController controller;

    private RestClient mockRestClient;
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;
    private RestClient.RequestHeadersSpec requestHeadersSpec;
    private RestClient.ResponseSpec responseSpec;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockRestClient = mock(RestClient.class);
        requestHeadersUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        requestHeadersSpec = mock(RestClient.RequestHeadersSpec.class);
        responseSpec = mock(RestClient.ResponseSpec.class);

        controller = new StaffLocationController(mockRestClient);
        setField(controller, "employeeAPIUrl", "http://localhost:8080/employees/{firstName}");
    }

    @Test
    void testGetStaffDetails_Success() {
        String firstName = "Alice";
        URI uri = URI.create("http://localhost:8080/employees/Alice");
        //paramaterized test
        Employee[] mockEmployees = new Employee[] {
            createMockEmployee("1", "Alice", "Smith", "Melbourne", "12345")
        };

        when(mockRestClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(uri)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(Employee[].class)).thenReturn(mockEmployees);

        ResponseEntity<List<StaffDetails>> response = controller.getStaffDetails(firstName);

        System.out.println("TEST 1:");
        assertEquals(1, response.getBody().size());

        StaffDetails result = response.getBody().get(0);

        assertEquals("Alice Smith", result.getName());
        assertEquals("1", result.getId());
        assertEquals("Melbourne", result.getOfficeLocation());
        assertEquals("12345", result.getOfficePhone());
    }

    // @Test
    // void testGetStaffDetails_ThrowsException() {
    //     String firstName = "Alice";
    //     URI uri = URI.create("http://localhost:8080/employees/Alice");

    //     when(mockRestClient.get()).thenReturn(requestHeadersUriSpec);
    //     when(requestHeadersUriSpec.uri(uri)).thenReturn(requestHeadersSpec);
    //     when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    //     when(responseSpec.body(Employee[].class)).thenThrow(new RestClientException("API failed"));

    //     System.out.println("TEST 2:");
    //     assertThrows(RestClientException.class, () -> {
    //         controller.getStaffDetails(firstName);
    //     });
    // }

    private Employee createMockEmployee(String id, String firstName, String surname, String location, String phone) {
        Employee e = mock(Employee.class);
        when(e.getId()).thenReturn(id);
        when(e.getFirstName()).thenReturn(firstName);
        when(e.getSurname()).thenReturn(surname);
        when(e.getOfficeLocation()).thenReturn(location);
        when(e.getOfficePhone()).thenReturn(phone);
        return e;
    }

    private void setField(Object target, String fieldName, Object value) {
        try {
            java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
