package br.com.test.ubs.jobs.mappers;

import br.com.test.ubs.models.GeoCode;
import br.com.test.ubs.models.Score;
import br.com.test.ubs.models.Ubs;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class UbsFieldMapper implements FieldSetMapper<Ubs> {

    @Override
    public Ubs mapFieldSet(FieldSet fieldSet) {
        GeoCode geoCode = new GeoCode();
        geoCode.setLat(fieldSet.readDouble("vlr_latitude"));
        geoCode.setLon(fieldSet.readDouble("vlr_longitude"));

        Score score = new Score();
        score.setAdaptation_for_seniors(scoreAttributesConverter(fieldSet.readString("dsc_adap_defic_fisic_idosos")));
        score.setMedical_equipment(scoreAttributesConverter(fieldSet.readString("dsc_equipamentos")));
        score.setMedicine(scoreAttributesConverter(fieldSet.readString("dsc_medicamentos")));
        score.setSize(scoreAttributesConverter(fieldSet.readString("dsc_estrut_fisic_ambiencia")));

        Ubs ubs = new Ubs();
        ubs.setName(fieldSet.readString("nom_estab"));
        ubs.setPhone(fieldSet.readString("dsc_telefone"));
        ubs.setAddress(fieldSet.readString("dsc_endereco"));
        ubs.setCity(fieldSet.readString("dsc_cidade"));
        ubs.setGeoCode(geoCode);
        ubs.setScore(score);
        return ubs;
    }

    private Integer scoreAttributesConverter(String attributeValue) {
        if (attributeValue.equals("Desempenho mediano ou  um pouco abaixo da média")) {
            return 1;
        } else if (attributeValue.equals("Desempenho acima da média")) {
            return 2;
        } else if (attributeValue.equals("Desempenho muito acima da média")) {
            return 3;
        }
        return 0;
    }
}
