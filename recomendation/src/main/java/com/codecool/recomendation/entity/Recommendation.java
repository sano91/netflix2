package com.codecool.recomendation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "recommendation")
@Service
public class Recommendation {

    @Id
    @GeneratedValue(generator = "video_generator")
    @SequenceGenerator(
            name = "video_generator",
            sequenceName = "video_sequence",
            initialValue = 1000
    )
    @Column(unique = true)
    private int id;

    @Column(columnDefinition = "int")
    private int rate;

    @Column(columnDefinition = "text")
    private String comment;

    @Column(columnDefinition = "int")
    private int videoID;

}
