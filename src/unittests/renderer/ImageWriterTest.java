package unittests.renderer;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import renderer.ImageWriter;

public class ImageWriterTest {
    @Test
    public void GeneralTest(){
        ImageWriter iw = new ImageWriter("test.jpeg", 800, 500);
        for (int i = 0; i < 800; i++){
            for (int j = 0; j < 500; j++){
                if (i% 50 == 0 || j % 50 == 0){
                    iw.writePixel(i, j, new primitives.Color(Color.RED));
                }else{
                    iw.writePixel(i, j, new primitives.Color(Color.YELLOW));
                }
            }
        }
        iw.writeToImage();
    }
}
