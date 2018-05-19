package com.example.dell.racing.DataModel;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.dell.racing.Asset;
import com.example.dell.racing.Drawer;

public class MyCar {
    private float x, y;
    private int width, height;
    private Rect rect;
    private float yGround;
    private Bitmap imageCar;
    int countLeft;
    Drawer drawer;


    public MyCar(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        rect = new Rect((int) x, (int) y, (int) (x + width), (int) (y + height));
        yGround = y + height;

        imageCar = Asset.car;
        countLeft = 1;
    }

    public void update(float delta){
       updateRect();
    }

    public void render(Drawer drawer){
        drawer.drawImage(imageCar,(int)x, (int)y, width, height);
        this.drawer = drawer;
    }

    private void changeLane(int touchX){
        //if(isGrounded())
        {
            if(touchX<240){
                if(countLeft>0) {
                    countLeft--;
                    x -= 75;
                }
            }
            else if(touchX>240){
                if(countLeft<3){
                x+=75;
                countLeft++;
                }
            }
        }
    }

    public void onTouch(MotionEvent motionEvent, int touchX, int touchY){
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            changeLane(touchX);
        }
    }

    public Boolean isGrounded(){
        if (rect.top + rect.height() >= yGround) {
            return true;
        }
        return false;
    }

    public void updateRect() {
        rect.set((int) x, (int) y, (int) x + width, (int) y + height);
    }

    public Rect getRect() {
        return rect;
    }
}
