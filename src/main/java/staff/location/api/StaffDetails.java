package staff.location.api;

import io.swagger.v3.oas.annotations.media.Schema;

public class StaffDetails {
    @Schema(
        description = "Name of the staff member",
        example = "Gary Chau",
        required = true
    )
    private String name;

    @Schema(
        description = "Unique identifier for the staff member",
        example = "2150",
        required = true
    )
    private String id;

    @Schema(
        description = "Office location of the staff member",
        example = "Freedom Office, Level 11, Desk 25",
        required = true
    )
    private String officeLocation;

    @Schema(
        description = "Office phone number of the staff member",
        example = "03 9476-8356",
        required = true
    )
    private String officePhone;
    

    public StaffDetails(String name, String id, String officeLocation, String officePhone) {
        this.name = name;
        this.id = id;
        this.officeLocation = officeLocation;
        this.officePhone = officePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(String officeLocation) {
        this.officeLocation = officeLocation;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Override
    public String toString() {
        return "StaffDetails{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", officeLocation='" + officeLocation + '\'' +
                ", officePhone='" + officePhone + '\'' +
                '}';
    }
}

