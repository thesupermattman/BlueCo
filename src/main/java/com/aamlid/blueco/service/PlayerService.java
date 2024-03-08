package com.aamlid.blueco.service;

import com.aamlid.blueco.entity.Budget;
import com.aamlid.blueco.entity.Player;
import com.aamlid.blueco.repository.BudgetRepository;
import com.aamlid.blueco.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final BudgetRepository budgetRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, BudgetRepository budgetRepository) {
        this.playerRepository = playerRepository;
        this.budgetRepository = budgetRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findByName(String name) {
        return playerRepository.findByName(name);
    }

    public Player save(Player player) {
        Budget transferBudget = budgetRepository.findByType("Transfer Budget");
        Player newPlayer = playerRepository.save(player);

        int updatedTransferBudget = transferBudget.getAmount() - newPlayer.getPrice();
        transferBudget.setAmount(updatedTransferBudget);
        budgetRepository.save(transferBudget);

        return newPlayer;
    }

    public Player update(String name, Player updatedPlayer) {
        Optional<Player> optionalPlayer = Optional.ofNullable(playerRepository.findByName(name));

        if (optionalPlayer.isPresent()) {
            Player existingPlayer = optionalPlayer.get();

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
        Budget transferBudget = budgetRepository.findByType("Transfer Budget");
        Player deletedPlayer = playerRepository.findByName(name);

        int updatedBudget = transferBudget.getAmount() + deletedPlayer.getPrice();
        transferBudget.setAmount(updatedBudget);
        budgetRepository.save(transferBudget);

        playerRepository.deleteByName(name);
    }
}
