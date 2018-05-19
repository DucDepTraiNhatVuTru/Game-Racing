package com.example.dell.racing.State;

import android.view.MotionEvent;

import com.example.dell.racing.Drawer;
import com.example.dell.racing.MainActivity;

public abstract class State {
    public void setExistingState(State state){
        MainActivity.gameView.setExistingState(state);
    }
    public void setPauseGame(){
        MainActivity.gameView.setPauseGame();
    }
    public void setResumeGame(){
        MainActivity.gameView.setResumeGame();
    }
    public abstract void init() ;
    public abstract void update(float d);
    public abstract void render(Drawer drawer);
    public abstract boolean onTouch(MotionEvent motionEvent, int touchX, int touchY);
}
