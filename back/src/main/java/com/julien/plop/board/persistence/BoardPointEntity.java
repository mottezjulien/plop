package com.julien.plop.board.persistence;


import com.julien.plop.board.domain.BoardPoint;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "TEST1_BOARD_POINT")
public class BoardPointEntity {

    @Id
    @UuidGenerator
    protected String id;

    private double lat;

    private double lng;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public BoardPoint toModel() {
        return new BoardPoint(lat, lng);
    }
}
