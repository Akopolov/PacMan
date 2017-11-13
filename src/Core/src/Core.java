package Core.src;

import java.awt.*;

public abstract class Core {

    protected ScreenManager _screenManager;
    private boolean _programRunning;
    private DisplayMode[] _displayModeList = {
            new DisplayMode(800,600,32,0),
            new DisplayMode(800,600,24,0),
            new DisplayMode(800,600,16,0)
    };

    //stop the hole program
    public void stopProgram(){
        _programRunning = false;
    }

    public void run(){
        try{
            init();
            gameLoop();
        }finally {
            _screenManager.restoreScreen();
        }
    }

    public void init() {
        _screenManager = new ScreenManager();
        DisplayMode displayMode = _screenManager.getFirstDisplayMode(_displayModeList);
        _screenManager.enableFullScreen();
        _screenManager.setDisplayMode(displayMode);
        _programRunning = true;
    }

    public void gameLoop(){
        long startTime = System.currentTimeMillis();
        long accumulativeTime = startTime;
        while (_programRunning){
            long timePassed = System.currentTimeMillis() - accumulativeTime;
            accumulativeTime += timePassed;
            update(timePassed);
            Graphics2D graphics = _screenManager.getGraphics();
            draw(graphics);
            graphics.dispose();
            _screenManager.updateView();
        }
    }

    public void update(long timePassed){}

    public void draw(Graphics2D graphics){}
}
