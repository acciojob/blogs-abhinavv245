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

    public Image addImage(Integer blogId, String description, String dimensions) {
        Blog blog = blogRepository2.findById(blogId).get();
        //create image object
        Image image = new Image(blog, description, dimensions);
        blog.getImageList().add(image);
        //only saving the blog as image will be automatically saved due to bidirectional mapping
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id) {
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        int count = 0;

        String[] screen = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();
        String imageDimensions = image.getDimensions();
        String[] img = imageDimensions.split("X");


        int sLen=Integer.parseInt(screen[0]);
        int sBreadth=Integer.parseInt(screen[1]);
        int iLen=Integer.parseInt(img[0]);
        int iBreadth=Integer.parseInt(img[0]);

        count=(sLen/iLen)*(sBreadth/iBreadth);
        return count;
    }
}
