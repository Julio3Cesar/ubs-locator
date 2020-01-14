package br.com.test.ubs.helpers;

import br.com.test.ubs.models.GeoCode;

public class GeoDistanceHelper {

    public static double distance(GeoCode referenceGeoCode, GeoCode compareGeoCode) {
        if ((referenceGeoCode.getLat() == compareGeoCode.getLat()) &&
                (referenceGeoCode.getLon() == compareGeoCode.getLon())) {
            return 0;
        } else {
            double theta = referenceGeoCode.getLon() - compareGeoCode.getLon();
            double dist = Math.sin(Math.toRadians(referenceGeoCode.getLat())) * Math.sin(Math.toRadians(compareGeoCode.getLat())) + Math.cos(Math.toRadians(referenceGeoCode.getLat())) * Math.cos(Math.toRadians(compareGeoCode.getLat())) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;

            return dist;
        }
    }
}
