package staff.location.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@SpringBootApplication
public class StaffLocationAPI {
    @RequestMapping("/")
    String home(){
        return "hi";
    }

    @GetMapping("/staff-details/{firstName}") 
    public ResponseEntity<List<StaffDetails>> getStaffDetails (@PathVariable String firstName) {
        String url = "http://localhost:8080/employees/" + firstName;

        RestTemplate restTemplate = new RestTemplate();
        //Send HTTP get request to http://localhost:8080/employees/"name"
        //Converts JSON into array
        Employee [] employee;
        employee = restTemplate.getForObject(url, Employee[].class);


        List<StaffDetails> finalOutput = new ArrayList<>();

        for (int i = 0; i < employee.length; i ++) {
            Employee employee1 = employee[i];
            String fullName = employee1.getFirstName() + " " + employee1.getSurname();
            
        }
        
        return ResponseEntity.ok(new ArrayList<>());
        }
        
    
    public static void main(String[] args){
        SpringApplication.run(StaffLocationAPI.class, args);
    }
}
