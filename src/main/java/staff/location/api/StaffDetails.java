package staff.location.api;

public class StaffDetails {
    private String name;
    private String id;
    private String officeLocation;
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

