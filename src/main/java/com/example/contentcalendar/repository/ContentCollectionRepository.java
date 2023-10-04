package com.example.contentcalendar.repository;

import com.example.contentcalendar.model.Content;
import com.example.contentcalendar.model.Status;
import com.example.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    //keep the data in memory:
    //initialize this arraylist:
    private final List<Content> contentList = new ArrayList<>();
    //constructor
    public ContentCollectionRepository() {
    }
    //add methods
    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    //如果新加的c.id = content.id,就remove佢.
    //然後再add in Backend.
    public void save(Content content) {
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    @PostConstruct //will call after above setting.
    private void init() {
        Content content = new Content(1,
                "This is the first test",
                "my descrption 000001",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "");

        contentList.add(content);
    }

    //硬揼一個功能,count() == 1,
    //有就.............
    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }

    //delete根據id
    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }
}
