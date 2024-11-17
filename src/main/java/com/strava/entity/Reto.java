package com.strava.entity;

import java.util.Date;

public class Reto {

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private Float targetDistance;
    private Integer targetDuration;
    private String sport;  // "cycling" o "running"


    // Constructor con todos los atributos
    public Reto(long id, String name, String startDate, String endDate, Float targetDistance, Integer targetDuration, String sport) {
    	this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.targetDistance = targetDistance;
        this.targetDuration = targetDuration;
        this.sport = sport;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Float getTargetDistance() {
        return targetDistance;
    }

    public void setTargetDistance(Float targetDistance) {
        this.targetDistance = targetDistance;
    }

    public Integer getTargetDuration() {
        return targetDuration;
    }

    public void setTargetDuration(Integer targetDuration) {
        this.targetDuration = targetDuration;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "Reto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", targetDistance=" + targetDistance +
                ", targetDuration=" + targetDuration +
                ", sport='" + sport + '\'' +
                '}';
    }
}
