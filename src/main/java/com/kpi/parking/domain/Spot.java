package com.kpi.parking.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "spots")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "is_free")
    private boolean isFree;
    private String type;
    @Column(name = "floor_id")
    private int floorId;

    public Spot() {
    }

    public Spot(boolean isFree, String type, int floorId) {
        this.isFree = isFree;
        this.type = type;
        this.floorId = floorId;
    }

    public Spot(int id, boolean isFree, String type, int floorId) {
        this.id = id;
        this.isFree = isFree;
        this.type = type;
        this.floorId = floorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(boolean free) {
        isFree = free;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spot spot = (Spot) o;
        return id == spot.id &&
                isFree == spot.isFree &&
                floorId == spot.floorId &&
                type.equals(spot.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isFree, type, floorId);
    }
}
