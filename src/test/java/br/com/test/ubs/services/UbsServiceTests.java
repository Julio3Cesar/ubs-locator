package br.com.test.ubs.services;

import br.com.test.ubs.models.GeoCode;
import br.com.test.ubs.models.Score;
import br.com.test.ubs.models.Ubs;
import br.com.test.ubs.repositories.UbsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UbsServiceTests {

    @Autowired
    private UbsService ubsService;

    @MockBean
    private UbsRepository ubsRepository;

    @Test
    @DisplayName("Successfully on get one nearby UBS's.")
    public void successfully() throws Exception {
        // given
        GeoCode geoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        Score score = new Score();
        Ubs ubs = new Ubs("Ubs Santa Paula", "Rua ali",
                "São Paulo", "11 4444-9999", geoCode, score);
        GeoCode notNearbyGeoCode = new GeoCode(-5.9112370014188, -20.06207752227);
        Ubs notNearbyUbs = new Ubs("Ubs Santa Paula", "Rua ali",
                "São Paulo", "11 4444-9999", notNearbyGeoCode, score);

        Mockito.when(ubsRepository.findAll()).thenReturn(Arrays.asList(ubs, notNearbyUbs));

        // when
        GeoCode referenceGeoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        List<Ubs> nearbyUbs = ubsService.nearbyUbs(referenceGeoCode);

        // then
        Assert.assertThat(nearbyUbs, hasSize(1));
        Assert.assertEquals(ubs.getName(), nearbyUbs.get(0).getName());
    }

    @Test
    @DisplayName("Successfully on get zero nearby UBS's.")
    public void successfullyOnGetZeroNearbyUbs() throws Exception {
        // given
        GeoCode notNearbyGeoCode = new GeoCode(-5.9112370014188, -20.06207752227);
        Score score = new Score();
        Ubs notNearbyUbs = new Ubs("Ubs Santa Paula", "Rua ali",
                "São Paulo", "11 4444-9999", notNearbyGeoCode, score);

        Mockito.when(ubsRepository.findAll()).thenReturn(Arrays.asList(notNearbyUbs));

        // when
        GeoCode referenceGeoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        List<Ubs> nearbyUbs = ubsService.nearbyUbs(referenceGeoCode);

        // then
        Assert.assertThat(nearbyUbs, hasSize(0));
    }



    @Test
    @DisplayName("Successfully, when there are no ubs in the base")
    public void successfullyWhereThereAreNoUbs() throws Exception {
        // given
        List<Ubs> empty = new ArrayList<>();

        Mockito.when(ubsRepository.findAll()).thenReturn(empty);

        // when
        GeoCode referenceGeoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        List<Ubs> nearbyUbs = ubsService.nearbyUbs(referenceGeoCode);

        // then
        Assert.assertThat(nearbyUbs, hasSize(0));
    }
}
