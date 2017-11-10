package Core.src;

public abstract class Core {

    private boolean _programRunning;
    public ScreenManager _screenManager;

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

    }

    public void gameLoop(){

    }

}
