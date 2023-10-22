package com.renoskerkides.posts.controller;


import com.renoskerkides.posts.entity.Post;
import com.renoskerkides.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
// All the mappings inside this class will start with /posts in their URL.
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);  // Adding the list of posts to the model to be accessible by the view template.
        // Returning the view name "post/list" which maps to the post/list.html view template.
        // This template will use the posts data to render the HTML.
        return "post/list";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.findByID(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + id));  // Fetching the post by id or throwing an exception if not found.
        model.addAttribute("post", post);  // Adding the post to the model to be accessible by the view template.
        // Returning the view name "post/view" which maps to the post/view.html view template.
        // This template will use the post data to render the HTML.
        return "post/view";
    }

    @PostMapping
    public String createPost(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        Post existingPost = postService.findByID(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + id));
        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());
        existingPost.setAuthor(post.getAuthor());
        postService.save(existingPost);
        return "redirect:/posts";
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deleteById(id);
        return "redirect:/posts";
    }
}
