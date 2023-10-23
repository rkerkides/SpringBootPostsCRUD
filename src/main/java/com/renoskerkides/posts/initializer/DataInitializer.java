package com.renoskerkides.posts.initializer;

import com.renoskerkides.posts.entity.Post;
import com.renoskerkides.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private PostService postService;

    @PostConstruct
    public void initData() {
        // Create and save the posts
        Post post1 = new Post();
        post1.setTitle("Sample Post 1");
        post1.setContent("This is the content of sample post 1.");
        post1.setAuthor("Renos Kerkides");
        postService.save(post1);

        Post post2 = new Post();
        post2.setTitle("Sample Post 2");
        post2.setContent("This is the content of sample post 2.");
        post2.setAuthor("John Doe");
        postService.save(post2);

    }
}
