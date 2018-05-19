package com.example.dell.racing.DataModel;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.example.dell.racing.Asset;
import com.example.dell.racing.Drawer;
import com.example.dell.racing.MainActivity;
import com.example.dell.racing.RandomOpstacle;
import com.example.dell.racing.View.GameView;

public class Opstacle {
    private float x, y;
    private int width, height;
    private int speed = 850;
    private Bitmap opstacle;
    private boolean passed;
    private int index;
    private Rect rect;

    public Opstacle (int width, int height){
        this.width = width;
        this.height = height;
        opstacle = Asset.opstacle;
        this.y=0;
        this.x = RandomOpstacle.getRandIntBetween(0,480);
        this.rect = new Rect((int)x, (int)y, (int)x + width - 15, (int)y + height-15);
        passed =false;
    }

    public void update(){
        y+=speed/ GameView.FPS;
        if(y>720){
            this.x = RandomOpstacle.getRandIntBetween(0,480);
            this.y = 0;
            passed = false;
        }
        updateRect();
    }
    public void updateRect() {
        this.rect = new Rect((int)x, (int)y, (int)x + width-15, (int)y + height-15);
    }
    public void render(Drawer drawer) {
        drawer.drawImage(opstacle, (int)x, (int)y, width, height);
    }

    public Rect getRect() {
        return rect;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
