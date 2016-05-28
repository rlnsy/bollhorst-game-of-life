package src;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;

public class ImageReader
{
    public static Image readImage(String imagePath) {
        return new ImageReader().findImage(imagePath);
    }
    
    public Image findImage(String imagePath) {
        try {
           return ImageIO.read(getClass().getResource(imagePath));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
    }
}
