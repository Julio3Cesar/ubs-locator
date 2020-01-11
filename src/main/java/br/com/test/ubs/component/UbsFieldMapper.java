package br.com.test.ubs.component;

import br.com.test.ubs.models.GeoCode;
import br.com.test.ubs.models.Scores;
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

        Scores scores = new Scores();
        scores.setAdaptation_for_seniors(scroreAttributesConverter(fieldSet.readString("dsc_adap_defic_fisic_idosos")));
        scores.setMedical_equipment(scroreAttributesConverter(fieldSet.readString("dsc_equipamentos")));
        scores.setMedicine(scroreAttributesConverter(fieldSet.readString("dsc_medicamentos")));
        scores.setSize(scroreAttributesConverter(fieldSet.readString("dsc_estrut_fisic_ambiencia")));

        Ubs ubs = new Ubs();
        ubs.setName(fieldSet.readString("nom_estab"));
        ubs.setPhone(fieldSet.readString("dsc_telefone"));
        ubs.setAddress(fieldSet.readString("dsc_endereco"));
        ubs.setCity(fieldSet.readString("dsc_cidade"));
        ubs.setGeoCode(geoCode);
        ubs.setScores(scores);
        return ubs;
    }

    private Integer scroreAttributesConverter(String attributeValue) {
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
