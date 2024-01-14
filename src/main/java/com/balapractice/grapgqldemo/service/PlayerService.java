package com.balapractice.grapgqldemo.service;

import com.balapractice.grapgqldemo.model.Player;
import com.balapractice.grapgqldemo.model.Team;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  AtomicInteger id = new AtomicInteger(0);
  private List<Player> players = new ArrayList<>();

  public List<Player> findAll() {
    return players;
  }

  public Optional<Player> findOne(Integer id) {
    return players.stream().filter(player -> player.Id() == id).findFirst();
  }

  public Player create(String name, Team team) {
    Player player = new Player(id.incrementAndGet(), name, team);
    players.add(player);
    return player;
  }

  public Player delete(Integer id) {
    Player player = players.stream().filter(p -> p.Id() == id).findFirst()
        .orElseThrow(() -> new IllegalArgumentException());
    players.remove(player);
    return player;
  }

  public Player update(Integer id, String name, Team team) {
    Player updatedPlayer = new Player(id, name, team);
    Optional<Player> optional = players.stream().filter(p -> p.Id() == id).findFirst();
    if (optional.isPresent()) {
      Player player = optional.get();
      int index = players.indexOf(player);
      players.set(index, updatedPlayer);
    } else {
      throw new IllegalArgumentException();
    }

    return updatedPlayer;
  }

  @PostConstruct
  private void init() {
    players.add(new Player(id.incrementAndGet(), "Rohit", Team.MI));
    players.add(new Player(id.incrementAndGet(), "Bumra", Team.MI));
    players.add(new Player(id.incrementAndGet(), "MSD", Team.CSK));
    players.add(new Player(id.incrementAndGet(), "Jadeja", Team.CSK));
    players.add(new Player(id.incrementAndGet(), "Kohli", Team.RCB));
    players.add(new Player(id.incrementAndGet(), "Maxwel", Team.RCB));
    players.add(new Player(id.incrementAndGet(), "Bhuvi", Team.SRH));
    players.add(new Player(id.incrementAndGet(), "Warner", Team.SRH));
  }


}
