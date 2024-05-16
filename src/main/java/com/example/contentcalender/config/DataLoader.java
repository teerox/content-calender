package com.example.contentcalender.config;

import com.example.contentcalender.model.Content;
import com.example.contentcalender.model.Status;
import com.example.contentcalender.model.Type;
import com.example.contentcalender.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;


//@Configuration
public class DataLoader {

    //@Bean
    CommandLineRunner commandLineRunner(ContentRepository repository) {
        return args -> {
            Content content = new Content(
                    null,
                    "Hello Chat GPT",
                    "Welcome to my first article on Chat GPT. I am excited to share my experience with you.",
                    Status.IDEA,
                    Type.ARTICLE,
                    LocalDateTime.now(),
                    null,
                    null
            );

            Content content2 = new Content(
                    null,
                    "Hello Chat Gemini",
                    "Welcome to my first article on Chat Gemini. I am excited to share my experience with you.",
                    Status.IDEA,
                    Type.ARTICLE,
                    LocalDateTime.now(),
                    null,
                    null
            );
                repository.save(content);
                repository.save(content2);
        };
    }
}
