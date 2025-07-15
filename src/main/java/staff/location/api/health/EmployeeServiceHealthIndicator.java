package staff.location.api.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmployeeServiceHealthIndicator implements HealthIndicator {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String EMPLOYEE_SERVICE_HEALTH_URL = "http://localhost:8080/actuator/health";

    @Override
    public Health health() {
        try {
            String response = restTemplate.getForObject(EMPLOYEE_SERVICE_HEALTH_URL, String.class);
            return Health.up().withDetail("employee-service", response).build();
        } catch (Exception ex) {
            return Health.down().withDetail("employee-service", "DOWN").withException(ex).build();
        }
    }
}
