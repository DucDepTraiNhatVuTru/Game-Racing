package com.example.dell.racing.UI;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.dell.racing.Asset;
import com.example.dell.racing.Drawer;

public class GameText {
    private String text;
    private int size = 80;
    private Paint paint;
    private float x;
    private float y;

    public GameText(String text, float x, float y){
        this.x = x;
        this.y = y;
        this.text = text;
        paint = new Paint();
    }

    public void render(Drawer drawer){
        paint.setTypeface(Asset.typeface);
        paint.setTextSize(size);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        drawer.getCanvas().drawText(text,x,y,paint);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
