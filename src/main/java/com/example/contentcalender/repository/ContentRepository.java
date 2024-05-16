package com.example.contentcalender.repository;

import com.example.contentcalender.model.Content;
import com.example.contentcalender.model.Status;
import com.example.contentcalender.model.Type;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {


    @Query(""" 
            select * from Content
            where content_type = :type 
            """)
    List<Content> listByType(@Param("type") Type type);

    @Query("""
            select * from Content 
            where status = :status
            """)
    List<Content> listByStatus(@Param("status") Status status);

    List<Content> findAllByTitleContains(String keyword);
}
