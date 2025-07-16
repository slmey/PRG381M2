package model;

public class Counselor {
    private int counselorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String availability;

    public Counselor(int id, String firstName, String lastName, String specialization, String email, String availability) {
        this.counselorId = counselorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.availability = availability;
    }
}
