package com.aamlid.blueco.repository;

import com.aamlid.blueco.entity.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {

    Player findByName(String name);

    void deleteByName(String name);
}
