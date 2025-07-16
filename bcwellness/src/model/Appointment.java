package model;

import java.sql.Time;
import java.sql.Date;

public class Appointment {
    private int id;
    private String studentName;
    private int counselorId;
    private Date date;
    private Time time;
    private String status;

    public Appointment(int id, String studentName, int counselorId, String dateStr, String timeStr, String status) {
        this.id = id;
        this.studentName = studentName;
        this.counselorId = counselorId;
        this.date = Date.valueOf(dateStr);
        this.time = Time.valueOf(timeStr);
        this.status = status;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getCounselorId() {
        return counselorId;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
