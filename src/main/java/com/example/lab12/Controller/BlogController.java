package com.example.lab12.Controller;

import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;



    @GetMapping("/get")
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/get-my")
    public ResponseEntity getMyBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user,@Valid @RequestBody Blog blog) {
        blogService.addBlog(user.getId(), blog);

            return ResponseEntity.status(200).body("Blog added successfully");
    }

    @PutMapping("/update/{auth_id}/{blog_id}")
    public ResponseEntity updateBlog(@PathVariable Integer auth_id,@PathVariable Integer blog_id,@Valid @RequestBody  Blog blog) {
        blogService.updateBlog(auth_id, blog_id,blog);
        return ResponseEntity.status(200).body("Blog updated successfully");
    }

    @DeleteMapping("/delete/{auth_id}/{blog_id}")
    public ResponseEntity deleteBlog(@PathVariable Integer auth_id,@PathVariable Integer blog_id) {
        blogService.deleteBlog(auth_id,blog_id);
        return ResponseEntity.status(200).body("Blog deleted successfully");
    }

    @GetMapping("/get-blog-byId/{auth_id}/{blog_id}")
    public ResponseEntity getTodoById(@PathVariable Integer auth_id,@PathVariable Integer blog_id) {
        return ResponseEntity.status(200).body(blogService.getBlogById(auth_id,blog_id));
    }

  
    @GetMapping("/get-blog-byT/{title}")
    public ResponseEntity getBlogByTitle( @PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }








}
