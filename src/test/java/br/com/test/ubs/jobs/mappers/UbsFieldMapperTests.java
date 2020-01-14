package br.com.test.ubs.jobs.mappers;

import br.com.test.ubs.models.Ubs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UbsFieldMapperTests {

    FieldSetMapper<Ubs> ubsFieldSetMapper = new UbsFieldMapper();

    @Test
    @DisplayName("Successfully, when FieldSet has the valid parameters")
    public void successfully() throws Exception {
        // given
        String[] tokens = new String[]{"-10.9112370014188", "-37.0620775222768", "280030", "3492", "US OSWALDO DE SOUZA",
                "TV ADALTO BOTELHO", "GETULIO VARGAS", "Aracaju", "7931791326", "Desempenho acima da média",
                "Desempenho muito acima da média", "Desempenho mediano ou  um pouco abaixo da média", "Desempenho acima da média"};
        String[] names = new String[]{"vlr_latitude", "vlr_longitude", "cod_munic", "cod_cnes", "nom_estab",
                "dsc_endereco", "dsc_bairro", "dsc_cidade", "dsc_telefone", "dsc_estrut_fisic_ambiencia",
                "dsc_adap_defic_fisic_idosos", "dsc_equipamentos", "dsc_medicamentos"};
        FieldSet fieldSet = new DefaultFieldSet(tokens, names);

        // when
        Ubs ubs = ubsFieldSetMapper.mapFieldSet(fieldSet);

        // then
        Assert.assertEquals((Double) Double.parseDouble(tokens[0]), ubs.getGeoCode().getLat());
        Assert.assertEquals((Double) Double.parseDouble(tokens[1]), ubs.getGeoCode().getLon());

        Assert.assertEquals(tokens[4], ubs.getName());
        Assert.assertEquals(tokens[5], ubs.getAddress());
        Assert.assertEquals(tokens[7], ubs.getCity());
        Assert.assertEquals(tokens[8], ubs.getPhone());

        Assert.assertEquals((Integer) 2, ubs.getScore().getSize());
        Assert.assertEquals((Integer) 3, ubs.getScore().getAdaptation_for_seniors());
        Assert.assertEquals((Integer) 1, ubs.getScore().getMedical_equipment());
        Assert.assertEquals((Integer) 2, ubs.getScore().getMedicine());
    }
}
