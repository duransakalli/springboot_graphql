package com.drn.controller;

import com.drn.model.Player;
import com.drn.model.Team;
import com.drn.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id) {
        return playerService.findOne(id);
    }

    @MutationMapping
    public Player create(@Argument String firstName, @Argument String lastName, @Argument Team team) {
        return playerService.createPlayer(firstName, lastName, team);
    }

    @MutationMapping
    public Player delete(@Argument Integer id) {
        return playerService.deletePlayer(id);
    }

    @MutationMapping
    public Player update(@Argument Integer id, @Argument String firstName, @Argument String lastName, @Argument Team team) {
        return playerService.updatePlayer(id, firstName, lastName, team);
    }
}
