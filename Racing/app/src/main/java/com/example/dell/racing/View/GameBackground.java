package com.example.dell.racing.View;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.example.dell.racing.Asset;

public class GameBackground {
    private Bitmap background;
    private Bitmap backgroundReversed;
    private int width;
    private int height;
    private boolean reversedFirst;
    public static final float speed = 850;
    private int yClip;

    public GameBackground(int width, int height) {
        background = Asset.gameBackground;
        reversedFirst = false;

        yClip = 0;
        background = Bitmap.createScaledBitmap(background, width, height, true);

        this.width = background.getWidth();
        this.height = background.getHeight();

        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        backgroundReversed = Bitmap.createBitmap(background, 0, 0, width, height, matrix, true);
    }

    public void update() {
        yClip += speed / GameView.FPS;

        if (yClip > height) {
            yClip = 0;
            reversedFirst = !reversedFirst;
        } else if (yClip < 0) {
            yClip = height;
            reversedFirst = !reversedFirst;
        }
        /*xClip -= speed / GameView.FPS;

        if (xClip >= width) {
            xClip = 0;
            reversedFirst = !reversedFirst;
        } else if (xClip <= 0) {
            xClip = width;
            reversedFirst = !reversedFirst;
        }*/
    }

    public Bitmap getBackground() {
        return background;
    }

    public Bitmap getBackgroundReversed() {
        return backgroundReversed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isReversedFirst() {
        return reversedFirst;
    }

    public int getyClip() {
        return yClip;
    }

}
