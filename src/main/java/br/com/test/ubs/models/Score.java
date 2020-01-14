package br.com.test.ubs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer size;
    private Integer adaptation_for_seniors;
    private Integer medical_equipment;
    private Integer medicine;

    public Long getId() {
        return id;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getAdaptation_for_seniors() {
        return adaptation_for_seniors;
    }

    public void setAdaptation_for_seniors(Integer adaptation_for_seniors) {
        this.adaptation_for_seniors = adaptation_for_seniors;
    }

    public Integer getMedical_equipment() {
        return medical_equipment;
    }

    public void setMedical_equipment(Integer medical_equipment) {
        this.medical_equipment = medical_equipment;
    }

    public Integer getMedicine() {
        return medicine;
    }

    public void setMedicine(Integer medicine) {
        this.medicine = medicine;
    }
}
