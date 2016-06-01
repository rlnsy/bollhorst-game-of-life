package res;

import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import src.Game;

public class ImageReader
{
    public static final boolean USE_ALTERNATE_STYLE = true;
    
    public static Image readImage(String imagePath) {
        return new ImageReader().findImage(imagePath);
    }
    
    private Image findImage(String imagePath) {
        try {
           return ImageIO.read(getClass().getResource(imagePath));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }    
    }
    
    public static Image getEffectImage(String effectName, int width, int height) {
        String filePath = getEffectLocation() + effectName + "_" + width + "x" + height + ".png";
        return readImage(filePath);
    }
    
    public static String getImageLocation() {
        if(USE_ALTERNATE_STYLE)
            return "alternate_images/";
        else
            return "images/";
    }
    
    public static String getDefaultSpriteLocation() {
        return getImageLocation() + "element_sprites/";
    }
    
    public static String getThumbnailLocation() {
        return getImageLocation() + "toolbar_sprites/";
    }
    
    public static String getBackgroundLocation() {
        return getImageLocation() + "backgrounds/";
    }
    
    public static String getEffectLocation() {
        return getImageLocation() + "effect_sprites/";
    }
}
