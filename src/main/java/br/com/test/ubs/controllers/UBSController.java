package br.com.test.ubs.controllers;

import br.com.test.ubs.models.GeoCode;
import br.com.test.ubs.services.UbsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UBSController {

    @Autowired
    private UbsService ubsService;

    @GetMapping(value = "v1/find_ubs")
    public Page<?> show(@RequestParam Double latitude,
                          @RequestParam Double longitude,
                          final Pageable pageable) {
        List<?> nearbyUbs = ubsService.nearbyUbs(new GeoCode(latitude, longitude));
        return paginate(nearbyUbs, pageable);
    }

    private Page<?> paginate(List<?> toPaginate, Pageable pageable) {
        int start;
        if (pageable.getOffset() < toPaginate.size()) {
            start = (int) pageable.getOffset();
        } else {
            return new PageImpl<>(new ArrayList<>(), pageable, toPaginate.size());
        }
        int end = (Math.min((start + pageable.getPageSize()), toPaginate.size()));
        return new PageImpl<>(toPaginate.subList(start, end), pageable, toPaginate.size());
    }
}