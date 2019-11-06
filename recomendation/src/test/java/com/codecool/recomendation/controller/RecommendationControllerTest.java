package com.codecool.recomendation.controller;

import com.codecool.recomendation.RecomendationApplication;
import com.codecool.recomendation.entity.Recommendation;
import com.codecool.recomendation.repository.RecommendationRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(RecommendationController.class)
@ContextConfiguration(classes= RecomendationApplication.class)
@ExtendWith(MockitoExtension.class)
@EnableAutoConfiguration
class RecommendationControllerTest {




    @Autowired
    Gson gson;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    RecommendationController recommendationController;

    @Autowired
    RestTemplate restTemplate;

    @Value("${recommendation.url}")
    private String baseUrl;




    @Test
    void allRecommendationsByVideoIDTest() {
        Recommendation recommendation = Recommendation.builder()
                .videoID(43)
                .rate(3)
                .comment("matyi")
                .id(16)
                .build();

        Recommendation recommendation1 = Recommendation.builder()
                .videoID(23)
                .rate(5)
                .comment("hello")
                .id(43)
                .build();

        Recommendation recommendation2 = Recommendation.builder()
                .rate(1)
                .videoID(43)
                .id(21)
                .build();

        recommendationRepository.saveAll(Arrays.asList(recommendation,recommendation1,recommendation2));
        recommendationRepository.flush();

        @SuppressWarnings("unchecked")
        List<Recommendation> body = restTemplate.getForEntity(baseUrl + 43, List.class).getBody();

        assertThat(body)
                .hasSize(2);
    }


    @Test
    public void saveNewRecommendation() throws Exception {


        long currentLength = recommendationRepository.count();
        Recommendation comment = Recommendation.builder()
                .comment("decent")
                .rate(4)
                .videoID(22)
                .build();

        String newComment = gson.toJson(comment);

        mockMvc.perform(MockMvcRequestBuilders.post("/recommendation/new-comment")
                .content(newComment)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(recommendationRepository.count()).isGreaterThan(currentLength);

    }
}