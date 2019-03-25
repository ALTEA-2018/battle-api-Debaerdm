package com.miage.altea.tp.battle_api.controller;

import com.miage.altea.tp.battle_api.bo.Battle;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BattleControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BattleController controller;

    @Test
    void trainerController_shouldBeInstanciated(){
        assertNotNull(controller);
    }

    /*@Test
    void getTrainers_shouldThrowAnUnauthorized(){
        var responseEntity = this.restTemplate.getForEntity("http://localhost:" + port + "/api/battles", Battle[].class);

        assertNull(responseEntity);
    }*/

    /*@Test
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate.getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);

        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());

        assertEquals(25, ash.getTeam().get(0).getPokemonType());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }

    @Test
    void addTrainer_withTrainer_shouldReturnAsh() {
        var trainer = new Trainer("mock");
        trainer.setTeam(List.of(new Pokemon(120, 18), new Pokemon(125, 30)));

        var response = this.restTemplate
                .withBasicAuth(username, password)
                .postForEntity("http://localhost:" + port + "/trainers/", trainer, Trainer.class);

        var responseTrainer = response.getBody();
        assertNotNull(responseTrainer);
        assertEquals("mock", responseTrainer.getName());
        assertEquals(2, responseTrainer.getTeam().size());

        assertEquals(120, responseTrainer.getTeam().get(0).getPokemonType());
        assertEquals(18, responseTrainer.getTeam().get(0).getLevel());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {
        var trainers = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);

        assertNotNull(trainers);
        assertEquals(2, trainers.length);

        assertEquals("Ash", trainers[0].getName());
        assertEquals("Misty", trainers[1].getName());
    }*/
}
