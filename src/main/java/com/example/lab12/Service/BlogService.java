package com.example.lab12.Service;

import com.example.lab12.Api.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.AuthRepository;
import com.example.lab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    //• Get All user blogs
    //get all blogs >> admin
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }

    //get user blogs

    public List <Blog> getMyBlogs(Integer id){
        User user = authRepository.findUserById(id);

        return blogRepository.findAllByUser(user);

    }

    //add
    public void addBlog(Integer auth_id , Blog blog){
        User user = authRepository.findUserById(auth_id);
        if(user == null){
            throw new ApiException("User not found");
        }

        blog.setUser(user);
        blogRepository.save(blog);
    }

    //update

    public void updateBlog(Integer auth_id , Integer blog_id,Blog blog){
        User user = authRepository.findUserById(auth_id);
        if(user == null){
            throw new ApiException("User not found");
        }

        Blog OldBlog = blogRepository.findBlogById(blog_id);
        if(OldBlog == null){
            throw new ApiException("Blog not found");
        }else if(OldBlog.getUser().getId() != auth_id){
            throw new ApiException("User not authorized");
        }
        OldBlog.setTitle(blog.getTitle());
        OldBlog.setBody(blog.getBody());
        blogRepository.save(OldBlog);
    }


    //delete
    public void deleteBlog(Integer auth_id , Integer blog_id){
        User user = authRepository.findUserById(auth_id);
        if(user == null){
            throw new ApiException("User not found");
        }
        Blog OldBlog = blogRepository.findBlogById(blog_id);
        if(OldBlog == null){
            throw new ApiException("Blog not found");
        }else if(OldBlog.getUser().getId() != auth_id){
            throw new ApiException("User not authorized");
        }
        blogRepository.delete(OldBlog);
    }

    //• Get blog by Id
    public Blog getBlogById (Integer auth_id , Integer blog_id){
        User user = authRepository.findUserById(auth_id);
        if(user == null){
            throw new ApiException("User not found");
        }
        Blog OldBlog = blogRepository.findBlogById(blog_id);
        if(OldBlog == null){
            throw new ApiException("Blog not found");
        }else if(OldBlog.getUser().getId() != auth_id){
            throw new ApiException("User not authorized");
        }
        return OldBlog;
    }


    //• Get blog by title
    public Blog getBlogByTitle( String title){
        Blog blogTitle = blogRepository.findBlogByTitle(title);
        if(blogTitle == null){
            throw new ApiException("Blog title not found");
        }
     return blogTitle;
    }







}
