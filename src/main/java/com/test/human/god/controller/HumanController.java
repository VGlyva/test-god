package com.test.human.god.controller;

import com.test.human.god.model.Human;
import com.test.human.god.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("humans")
public class HumanController {
    @Autowired
    private final HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }
    @PostMapping
    public ResponseEntity<Human> createHuman(@RequestBody Human human) {
        Human createHuman = humanService.createHuman(human);
        return ResponseEntity.ok(createHuman);
    }
    @GetMapping("{id}")
    public ResponseEntity<Human> getHuman(@PathVariable long id) {
        Human human = humanService.findHuman(id);
        if (human == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(human);
    }
    @PutMapping("{id}") // PUT http://localhost:8080/students/1
    public ResponseEntity<Human> editHuman(@RequestBody Human human) {
        Human foundHuman = humanService.editHuman(human);
        if (foundHuman == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundHuman);
    }
    @DeleteMapping("{id}")// DELETE http://localhost:8080/students/1
    public void deleteHuman(@PathVariable long id) {
        humanService.deleteHuman(id);
    }
}
