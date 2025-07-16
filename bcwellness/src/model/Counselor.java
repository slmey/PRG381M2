package model;

public class Counselor {
    private int counselorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String availability;

    public Counselor(int id, String firstName, String lastName, String specialization, String email, String availability) {
        this.counselorId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.availability = availability;
    }

    // Getters
    public int getCounselorId() {
        return counselorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getEmail() {
        return email;
    }

    public String getAvailability() {
        return availability;
    }

    // Setters
    public void setCounselorId(int counselorId) {
        this.counselorId = counselorId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "ID: " + counselorId + " - " + firstName + " " + lastName + " (" + specialization + ")";
    }
}
