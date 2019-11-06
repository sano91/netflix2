package com.codecool.recomendation.repository;

import com.codecool.recomendation.entity.Recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {





    @Query(value="SELECT r from Recommendation r where r.videoID = :id")
    List<Recommendation> getAllRecommendationsByVideoId(@Param("id") int id);




}
