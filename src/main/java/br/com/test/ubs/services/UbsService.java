package br.com.test.ubs.services;

import br.com.test.ubs.helpers.GeoDistanceHelper;
import br.com.test.ubs.models.GeoCode;
import br.com.test.ubs.models.Ubs;
import br.com.test.ubs.repositories.UbsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UbsService {

    @Autowired
    private UbsRepository ubsRepository;
    private static final Integer RADIOS_5KM = 5;

    public List<Ubs> nearbyUbs(GeoCode geoCodeReference) {

        Iterable<Ubs> ubsIterator = ubsRepository.findAll();
        List<Ubs> nearbyUbs = new ArrayList<>();
        ubsIterator.forEach(ubs -> {
            Double distance = GeoDistanceHelper.distance(geoCodeReference, ubs.getGeoCode());
            if(distance <= RADIOS_5KM){
                nearbyUbs.add(ubs);
            }
        });

        return nearbyUbs;
    }
}
