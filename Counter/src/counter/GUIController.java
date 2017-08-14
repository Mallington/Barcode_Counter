/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import org.opencv.core.Core;

/**
 *
 * @author Mathew
 */
public class GUIController implements Initializable {
    //Video and Processing
    ImageAnalyser IA;
    int CAM_VIEW = 0;
    FrameUpdater UPDATER;
    double FPS = 30;
    
    // GUI Objects
    @FXML
    private ListView DATA_OUT;
    @FXML
    private ImageView VIDEO_VIEW;
    
    //Settings
    @FXML
    public Slider CANNY_THRESH;
    @FXML
    public Slider BLUR_VALUE;
    @FXML
    public CheckBox PAUSE_CHECK;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        IA = new ImageAnalyser(CAM_VIEW);
        Runnable r = ()-> {IA.processAndCapture(VIDEO_VIEW,this);};
        UPDATER = new FrameUpdater(VIDEO_VIEW, FPS, IA, r);
        UPDATER.start();
        System.out.println("GUI and Webcam Up");
    }    
    
}
