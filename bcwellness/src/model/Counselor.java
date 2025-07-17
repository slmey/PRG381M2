package model;

/*
 * Represents a counselor in the wellness management system,
 * storing details like ID, name, specialization, email, and availability.
 */
public class Counselor {
    private int counselorId;      // Unique identifier for the counselor
    private String firstName;     // First name of the counselor
    private String lastName;      // Last name of the counselor
    private String specialization; // Area of expertise
    private String email;         // Email address for contact
    private String availability;  // Availability status (e.g., Available/Unavailable)

    /*
     * Constructs a new Counselor with the specified details.
     * id - The unique identifier for the counselor
     * firstName - The first name of the counselor
     * lastName - The last name of the counselor
     * specialization - The area of expertise
     * email - The email address
     * availability - The availability status
     */
    public Counselor(int id, String firstName, String lastName, String specialization, String email, String availability) {
        this.counselorId = id;     // Assign the provided ID (fix: was counselorId = counselorId)
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.availability = availability;
    }

    // Getters for accessing counselor details
    /*
     * Returns the unique identifier of the counselor.
     * Returns the counselor ID
     */
    public int getCounselorId() {
        return counselorId;
    }

    /*
     * Returns the first name of the counselor.
     * Returns the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /*
     * Returns the last name of the counselor.
     * Returns the last name
     */
    public String getLastName() {
        return lastName;
    }

    /*
     * Returns the specialization of the counselor.
     * Returns the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /*
     * Returns the email address of the counselor.
     * Returns the email
     */
    public String getEmail() {
        return email;
    }

    /*
     * Returns the availability status of the counselor.
     * Returns the availability
     */
    public String getAvailability() {
        return availability;
    }
}