package com.sunny.Sunny.repository;

import com.sunny.Sunny.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "SELECT * FROM posts where title like %:title% ORDER BY title OFFSET (:_page*:_limit) ROWS FETCH NEXT :_limit ROWS ONLY",nativeQuery = true )
    List<Post> searchPostByTitle(@Param("title") String title, @Param("_page") Integer _page, @Param("_limit") Integer _limit);

    @Query(value = "Select count(title) from posts where title like %:title%", nativeQuery = true)
    Integer countRowByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM posts where description like %:description% ORDER BY description OFFSET (:_page*:_limit) ROWS FETCH NEXT :_limit ROWS ONLY", nativeQuery = true)
    List<Post> searchPostByDescription(@Param("description") String description, @Param("_page") Integer _page, @Param("_limit") Integer _limit);

    @Query(value = "Select count(description) from posts where description like %:description%", nativeQuery = true)
    Integer countRowByDescription(@Param("description") String description);
}
