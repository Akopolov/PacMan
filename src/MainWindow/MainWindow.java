package MainWindow;

import Core.src.Core;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow
        extends Core
        implements KeyListener{

    private String _rezolution;
    private int _numberOfDisplayModes;
    private int _currentNumberDisplayMode;

    public static void main(String[] args) {
        new MainWindow().run();
    }

    public void init(){
        super.init();
        Window window = _screenManager.getFullScreenWindow();
        _numberOfDisplayModes = _screenManager.getCompatibleDisplayModes().length;
        _currentNumberDisplayMode = 0;
        screenSize();
        window.addKeyListener(this);
    }

    public synchronized void draw(Graphics2D graphics){
        Window window = _screenManager.getFullScreenWindow();
        graphics.setFont(new Font("Arial", Font.PLAIN, 20));
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, window.getWidth(), window.getHeight());
        graphics.setColor(Color.BLACK);
        graphics.drawString(_rezolution, 30, 30);
    }

    public synchronized void screenSize(){
        DisplayMode currentDisplayMode = _screenManager.getCurrentDisplayMode();
        _rezolution = "Size: " + currentDisplayMode.getWidth() + " X " + currentDisplayMode.getHeight();
        _rezolution += " Bit: " + currentDisplayMode.getBitDepth();
        _rezolution += " Refresh: " + currentDisplayMode.getRefreshRate();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE){
            super.stopProgram();
        }

        //checking how to resize the window
        if(keyCode == KeyEvent.VK_UP){
            while(_screenManager.displayModesMatch(
                    _screenManager.getCurrentDisplayMode(),
                    _screenManager.getCompatibleDisplayModes()[_currentNumberDisplayMode])
                    ){
                if(_currentNumberDisplayMode + 1 > _numberOfDisplayModes){
                    _currentNumberDisplayMode = 0;
                }else {
                    _currentNumberDisplayMode ++;
                }
            }
            _screenManager.setDisplayMode(_screenManager.getCompatibleDisplayModes()[_currentNumberDisplayMode]);
            screenSize();
        }

        if(keyCode == KeyEvent.VK_DOWN){
            _screenManager.disableFullScreen();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
