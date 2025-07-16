package model;

public class Appointment {
    private int id;
    private String studentName;
    private int counselorId;
    private String date;
    private String time;
    private String status;

    public Appointment(int id, String studentName, int counselorId, String date, String time, String status) {
        this.id = id;
        this.studentName = studentName;
        this.counselorId = counselorId;
        this.date = date;
        this.time = time;
        this.status = status;
    }
}
