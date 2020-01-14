package br.com.test.ubs.features;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

import br.com.test.ubs.UbsApplication;
import br.com.test.ubs.models.GeoCode;
import br.com.test.ubs.models.Score;
import br.com.test.ubs.models.Ubs;
import br.com.test.ubs.repositories.UbsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = UbsApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class GetNearbyUbsTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UbsRepository ubsRepository;

    @After
    public void resetDb() {
        ubsRepository.deleteAll();
    }

    @Test
    @DisplayName("Successfully on get nearby UBS's.")
    public void successfully() throws Exception {
        GeoCode geoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        Score score = new Score();
        Ubs ubs = new Ubs("Ubs Santa Paula", "Rua ali",
                "São Paulo", "11 4444-9999", geoCode, score);
        ubsRepository.save(ubs);

        mvc.perform(get("/api/v1/find_ubs?latitude=-10.9112370014188" +
                "&longitude=-37.0620775222768&page=1&per_page=20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].name", is(ubs.getName())));
    }

    @Test
    @DisplayName("Successfully on get two nearby UBS's in two pages.")
    public void successfullyWithTwoPages() throws Exception {
        GeoCode geoCode = new GeoCode(-10.9112370014188, -37.06207752227);
        Score score = new Score();
        Ubs ubs = new Ubs("Ubs Santa Paula", "Rua ali",
                "São Paulo", "11 4444-9999", geoCode, score);

        Ubs anotherUbs = new Ubs("Ubs Santa Joana", "Rua aqui",
                "São Paulo", "11 5555-8888", geoCode, score);
        ubsRepository.saveAll(Arrays.asList(ubs, anotherUbs));

        mvc.perform(get("/api/v1/find_ubs?latitude=-10.9112370014188" +
                "&longitude=-37.0620775222768&page=2&per_page=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].name", is(anotherUbs.getName())))
                .andExpect(jsonPath("$.content[0].name", not(ubs.getName())));
    }

    @Test
    @DisplayName("No UBS's within 5KM.")
    public void noneUbs() throws Exception {
        GeoCode geoCode = new GeoCode(-5.9112370014188, -60.06207752227);
        Score score = new Score();
        Ubs ubs = new Ubs("Ubs Santa Paula", "Rua ali",
                "São Paulo", "11 4444-9999", geoCode, score);
        ubsRepository.save(ubs);

        mvc.perform(get("/api/v1/find_ubs?latitude=-10.9112370014188" +
                "&longitude=-37.0620775222768&page=1&per_page=20")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(0)));
    }
}
