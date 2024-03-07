package com.aamlid.blueco.service;

import com.aamlid.blueco.entity.Player;
import com.aamlid.blueco.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findByName(String name) {
        return playerRepository.findByName(name);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
