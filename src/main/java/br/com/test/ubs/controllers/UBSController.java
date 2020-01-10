package br.com.test.ubs.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UBSController {

    @GetMapping(value = "v1/ubs")
    public String show(@RequestParam Double latitude,
                       @RequestParam Double longitude,
                       @RequestParam Integer page,
                       @RequestParam(name = "per_page") Integer perPage) {
        return "";
    }
}