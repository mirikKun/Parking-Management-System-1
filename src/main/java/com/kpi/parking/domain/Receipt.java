package com.kpi.parking.domain;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Receipt {
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

    private LocalDateTime creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
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

}
