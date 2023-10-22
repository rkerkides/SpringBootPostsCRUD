package com.renoskerkides.posts.service;

import com.renoskerkides.posts.repository.PostRepository;
import com.renoskerkides.posts.entity.Post;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// The @Transactional annotation ensures that the methods in this class
// are executed within a transaction context, which is a series of operations
// performed as a single unit of work. If any operation within the transaction fails,
// all operations are rolled back to ensure data consistency.
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findByID(Long id) {
        return postRepository.findById(id);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
