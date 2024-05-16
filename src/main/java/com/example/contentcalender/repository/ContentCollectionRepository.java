package com.example.contentcalender.repository;

import com.example.contentcalender.model.Content;
import com.example.contentcalender.model.Status;
import com.example.contentcalender.model.Type;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> content = new ArrayList<>();

    ContentCollectionRepository() {
        init();
    }

    public List<Content> findAll() {
        return content;
    }

    public Optional<Content> findById(Integer id) {
        return content
                .stream()
                .filter(c -> c.id()
                        .equals(id))
                .findFirst();
    }

    public void init() {
        // Create a new object of the Content record with specified values
        Content contents = new Content(
                1,                      // id
                "Sample Title",         // title
                "Sample Description",   // desc
                Status.COMPLETED,         // status
                Type.ARTICLE,           // contentType
                LocalDateTime.now(),   // dateCreated
                null,   // dateUpdated
                "https://example.com"  // url
        );
        content.add(contents);
    }

    public void save(Content singleContent) {
        content.removeIf(c -> c.id().equals(singleContent.id()));
        content.add(singleContent);
    }

    public boolean exist(Integer id) {
        return content.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    public void delete(Integer id) {
        content.removeIf(c -> c.id().equals(id));
    }
}
