package model;

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
}
