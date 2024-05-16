package com.example.contentcalender.controller;

import com.example.contentcalender.model.Content;
import com.example.contentcalender.model.Status;
import com.example.contentcalender.model.Type;
import com.example.contentcalender.repository.ContentRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/content")
@CrossOrigin // to work with front end
public class ContentController {

    private final ContentRepository contentRepository;

    ContentController(ContentRepository contentRepository) {

        this.contentRepository = contentRepository;
    }

    @GetMapping
   public List<Content> findAll() {
        return contentRepository.findAll();
   }

   @GetMapping("/{id}")
   public Content findById(@PathVariable Integer id) {
        return contentRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
   }

   @ResponseStatus(HttpStatus.CREATED)
   @PostMapping("")
   public void create(@Valid @RequestBody Content content) {
        contentRepository.save(content);
   }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id) {
        if (!contentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        } else {
            contentRepository.save(content);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (!contentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        } else {
            contentRepository.deleteById(id);
        }
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> listByStatus(@PathVariable Status status){
        return contentRepository.listByStatus(status);
    }


    @GetMapping("/filter/{keyword}")
    public List<Content> findByTitle(@PathVariable String keyword) {
        return contentRepository.findAllByTitleContains(keyword);
    }

    @GetMapping("/filter/type/{type}")
    public List<Content> findByType(@PathVariable Type type) {
        return contentRepository.listByType(type);
    }
}
