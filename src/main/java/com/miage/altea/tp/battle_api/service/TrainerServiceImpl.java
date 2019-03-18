package com.miage.altea.tp.battle_api.service;

import com.miage.altea.tp.battle_api.Utils.BattleUtilities;
import com.miage.altea.tp.battle_api.bo.PokemonType;
import com.miage.altea.tp.battle_api.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TrainerServiceImpl implements TrainersService {

    private RestTemplate restTemplate;
    private String url;
    private PokemonTypeService pokemonTypeService;

    @Autowired
    @Qualifier("trainerApiRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @Value("${trainers.service.url}")
    public void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.url = pokemonServiceUrl;
    }

    @Cacheable("trainers")
    @Override
    public Trainer getTrainer(String name) {
        Trainer trainer = this.restTemplate.getForObject(this.url+"/trainers/{name}", Trainer.class, name);

        trainer.getTeam().forEach(pokemon -> {
            PokemonType pokemonType = this.pokemonTypeService.getPokemonByTypeId(pokemon.getPokemonType().getId());

            pokemon.setPokemonType(pokemonType);
            pokemon.setHp(BattleUtilities.pokemonLife(pokemonType.getStats().getHp(), pokemon.getLevel()));
            pokemon.setSpeed(BattleUtilities.pokemonStat(pokemonType.getStats().getSpeed(), pokemon.getLevel()));
            pokemon.setAttack(BattleUtilities.pokemonStat(pokemonType.getStats().getAttack(), pokemon.getLevel()));
            pokemon.setDefense(BattleUtilities.pokemonStat(pokemonType.getStats().getDefense(), pokemon.getLevel()));
            pokemon.setMaxHp(BattleUtilities.pokemonLife(pokemonType.getStats().getHp(), pokemon.getLevel()));
            pokemon.setAlive(true);
            pokemon.setKo(false);
        });

        return trainer;
    }
}
