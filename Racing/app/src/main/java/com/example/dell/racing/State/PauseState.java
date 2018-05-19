package com.example.dell.racing.State;

import android.Manifest;
import android.view.MotionEvent;

import com.example.dell.racing.Asset;
import com.example.dell.racing.Drawer;
import com.example.dell.racing.MainActivity;
import com.example.dell.racing.UI.GameButton;
import com.example.dell.racing.UI.GameText;

public class PauseState extends State {
    private GameButton btnResume;
    private GameButton btnRestart;
    private GameText txtPause;

    @Override
    public void init() {
        btnRestart = new GameButton((int)(MainActivity.width/2 - 100),(int)(MainActivity.height/2 -100),
                100,100, Asset.btnRestart);
        btnResume = new GameButton((int)(MainActivity.width/2),(int)(MainActivity.height/2 - 100),100,100,
                Asset.btnResume);
        txtPause = new GameText("PAUSE",MainActivity.width/2 - 10,MainActivity.height/2 - 180);
    }

    @Override
    public void update(float d) {

    }

    @Override
    public void render(Drawer drawer) {
        btnResume.render(drawer);
        btnRestart.render(drawer);
        txtPause.render(drawer);
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent, int touchX, int touchY) {
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            btnRestart.onTouchDown(touchX,touchY);
            btnResume.onTouchDown(touchX,touchY);
            if(btnResume.isPressed(touchX,touchY)){
                btnResume.cancel();
                setResumeGame();
            }else if(btnRestart.isPressed(touchX,touchY)){
                btnRestart.cancel();
                setExistingState(new PlayState());
            }
        }
        return false;
    }
}
