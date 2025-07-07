package staff.location.api.provider.employeeapi;

    /**
     * This is an Employee class
     */
public class Employee {
    /**
     * This class represents an employee id in the Employee API.
     */
    private String id;
    /**
     * This class represents an firstName in the Employee API.
     */
    private String firstName;
    /**
     * This class represents an surname in the Employee API.
     */
    private String surname;
    /**
     * This class represents an officeLocation in the Employee API.
     */
    private String officeLocation;
    /**
     * This class represents an officePhone in the Employee API.
     */
    private String officePhone;

    public String getId() {
        return id; }
    public String getFirstName() {
        return firstName; }
    public String getSurname() { 
        return surname; }
    public String getOfficeLocation() { 
        return officeLocation; }
    public String getOfficePhone() { 
        return officePhone; }
}

