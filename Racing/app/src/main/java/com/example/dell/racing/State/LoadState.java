package com.example.dell.racing.State;

import android.view.MotionEvent;

import com.example.dell.racing.Asset;
import com.example.dell.racing.Drawer;

public class LoadState extends State {
    @Override
    public void init() {
        Asset.load();
        setExistingState(new MenuState());
    }

    @Override
    public void update(float d) {
        setExistingState(new MenuState());
    }

    @Override
    public void render(Drawer drawer) {
        drawer.drawImage(Asset.gameLoading,0,0,480,720);
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent, int touchX, int touchY) {

        return false;
    }


}
