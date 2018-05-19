package com.example.dell.racing.State;

import android.view.MotionEvent;

import com.example.dell.racing.Asset;
import com.example.dell.racing.Drawer;
import com.example.dell.racing.UI.GameButton;
import com.example.dell.racing.MainActivity;

public class MenuState extends State {
    private GameButton btnPlay;
    private GameButton btnHighScore;

    @Override
    public void init() {
        btnPlay = new GameButton(MainActivity.width/2-50,350, 128,128, Asset.btnPlay);
        btnHighScore = new GameButton(MainActivity.width/2-50,350+128+20,128,128/3, Asset.btnHightScore);
    }


    @Override
    public void update(float d) {

    }

    @Override
    public void render(Drawer drawer) {
        drawer.drawImage(Asset.gameBackground,0,0, MainActivity.width,MainActivity.height);
        btnPlay.render(drawer);
        btnHighScore.render(drawer);
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent, int touchX, int touchY) {
        if(motionEvent.getAction() ==MotionEvent.ACTION_DOWN){
            btnPlay.onTouchDown(touchX,touchY);
            btnHighScore.onTouchDown(touchX,touchY);
        }

        if(motionEvent.getAction()==MotionEvent.ACTION_UP){
            if(btnPlay.isPressed(touchX,touchY)){
                btnPlay.cancel();
                //chuyển sang PlayState
                setExistingState(new PlayState());
            }
            if(btnHighScore.isPressed(touchX,touchY)){
                btnHighScore.cancel();
                setExistingState(new HighScoreState());
            }
        }
        return true;
    }
}
