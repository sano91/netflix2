package com.codecool.video.controller;


import com.codecool.video.entity.Video;
import com.codecool.video.repository.VideoRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/all-videos-name-and-id")
    public Map<String, Integer> init(){
        List<Video> allVideos = videoRepository.findAll();
        Map<String, Integer> nameID = new HashMap<>();
        allVideos.forEach(video -> nameID.put(video.getName(), video.getId()));
        return nameID;
    }


}
