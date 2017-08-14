/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter;

import java.io.ByteArrayInputStream;
import javafx.scene.image.Image;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Mathew
 */
public class Processing {
    
    public static Mat drawCanny(Mat frame,double threshold, double blur){
        Mat grayImage = new Mat();
        Mat detectedEdges = new Mat();
        Imgproc.cvtColor(frame, grayImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(grayImage, detectedEdges, new Size(blur, blur));
        
        Imgproc.Canny(detectedEdges, detectedEdges, threshold, threshold * 3, 3, false);
        return detectedEdges;
    }
    
    
    public static Image renderFrame(Mat frame){
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        Image img = new Image(new ByteArrayInputStream(buffer.toArray()));
        return img;
    }
}
