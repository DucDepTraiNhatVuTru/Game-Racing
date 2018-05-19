package com.example.dell.racing.UI;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.dell.racing.Drawer;

public class GameButton {
    private Rect btnRect;
    private Boolean buttonDown;
    private Bitmap btnImage;

    public GameButton(int left, int top, int width, int height, Bitmap image){
        btnRect = new Rect(left, top, left+width, top+height);
        btnImage = image;
    }

    public void render(Drawer drawer){
        drawer.drawImage(btnImage,btnRect.left,btnRect.top,btnRect.width(), btnRect.height());
    }

    public Boolean isPressed(int touchX, int touchY){
        if(buttonDown&&btnRect.contains(touchX,touchY))return true;
        return false;
    }

    public void onTouchDown(int touchX, int touchY){
        if(btnRect.contains(touchX,touchY))buttonDown=true;
        else buttonDown=false;
    }

    public void cancel(){
        buttonDown=false;
    }
}
