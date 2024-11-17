package com.strava.entity;

import java.util.Date;

public class Reto {

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Float targetDistance;
    private Integer targetDuration;
    private String sport;  // Puede ser "cycling" o "running"

    public Reto() {
        // Constructor vacío para JPA
    }

    // Constructor con todos los atributos
    public Reto(String name, Date startDate, Date endDate, Float targetDistance, Integer targetDuration, String sport) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
