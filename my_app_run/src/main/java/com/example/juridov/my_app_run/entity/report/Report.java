package com.example.juridov.my_app_run.entity.report;

public class Report {
    private double avSpeed;
    private double avTime;
    private int totalDistance;
    private int weekNumber;
    private long weekStart;
    private long weekEnd;

    public double getAvSpeed() {
        return avSpeed;
    }

    public void setAvSpeed(double avSpeed) {
        this.avSpeed = avSpeed;
    }

    public double getAvTime() {
        return avTime;
    }

    public void setAvTime(double avTime) {
        this.avTime = avTime;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public long getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(long weekStart) {
        this.weekStart = weekStart;
    }

    public long getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(long weekEnd) {
        this.weekEnd = weekEnd;
    }

}