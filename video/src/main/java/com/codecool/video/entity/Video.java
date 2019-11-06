package com.codecool.video.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(generator = "video_generator")
    @SequenceGenerator(
            name = "video_generator",
            sequenceName = "video_sequence",
            initialValue = 10
    )
    @Column(unique = true)
    private int id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;


    @Column(columnDefinition = "text")
    @NotBlank
    private String url;


}
