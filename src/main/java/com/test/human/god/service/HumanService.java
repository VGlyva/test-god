package com.test.human.god.service;

import com.test.human.god.model.Human;
import com.test.human.god.repositories.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HumanService {
    @Autowired
    private final HumanRepository humanRepository;

    public HumanService(HumanRepository humanRepository) {
        this.humanRepository = humanRepository;
    }
    public Human createHuman(Human human) {
        return humanRepository.save(human);
    }
    public Human findHuman(long id) {
        return humanRepository.findById(id).get();
    }
    public Human editHuman(Human human) {
        return humanRepository.save(human);
    }
    public ResponseEntity<?> deleteHuman(long id) {
        humanRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
