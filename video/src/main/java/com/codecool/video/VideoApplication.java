package com.codecool.video;

import com.codecool.video.entity.Video;
import com.codecool.video.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;


@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
@Slf4j
public class VideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoApplication.class, args);
	}


	@Autowired
	private VideoRepository videoRepository;

	@Bean
	public CommandLineRunner init(){
		return args -> {

			Video video1 = Video.builder()
					.url("https://www.youtube.com/watch?v=B-5R8DCPNQ0&list=PL6PFaB5ciOjIR_5hINxqFP6gQobgcWnOm&index=1")
					.name("Memories")
					.build();

			Video video2 = Video.builder()
					.url("https://www.youtube.com/watch?v=LG3NrZoH3zw&list=PL6PFaB5ciOjIR_5hINxqFP6gQobgcWnOm&index=14")
					.name("Cashmere")
					.build();

			Video video3 = Video.builder()
					.url("https://www.youtube.com/watch?v=Xpi9MdARIdw&list=PL6PFaB5ciOjIR_5hINxqFP6gQobgcWnOm&index=12")
					.name("C166W")
					.build();

			Video video4 = Video.builder()
					.url("https://www.youtube.com/watch?v=5XKQ8xNqMp0")
					.name("Let There Be Dark")
					.build();

			videoRepository.saveAll(Arrays.asList(video1,video2, video3, video4));
			videoRepository.flush();
			Video vidiFromRepo = videoRepository.findAll().get(1);

			log.info("video from repo : " + vidiFromRepo.getName());

		};
	}
}

