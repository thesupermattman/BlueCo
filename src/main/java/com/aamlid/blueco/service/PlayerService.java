package com.aamlid.blueco.service;

import com.aamlid.blueco.entity.Player;
import com.aamlid.blueco.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Player update(String name, Player updatedPlayer) {
        Optional<Player> optionalPlayer = Optional.ofNullable(playerRepository.findByName(name));

        if (optionalPlayer.isPresent()) {
            Player existingPlayer = optionalPlayer.get();

            // Update the fields you want to change
            existingPlayer.setName(updatedPlayer.getName());
            existingPlayer.setNumber(updatedPlayer.getNumber());
            existingPlayer.setPosition(updatedPlayer.getPosition());
            existingPlayer.setPrice(updatedPlayer.getPrice());

            return playerRepository.save(existingPlayer);
        } else {
            return null;
        }
    }

    public void deleteByName(String name) {
        playerRepository.deleteByName(name);
    }
}
