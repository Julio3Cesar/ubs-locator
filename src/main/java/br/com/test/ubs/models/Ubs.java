package br.com.test.ubs.models;

import javax.persistence.*;

@Entity
public class Ubs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String city;
    private String Phone;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "geo_code_id")
    private GeoCode geoCode;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "scores_id")
    private Score score;

    public Ubs() {
    }

    public Ubs(String name, String address, String city, String phone, GeoCode geoCode, Score score) {
        this.name = name;
        this.address = address;
        this.city = city;
        Phone = phone;
        this.geoCode = geoCode;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public GeoCode getGeoCode() {
        return geoCode;
    }

    public void setGeoCode(GeoCode geoCode) {
        this.geoCode = geoCode;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
