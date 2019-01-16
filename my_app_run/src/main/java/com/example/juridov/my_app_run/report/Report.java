package com.example.juridov.my_app_run.report;

public class Report {
    private Double avSpeed;
    private Double avTime;
    private Integer totalDistance;
    private Integer weekNumber;

    public Report(Double avSpeed, Double avTime, Integer totalDistance, Integer weekNumber) {
        this.avSpeed = avSpeed;
        this.avTime = avTime;
        this.totalDistance = totalDistance;
        this.weekNumber = weekNumber;
    }

    public Double getAvSpeed() {
        return avSpeed;
    }

    public void setAvSpeed(Double avSpeed) {
        this.avSpeed = avSpeed;
    }

    public Double getAvTime() {
        return avTime;
    }

    public void setAvTime(Double avTime) {
        this.avTime = avTime;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Integer getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

}
