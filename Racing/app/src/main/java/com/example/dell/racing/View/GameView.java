package com.example.dell.racing.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.dell.racing.Drawer;
import com.example.dell.racing.InputHandler;
import com.example.dell.racing.State.LoadState;
import com.example.dell.racing.State.PauseState;
import com.example.dell.racing.State.State;

public class GameView extends SurfaceView implements Runnable {

    private Bitmap gameImage;
    private Rect gameImageSource;
    private Rect gameImageDst;
    private Canvas canvas;
    private Drawer drawer;

    private InputHandler inputHandler;

    private Thread thread;
    private volatile State existingState;
    private volatile State previousState;
    private volatile boolean isRunning = false;

    public static final int FPS = 60;


    public GameView(Context context, int width, int height){
        super(context);
        gameImage = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        gameImageSource = new Rect(0,0,gameImage.getWidth(),gameImage.getHeight());
        gameImageDst = new Rect();
        canvas = new Canvas(gameImage);
        drawer = new Drawer(canvas);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                init();
                if(existingState==null){
                    setExistingState(new LoadState());
                }
                initGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    public void setExistingState(State state){
        System.gc();
        state.init();
        existingState = state;
        inputHandler.setExistingState(state);
    }

    private void init(){
        if(inputHandler==null){
            inputHandler = new InputHandler();
        }
        setOnTouchListener(inputHandler);
    }

    private void initGame(){
        isRunning=true;
        thread = new Thread(this,"GamePlay");
        thread.start();
    }

    private void renderGameImage(){
        Canvas screen = getHolder().lockCanvas();
        if(screen!=null){
            screen.getClipBounds(gameImageDst);
            screen.drawBitmap(gameImage,gameImageSource,gameImageDst,null);
            getHolder().unlockCanvasAndPost(screen);
        }
    }

    private void updateAndRender(float delta){
        //update
        existingState.update(delta/1000f);
        //render
        existingState.render(drawer);
        renderGameImage();
    }

    public GameView(Context context) {
        super(context);
    }

    @Override
    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        //Game loop
        while(isRunning) {

            long beforeUpdateRender = System.nanoTime();
            long deltaMillis = sleepDurationMillis + updateDurationMillis;
            updateAndRender(deltaMillis);

            updateDurationMillis = (System.nanoTime() - beforeUpdateRender) / 1000000L;
            sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);

            try {
                Thread.sleep(sleepDurationMillis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setPauseGame(){
        System.gc();
        PauseState pauseState = new PauseState();
        pauseState.init();
        previousState = existingState;
        existingState = pauseState;
        setExistingState(existingState);
    }

    public void setResumeGame(){
        if(previousState!=null){
            existingState=previousState;
            setExistingState(existingState);
        }
    }
}
