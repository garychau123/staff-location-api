package staff.location.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.Pattern;
import staff.location.api.model.StaffDetails;
import staff.location.api.provider.employeeapi.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * REST controller for staff location lookups.
 */
@RestController
@Validated
public class StaffLocationController {
    /**
     * Logger for the StaffLocationController.
     * This logger is used to log information and errors related to the staff location API.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StaffLocationController.class);

    /**
     * Base URL for the Employee API.
     */
    @Value("${employee.api.url}")
    private String employeeAPIUrl;

    @Operation(
        summary = "Get staff details by first name",
        parameters = {
            @io.swagger.v3.oas.annotations.Parameter(
                name = "firstName",
                description = "First name (letters only, A-Z or a-z)",
                example = "Gary",
                required = true,
                schema = @io.swagger.v3.oas.annotations.media.Schema(
                    pattern = "^[A-Za-z]+$"
                )
                )
        }
    )
    @ApiResponse(responseCode = "200", description = "List of staff details returned")
    @ApiResponse(
        responseCode = "400",
        description = "Invalid characters or missing name",
        content = @io.swagger.v3.oas.annotations.media.Content(
            mediaType = "application/json",
            examples = {
                @io.swagger.v3.oas.annotations.media.ExampleObject(
                    value = "{\"error\":\"invalid characters\"}"
                    )
            }
        )
    )
    @GetMapping(value = "/staff-details/{firstName}", produces = "application/json")
    public ResponseEntity<List<StaffDetails>> getStaffDetails(
        @PathVariable
        @Pattern(regexp = "^[A-Za-z]+$", message = "invalid characters") final String firstName) {

        final UriTemplate uriTemplate = new UriTemplate(employeeAPIUrl);
        final URI uri = uriTemplate.expand(firstName);

        LOGGER.info("Calling Employee API with url: {}", uri);

        final RestClient restClient = RestClient.create();
        final Employee[] employee = restClient.
            get().
            uri(uri).
            retrieve().
            body(Employee[].class);

        final List<StaffDetails> finalOutput = new ArrayList<>();

        for (Employee employee1 : employee) {
            final String fullName = employee1.getFirstName() + " " + employee1.getSurname();
            finalOutput.add(new StaffDetails(
                fullName,
                employee1.getId(),
                employee1.getOfficeLocation(),
                employee1.getOfficePhone()
            ));
        }

        LOGGER.info("Returning StaffDetails: {}", finalOutput);
        return ResponseEntity.ok(finalOutput);
    }
}
