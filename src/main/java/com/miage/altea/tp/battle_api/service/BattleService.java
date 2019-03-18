package com.miage.altea.tp.battle_api.service;

import com.miage.altea.tp.battle_api.bo.Battle;

import java.util.UUID;

public interface BattleService {
    Iterable<Battle> getBattles();
    Battle getBattle(UUID uuid);
    Battle createBattle(String trainer, String opponent);
    Battle attack(UUID uuid, String trainer);
}