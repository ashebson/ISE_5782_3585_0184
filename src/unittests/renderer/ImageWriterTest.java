package unittests.renderer;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import renderer.ImageWriter;

public class ImageWriterTest {
    /**
     * Test method for {@link renderer.ImageWriter#writeToImage()}.
     */
    @Test
    public void writeImageTest(){
        ImageWriter imageWriter = new ImageWriter("test", 800, 500);
        for (int i = 0; i < 800; i++){
            for (int j = 0; j < 500; j++){
                if (i% 50 == 0 || j % 50 == 0){
                    imageWriter.writePixel(i, j, new primitives.Color(Color.RED));
                }else{
                    imageWriter.writePixel(i, j, new primitives.Color(Color.BLUE));
                }
            }
        }
        imageWriter.writeToImage();
    }
}
