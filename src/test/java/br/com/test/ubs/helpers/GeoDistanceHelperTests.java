package br.com.test.ubs.helpers;

import br.com.test.ubs.models.GeoCode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeoDistanceHelperTests {

    @Test
    @DisplayName("Successfully, when the distance is equal to 0km")
    public void successfullyDistanceLessThanFiveKM() {
        // given
        GeoCode referenceGeoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        GeoCode compareGeoCode = new GeoCode(-10.9112370014188, -37.06207752227);

        // when
        Double distance = GeoDistanceHelper.distance(referenceGeoCode, compareGeoCode);

        // then
        Assert.assertTrue(distance == 0);
    }

    @Test
    @DisplayName("Successfully, when the distance is greater than 0km")
    public void successfullyDistanceGreaterThanFiveKM() {
        // given
        GeoCode referenceGeoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        GeoCode compareGeoCode = new GeoCode(-5.9112370014188, -30.06207752227);

        // when
        Double distance = GeoDistanceHelper.distance(referenceGeoCode, compareGeoCode);

        // then
        Assert.assertTrue(distance > 0);
    }
}
