package com.example.contentcalendar.repository;

import com.example.contentcalendar.model.Content;
import com.example.contentcalendar.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//type:Content,
//type:Content, id:Integer
//加入findAllByTitleContains(String keyword)的設定;
//可以再加入其他設定:listByStatus(Status status);
//..........................

public interface ContentRepository extends ListCrudRepository<Content,Integer> {
    List<Content> findAllByTitleContains(String keyword);

    @Query("""
        SELECT * FROM Content
        where status = :status
""")
    List<Content> listByStatus(@Param("status") Status status);
}

