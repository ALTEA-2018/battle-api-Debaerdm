package com.miage.altea.tp.battle_api.repository;

import com.miage.altea.tp.battle_api.bo.Battle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BattleRepository {
    List<Battle> findAll();
    Battle findById(UUID uuid);
    void update(Battle battle);
}
