package Core.src;

import javax.swing.*;
import java.awt.*;

public class ScreenManager {
    private GraphicsDevice _videoCard;

    //give _videoCard access to monitor screen
    public ScreenManager(){
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        _videoCard = environment.getDefaultScreenDevice();
    }

    //return the current used video card
    public GraphicsDevice getVideoCard(){
        return _videoCard;
    }

    //get all compatible display mode
    public DisplayMode[] getCompatibleDisplayModes(){
        return _videoCard.getDisplayModes();
    }

    public boolean displayModesMatch(DisplayMode dm1, DisplayMode dm2){

        //check size
        if(dm1.getWidth() != dm2.getWidth() || dm1.getHeight() != dm2.getHeight()){
            return false;
        }

        //check BitDepth
        if(dm1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                dm2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                dm1.getBitDepth() != dm2.getBitDepth()){
            return false;
        }

        //check RefreshRate
        if(dm1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
                dm2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
                dm1.getRefreshRate() != dm2.getRefreshRate()){
            return false;
        }

        return true;
    }

    //Make frame full screen TODO check if there is a frame the set to full
    public void setFullScreen(DisplayMode displayMode){
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        _videoCard.setFullScreenWindow(frame);

        if(displayMode != null && _videoCard.isDisplayChangeSupported()){
            try{
                _videoCard.setDisplayMode(displayMode);
            }catch (Exception ex){}
        }

        frame.createBufferStrategy(2);
    }

    //TODO check if there is a frame then set to not full
    public void setNotFullScreen(DisplayMode displayMode){

    }

    public Window getFullScreenWindow() {
        return _videoCard.getFullScreenWindow();
    }

    //get out of the fullscreen
    public void restoreScreen(){

    }
}
