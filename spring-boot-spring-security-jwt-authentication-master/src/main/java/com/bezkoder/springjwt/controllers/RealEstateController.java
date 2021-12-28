package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.models.RealEstate;
import com.bezkoder.springjwt.repository.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RealEstateController {
    @Autowired
    RealEstateRepository repository;
    @GetMapping("/realestate")
    public ResponseEntity<List<RealEstate>> getAllRealEstate(@RequestParam(required = false) String name) {
        try {
            List<RealEstate> tutorials = new ArrayList<RealEstate>();

            if (name == null)
                repository.findAll().forEach(tutorials::add);
            else
                repository.findByNameContaining(name).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/realestate/{id}")
    public ResponseEntity<RealEstate> getTutorialById(@PathVariable("id") long id) {
        Optional<RealEstate> tutorialData = repository.findById(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/realestate")
    public ResponseEntity<RealEstate> createTutorial(@RequestBody RealEstate tutorial) {
        try {
            RealEstate _tutorial = repository
                    .save(new RealEstate(tutorial.getName(), tutorial.getDescription(), tutorial.getPrice(),tutorial.getImage()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/realestate/{id}")
    public ResponseEntity<RealEstate> updateTutorial(@PathVariable("id") long id, @RequestBody RealEstate tutorial) {
        Optional<RealEstate> tutorialData = repository.findById(id);

        if (tutorialData.isPresent()) {
            RealEstate _tutorial = tutorialData.get();
            _tutorial.setName(tutorial.getName());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPrice(tutorial.getPrice());
            _tutorial.setImage(tutorial.getImage());
            return new ResponseEntity<>(repository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/realestate/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/realestate")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
