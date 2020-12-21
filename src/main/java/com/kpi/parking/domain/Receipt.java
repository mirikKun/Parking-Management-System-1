package com.kpi.parking.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;


public class Receipt {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endTime;
    private int hours;
    private int fee;
    private int totalCost;

    public Receipt(LocalDateTime creationDate, LocalDateTime endTime, int hours, int fee, int totalCost) {
        this.creationDate = creationDate;
        this.endTime = endTime;
        this.hours = hours;
        this.fee = fee;
        this.totalCost = totalCost;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getParkingTime() {
        return hours;
    }

    public void setParkingTime(int hours) {
        this.hours = hours;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "creationDate=" + creationDate +
                ", endTime=" + endTime +
                ", hours=" + hours +
                ", fee=" + fee +
                ", totalCost=" + totalCost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return hours == receipt.hours && fee == receipt.fee && totalCost == receipt.totalCost && creationDate.equals(receipt.creationDate) && endTime.equals(receipt.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, endTime, hours, fee, totalCost);
    }
}
