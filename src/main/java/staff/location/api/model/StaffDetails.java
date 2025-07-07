package staff.location.api.model;

/**
 * Represents trimmed staff details returned to the client.
 */
public class StaffDetails {

    /**
     * Full name of the staff member.
     */
    private String name;

    /**
     * Employee ID.
     */
    private String id;

    /**
     * Office location of the staff member.
     */
    private String officeLocation;

    /**
     * Office phone number.
     */
    private String officePhone;

    /**
     * Constructs a new StaffDetails instance.
     *
     * @param name            Full name
     * @param id              Employee ID
     * @param officeLocation  Office location
     * @param officePhone     Office phone number
     */
    public StaffDetails(final String staffName, final String staffId, final String staffOfficeLocation, final String staffOfficePhone) {
        name = staffName;
        id = staffId;
        officeLocation = staffOfficeLocation;
        officePhone = staffOfficePhone;
    }

    /**
     * Gets the full name.
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the employee ID.
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the office location.
     *
     * @return Office location
     */
    public String getOfficeLocation() {
        return officeLocation;
    }

    /**
     * Gets the office phone number.
     *
     * @return Office phone
     */
    public String getOfficePhone() {
        return officePhone;
    }

    @Override
    public String toString() {
        return "StaffDetails{"
            + "name='" + name + '\''
            + ", id='" + id + '\''
            + ", officeLocation='" + officeLocation + '\''
            + ", officePhone='" + officePhone + '\''
            + '}';
    }
}
