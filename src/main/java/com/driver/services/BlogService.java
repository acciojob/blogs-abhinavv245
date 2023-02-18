package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content) {
        //get user
        User user= userRepository1.findById(userId).get();
        //create blog object
        Blog blog= new Blog(user,title,content);
        blog.setPubDate(new Date());
        //add blog to the user
        user.getBlogList().add(blog);
        //save user
        //blog will be automatically saved due to bidirectional mapping
        userRepository1.save(user);
        return blog;
    }

    public void deleteBlog(int blogId){
       blogRepository1.deleteById(blogId);
    }
}
