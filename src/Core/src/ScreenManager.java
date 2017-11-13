package Core.src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ScreenManager {
    private GraphicsDevice _videoCard;
    private JFrame _frame;

    //give _videoCard access to monitor screen
    public ScreenManager(){
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        _videoCard = environment.getDefaultScreenDevice();
        _frame = new JFrame();
        _frame.setUndecorated(true);
        _frame.setIgnoreRepaint(true);
    }

    //return the current used video card
    public GraphicsDevice getVideoCard(){
        return _videoCard;
    }

    //get all compatible display mode
    public DisplayMode[] getCompatibleDisplayModes(){
        return _videoCard.getDisplayModes();
    }

    public DisplayMode getCurrentDisplayMode(){
        return _videoCard.getDisplayMode();
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

    //find and return the 1st sutable display mode
    public DisplayMode getFirstDisplayMode(DisplayMode[] displayModeList){
        DisplayMode[] videoCardDisplayModesList = _videoCard.getDisplayModes();
        for(DisplayMode d1 : displayModeList){
            for(DisplayMode d2 : videoCardDisplayModesList){
                if(displayModesMatch(d1,d2)){
                    return d2;
                }
            }
        }
        return null;
    }

    //Make frame full screen
    public void enableFullScreen(){
        if(_videoCard.getFullScreenWindow() != null){
            _videoCard.getFullScreenWindow().dispose();
        }
        _frame.setResizable(false);
        _videoCard.setFullScreenWindow(_frame);
        _frame.createBufferStrategy(2);
    }

    //disable full screen
    public void disableFullScreen(){
        if(_videoCard.getFullScreenWindow() != null){
            _videoCard.getFullScreenWindow().dispose();
        }
        _frame.setVisible(true);
        _frame.setResizable(true);
        _videoCard.setFullScreenWindow(null);
        _frame.createBufferStrategy(2);
    }

    //close screen
    public void restoreScreen(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            window.dispose();
        }
        _videoCard.setFullScreenWindow(null);
    }

    //set resolution
    public void setDisplayMode(DisplayMode displayMode){
        if(displayMode != null && _videoCard.isDisplayChangeSupported()){
            try{
                _videoCard.setDisplayMode(displayMode);
            }catch (Exception ex){}
        }
    }

    //get current window
    public Window getFullScreenWindow() {
        return _videoCard.getFullScreenWindow();
    }

    //get graphics
    public Graphics2D getGraphics(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            BufferStrategy bufferStrategy = window.getBufferStrategy();
            return (Graphics2D) bufferStrategy.getDrawGraphics();
        }else {
            return null;
        }
    }

    //update graphics
    public void updateView(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            BufferStrategy bufferStrategy = window.getBufferStrategy();
            if(!bufferStrategy.contentsLost()){
                bufferStrategy.show();
            }
        }
    }

    //get window width
    public int getWidth(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            return window.getWidth();
        }else {
            return 0;
        }
    }

    //get window height
    public int getHeight(){
        Window window = _videoCard.getFullScreenWindow();
        if(window != null){
            return window.getHeight();
        }else {
            return 0;
        }
    }
}
