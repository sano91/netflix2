package com.codecool.recomendation.controller;

import com.codecool.recomendation.entity.Recommendation;
import com.codecool.recomendation.repository.RecommendationRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RestController
@Slf4j
@RequestMapping("/recommendation")
public class RecommendationController {


    @Autowired
    RecommendationRepository recommendationRepository;

    @GetMapping(path="/all-recommendations/{videoId}")
    public List<Recommendation> allRecommendationsByVideoID(@PathVariable int videoId){

        return recommendationRepository.getAllRecommendationsByVideoId(videoId);
    }

    @PostMapping(path="/new-comment")
    public void saveNewRecommendation(@RequestBody Recommendation recommendation){

        recommendationRepository.saveAndFlush(recommendation);
    }



    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
