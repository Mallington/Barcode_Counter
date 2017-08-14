/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.scene.image.ImageView;

/**
 *
 * @author Mathew
 */
public class FrameUpdater {
    int DELAY;
    
    ImageView VIEW;
    ImageAnalyser IA;
    
    ScheduledExecutorService TIMER;
    Runnable PROCESS;
    public FrameUpdater(ImageView imgV, double FPS, ImageAnalyser iA, Runnable r){
        DELAY = (int)(1000.0/FPS);
        VIEW = imgV;
        IA = iA;
        PROCESS = r;
        
        TIMER = Executors.newSingleThreadScheduledExecutor(); 
    }
    
    public void start(){
        TIMER.scheduleAtFixedRate(PROCESS, 0, DELAY, TimeUnit.MILLISECONDS);
    }
    
    
    
    
    
}
