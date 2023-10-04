package com.example.contentcalendar.config;

import com.example.contentcalendar.model.Content;
import com.example.contentcalendar.repository.ContentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

//如果設定為只可以run in dev:@Profile("dev"),
//如果設定為只可以run in production:@Profile("production"),
//如果設定為不可以run in dev:@Profile("!dev"),
//@Profile("!dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final ContentRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        //read from content.json
        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
            //save a list
            //reading the JSON each of them
            repository.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<Content>>(){}));
        }
//        System.out.println("Hello handwaving22222");
    }
}


