package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        Blog blog=blogRepository2.findById(blogId).get();
        //create image object
        Image image= new Image(blog,description,dimensions);
       blog.getImageList().add(image);
       //only saving the blog as image will be automatically saved due to bidirectional mapping
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
       imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
       Image image= imageRepository2.findById(id).get();
       String imageDimension= image.getDimensions();
       Integer imgArea= ((int) imageDimension.charAt(0)-'0') * ((int) imageDimension.charAt(2)-'0');
       Integer screenArea= ((int) screenDimensions.charAt(0)-'0') * ((int) screenDimensions.charAt(2)-'0');

       int count=0;
       if(imgArea>0) count=screenArea/imgArea;
       else count=0;
       return count;
    }
}
