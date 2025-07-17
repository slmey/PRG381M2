package model;

import java.sql.Date;
import java.sql.Time;

/**
 * Represents an appointment in the wellness management system, storing details
 * such as student name, counselor ID, date, time, and status.
 */
public class Appointment {
    private int id;           // Unique identifier for the appointment
    private String studentName; // Name of the student booking the appointment
    private int counselorId;   // ID of the assigned counselor
    private Date date;         // Date of the appointment
    private Time time;         // Time of the appointment
    private String status;     // Current status (e.g., Scheduled, Confirmed)

    /**
     * Constructs a new Appointment with the specified details.
     * id The unique identifier for the appointment
     * studentName The name of the student
     * counselorId The ID of the counselor
     * date The date of the appointment
     * time The time of the appointment
     * status The current status of the appointment
     */

    public Appointment(int id, String studentName, int counselorId, Date date, Time time, String status) {
        this.id = id;
        this.studentName = studentName;
        this.counselorId = counselorId;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    // Getters for accessing appointment details
    /**
     * Returns the unique identifier of the appointment.
     * return The appointment ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the student for this appointment.
     * return The student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Returns the ID of the assigned counselor.
     * return The counselor ID
     */
    public int getCounselorId() {
        return counselorId;
    }

    /**
     * Returns the date of the appointment.
     * return The appointment date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the time of the appointment.
     * return The appointment time
     */
    public Time getTime() {
        return time;
    }

    /**
     * Returns the current status of the appointment.
     * return The appointment status
     */
    public String getStatus() {
        return status;
    }
}