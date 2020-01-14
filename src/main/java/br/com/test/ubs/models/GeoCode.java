package br.com.test.ubs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GeoCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double lat;
    private Double lon;

    public GeoCode(Double latitude, Double longitude) {
        this.lat = latitude;
        this.lon = longitude;
    }

    public GeoCode() {
    }

    public Long getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
