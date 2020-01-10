package br.com.test.ubs.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer size;
    private Integer adaptation_for_seniors;
    private Integer medical_equipment;
    private Integer medicine;
}
