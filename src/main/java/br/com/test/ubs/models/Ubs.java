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
    @OneToOne
    @JoinColumn(name = "geo_code_id")
    private GeoCode geoCode;
    @OneToOne
    @JoinColumn(name = "scores_id")
    private Scores scores;
}
