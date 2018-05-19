package com.example.dell.racing;

import android.view.MotionEvent;
import android.view.View;

import com.example.dell.racing.State.State;

public class InputHandler implements View.OnTouchListener {
    private State existingState;

    public void setExistingState(State existingState){
        this.existingState = existingState;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int) ((event.getX() / v.getWidth()) * MainActivity.width);
        int scaledY = (int) ((event.getY() / v.getHeight()) * MainActivity.height);
        return existingState.onTouch(event,scaledX,scaledY);
    }
}
