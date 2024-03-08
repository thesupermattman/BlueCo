package com.aamlid.blueco.controller;

import com.aamlid.blueco.entity.Player;
import com.aamlid.blueco.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> findAll() {
        return playerService.findAll();
    }

    @GetMapping("/players/{name}")
    public Player getPlayer(@PathVariable String name) {
        return playerService.findByName(name);
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return playerService.save(player);
    }

    @PutMapping("/players/{name}")
    public ResponseEntity<Player> updatePlayer(@PathVariable String name, @RequestBody Player player) {
        Player existingPlayer = playerService.update(name, player);

        if (existingPlayer != null) {
            return new ResponseEntity<>(player, HttpStatus.OK);
        } else {
            // Handle the case where the player with the given ID is not found
            // You can customize the response or return a specific error message
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public String deletePlayer(@PathVariable String name) {

        Player tempPlayer = playerService.findByName(name);

        if (tempPlayer == null) {
            throw new RuntimeException("Player not found - " + name);
        }

        playerService.deleteByName(name);

        return "Deleted player - " + name;
    }
}
