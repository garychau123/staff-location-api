package staff.location.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class StaffLocationAPIApplication {
    private StaffLocationAPIApplication() {
        // Private constructor to prevent instantiation
    }

    public static void main(final String[] args) {
        SpringApplication.run(StaffLocationAPIApplication.class, args);
    }
}
