package Core.test;
import Core.src.ScreenManager;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;


public class TestScreenManager {
    private ScreenManager _screenManager;

    @Before
    public void init(){
        _screenManager = new ScreenManager();
    }

    @Test
    public void ScreenManagerCreation(){
        assertNotNull(_screenManager.getVideoCard());
    }

    @Test
    public void DisplayModeMatch(){
        DisplayMode dm1 = new DisplayMode(800,600,16, 10);
        DisplayMode dm2 = new DisplayMode(800,600,16,10);
        assertTrue(_screenManager.displayModesMatch(dm1,dm2));
    }

    @Test
    public void DMWidthNotMatch(){
        DisplayMode dm1 = new DisplayMode(800,600,16, 10);
        DisplayMode dm2 = new DisplayMode(700,600,16,10);
        assertFalse(_screenManager.displayModesMatch(dm1,dm2));
    }

    @Test
    public void DMHeightNotMatch(){
        DisplayMode dm1 = new DisplayMode(800,600,16, 10);
        DisplayMode dm2 = new DisplayMode(800,500,16,10);
        assertFalse(_screenManager.displayModesMatch(dm1,dm2));
    }

    @Test
    public void DMBitDepthNotMatch(){
        DisplayMode dm1 = new DisplayMode(800,600,16, 10);
        DisplayMode dm2 = new DisplayMode(800,600,10,10);
        assertFalse(_screenManager.displayModesMatch(dm1,dm2));
    }

    @Test
    public void DMRefreshRateNotMatch(){
        DisplayMode dm1 = new DisplayMode(800,600,16, 10);
        DisplayMode dm2 = new DisplayMode(800,600,16,1);
        assertFalse(_screenManager.displayModesMatch(dm1,dm2));
    }

    @Test
    public void InFullScreen(){
        GraphicsDevice g = _screenManager.getVideoCard();
        assertTrue(g.isFullScreenSupported());
    }

    @Test
    public void NotInFullScreen() {

    }
}
