/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Mathew
 */
public class ImageAnalyser {

    public int CAM_ID; // Camera source
    VideoCapture INPUT; //Frame Capturer

    Mat CURRENT_FRAME;

    double SURFACE_BLUR = 1.0;
    double CANNY_THRESHOLD = 10.0;
    boolean PAUSED = false;
    public ImageAnalyser(int camID) {
        INPUT = new VideoCapture((CAM_ID = camID));
        CURRENT_FRAME = new Mat();
    }

    public void processAndCapture(ImageView img, GUIController g) {
        if (INPUT.isOpened()) {
            updateSettings(g);
            if(!PAUSED){
            captureNewFrame();
            processFrame();
            setImageView(img);
            }
        }
    }

    public void updateSettings(GUIController g) {
        SURFACE_BLUR = g.BLUR_VALUE.getValue();
        CANNY_THRESHOLD = g.CANNY_THRESH.getValue();
        PAUSED = g.PAUSE_CHECK.isSelected();
        System.out.println("t: "+CANNY_THRESHOLD+", b:"+SURFACE_BLUR);
    }

    public void captureNewFrame() {
        try {
            CURRENT_FRAME = new Mat();
            INPUT.read(CURRENT_FRAME);
        } catch (Exception e) {
            System.out.println("Could not grab frame!");
        }
    }

    public void processFrame() {
        //CURRENT_FRAME = Processing.drawCanny(CURRENT_FRAME, CANNY_THRESHOLD, SURFACE_BLUR);
    }

    public void setImageView(ImageView img) {
        final Mat toRender = CURRENT_FRAME;
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    img.setImage(Processing.renderFrame(toRender));
                } catch (Exception e) {
                    System.out.println("Render Failed");
                }
            }
        });

    }

}
