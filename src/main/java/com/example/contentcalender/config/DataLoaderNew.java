package com.example.contentcalender.config;

import com.example.contentcalender.model.Content;
import com.example.contentcalender.repository.ContentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

//@Profile("dev")
@Component
public class DataLoaderNew implements CommandLineRunner {

    private final ContentRepository contentRepository;
    private final ObjectMapper objectMapper;

    public DataLoaderNew(ContentRepository contentRepository,
                         ObjectMapper objectMapper) {
        this.contentRepository = contentRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
            List<Content> contents = objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){});
            contentRepository.saveAll(contents);
        }
    }
}
