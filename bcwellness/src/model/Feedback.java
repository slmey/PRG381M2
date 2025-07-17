package model;

/*
 * Represents feedback submitted by a student in the wellness management system.
 */
public class Feedback {
    private int id;
    private String studentName;
    private String studentEmail;
    private String feedback;
    private int rating;

    public Feedback(int id, String studentName, String studentEmail, String feedback, int rating) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.feedback = feedback;
        this.rating = rating;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getFeedback() {
        return feedback;
    }

    public int getRating() {
        return rating;
    }
}