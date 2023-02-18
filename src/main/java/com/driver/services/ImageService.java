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
        Image image= new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        Blog blog=blogRepository2.findById(blogId).get();
        List<Image> imageList=blog.getImageList();
        imageList.add(image);
        blog.setImageList(imageList);
        imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Integer id){
        Image image= imageRepository2.findById(id).get();
        Blog blog= image.getBlog();
        List<Image> imageList=blog.getImageList();
        imageList.remove(image);
        blog.setImageList(imageList);
        imageRepository2.delete(image);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
       Image image= imageRepository2.findById(id).get();
       String imageDimension= image.getDimensions();
       Integer imgArea= ((int) imageDimension.charAt(0)-'0') * ((int) imageDimension.charAt(2)-'0');
       Integer screenArea= ((int) screenDimensions.charAt(0)-'0') * ((int) screenDimensions.charAt(2)-'0');
       int count=screenArea/imgArea;
       return count;
    }
}
