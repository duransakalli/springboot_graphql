package com.drn.service;

import com.drn.model.Player;
import com.drn.model.Team;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findOne(Integer id) {
        return players.stream()
                .filter(player -> Objects.equals(player.getId(), id)).findFirst();
    }

    public Player createPlayer(String firstName, String lastName, Team team) {
        Player player = new Player(id.incrementAndGet(), firstName, lastName, team);
        players.add(player);
        return player;
    }

    public Player updatePlayer(Integer id, String firstName, String lastName, Team team) {
        Player updatedPlayer = new Player(id, firstName, lastName, team);
        Optional<Player> optionalPlayer = players.stream()
                .filter(c -> Objects.equals(c.getId(), id)).findFirst();
        if(optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            int index = players.indexOf(player);
            players.set(index, updatedPlayer);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayer;
    }

    public Player deletePlayer(Integer id) {
        Player player = players.stream().filter(c -> Objects.equals(c.getId(), id))
                .findFirst().orElseThrow();
        players.remove(player);
        return player;
    }

    @PostConstruct
    private void init() {
        for(int i = 0; i < 100; i++) {
            players.add(generateRandomPlayer());
        }
    }

    private Player generateRandomPlayer() {
        Faker faker = new Faker();
        Integer playerId = id.incrementAndGet();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        Team team = getRandomTeam();
        return new Player(playerId, firstName, lastName, team);
    }

    private Team getRandomTeam() {
        Team[] teams = Team.values();
        Random random = new Random();
        int index = random.nextInt(teams.length);
        return teams[index];
    }

}
