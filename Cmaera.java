import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class form {

    Webcam webcam = Webcam.getDefault();
    webcam.open();
    BufferedImage image = webcam.getImage();
        try {
            ImageIO.write(image, ImageUtils.FORMAT_JPG, new File("selfie.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
        }
    WebcamUtils.capture(webcam, "selfie.jpg");
    BufferedImage img1 = null;
        try {
            img1 = ImageIO.read(new File("selfie.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
        }
      BufferedImage img2 = null;
        try {
            img2 = ImageIO.read(new File("selfie1.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(form.class.getName()).log(Level.SEVERE, null, ex);
        }
      int w1 = img1.getWidth();
      int w2 = img2.getWidth();
      int h1 = img1.getHeight();
      int h2 = img2.getHeight();
      if ((w1!=w2)||(h1!=h2)) {
         System.out.println("Both images should have same dimensions");
      } else {
         long diff = 0;
         for (int j = 0; j < h1; j++) {
            for (int i = 0; i < w1; i++) {
               //Getting the RGB values of a pixel
               int pixel1 = img1.getRGB(i, j);
               Color color1 = new Color(pixel1, true);
               int r1 = color1.getRed();
               int g1 = color1.getGreen();
               int b1 = color1.getBlue();
               int pixel2 = img2.getRGB(i, j);
               Color color2 = new Color(pixel2, true);
               int r2 = color2.getRed();
               int g2 = color2.getGreen();
               int b2= color2.getBlue();
               //sum of differences of RGB values of the two images
               long data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
               diff = diff+data;
            }
         }
         double avg = diff/(w1*h1*3);
         double percentage = (avg/255)*100;
         System.out.println("Difference: "+percentage);
      }
    webcam.close();
}
