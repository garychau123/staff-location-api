package staff.location.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@SpringBootApplication
public class StaffLocationAPI {
    private static final Logger logger = LoggerFactory.getLogger(StaffLocationAPI.class);

    @RequestMapping("/")
    String home(){
        return "hi";
    }

    @GetMapping("/staff-details/{firstName}") 
    public ResponseEntity<List<StaffDetails>> getStaffDetails (@PathVariable String firstName) {
        String url = "http://localhost:8080/employees/" + firstName;

        RestClient restClient = RestClient.create();
        Employee[] employee = restClient.get()
            .uri(url)
            .retrieve()
            .body(Employee[].class);

        List<StaffDetails> finalOutput = new ArrayList<>();

        for (Employee employee1 : employee) {
            String fullName = employee1.getFirstName() + " " + employee1.getSurname();
            finalOutput.add(new StaffDetails(fullName, employee1.getId(), employee1.getOfficeLocation(), employee1.getOfficePhone()));
        }
        logger.info("Returning StaffDetails: {}", finalOutput);
        return ResponseEntity.ok(finalOutput);
    }
        
    
    public static void main(String[] args){
        SpringApplication.run(StaffLocationAPI.class, args);
    }
}
