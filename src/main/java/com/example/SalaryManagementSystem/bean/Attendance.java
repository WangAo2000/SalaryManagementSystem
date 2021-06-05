package com.example.SalaryManagementSystem.bean;

public class Attendance {
    private String userId;
    private int requiredTime;
    private int actualTime;
    private int leaveTime;
    private int absenceTime;

    public Attendance() {
    }

    public Attendance(String userId, int requiredTime, int actualTime, int leaveTime, int absenceTime) {
        this.userId = userId;
        this.requiredTime = requiredTime;
        this.actualTime = actualTime;
        this.leaveTime = leaveTime;
        this.absenceTime = absenceTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(int requiredTime) {
        this.requiredTime = requiredTime;
    }

    public int getActualTime() {
        return actualTime;
    }

    public void setActualTime(int actualTime) {
        this.actualTime = actualTime;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getAbsenceTime() {
        return absenceTime;
    }

    public void setAbsenceTime(int absenceTime) {
        this.absenceTime = absenceTime;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "userId='" + userId + '\'' +
                ", requiredTime=" + requiredTime +
                ", actualTime=" + actualTime +
                ", leaveTime=" + leaveTime +
                ", absenceTime=" + absenceTime +
                '}';
    }
}
