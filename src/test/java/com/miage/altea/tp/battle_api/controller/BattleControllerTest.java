package com.miage.altea.tp.battle_api.controller;

import com.miage.altea.tp.battle_api.bo.Battle;
import com.miage.altea.tp.battle_api.bo.Fight;
import com.miage.altea.tp.battle_api.service.BattleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

public class BattleControllerTest {

    @Mock
    private BattleService battleService;

    @InjectMocks
    private BattleController battleController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBattle_shouldCallTheService() {
        battleController.battles();

        verify(battleService).getBattles();
    }

    @Test
    void getBattles_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllBattles = BattleController.class.getDeclaredMethod("battles");
        var getMapping = getAllBattles.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/battles"}, getMapping.value());
    }

    @Test
    void postBattle_shouldBeAnnotated() throws NoSuchMethodException {
        var getBattle = BattleController.class.getDeclaredMethod("battles", Fight.class);
        var postMapping = getBattle.getAnnotation(PostMapping.class);

        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/battles"}, postMapping.value());
    }

    @Test
    void getBattle_shouldBeAnnotated() throws NoSuchMethodException {
        var getBattle = BattleController.class.getDeclaredMethod("battle", UUID.class);
        var postMapping = getBattle.getAnnotation(GetMapping.class);

        var pathVariableAnnotation = getBattle.getParameters()[0].getAnnotation(PathVariable.class);

        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/battles/{uuid}"}, postMapping.value());
        assertNotNull(pathVariableAnnotation);
    }

    @Test
    void attack_shouldBeAnnotated() throws NoSuchMethodException {
        var attack = BattleController.class.getDeclaredMethod("attack", UUID.class, String.class);
        var postMapping = attack.getAnnotation(PostMapping.class);

        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/battles/{uuid}/{trainerName}/attack"}, postMapping.value());
    }
}
