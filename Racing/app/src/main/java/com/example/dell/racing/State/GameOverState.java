package com.example.dell.racing.State;

import android.view.MotionEvent;

import com.example.dell.racing.Asset;
import com.example.dell.racing.DoAPI.DoGetScore;
import com.example.dell.racing.DoAPI.DoPostScore;
import com.example.dell.racing.Drawer;
import com.example.dell.racing.Instance;
import com.example.dell.racing.MainActivity;
import com.example.dell.racing.UI.GameButton;
import com.example.dell.racing.UI.GameText;

public class GameOverState extends State {

    private int score;
    private GameButton btnRestart;
    private GameButton btnMenu;
    private GameText txtScore;
    private GameText txtBestScore;
    private GameText txtGameOver;

    public GameOverState(int score) {
        this.score = score;
        new DoPostScore().execute("http://192.168.42.107:10101/api/Scores", Instance.user.getEmail(), String.valueOf(score));
        new DoGetScore().execute("http://192.168.42.107:10101/api/Scores?top=10");
    }

    @Override
    public void init() {

        txtScore = new GameText("     Score : " + score, MainActivity.width / 2 - 5, MainActivity.height / 2 - 120);
        txtScore.setSize(30);
        txtBestScore = new GameText("Best Score : " + Instance.bestScore, MainActivity.width / 2 - 5, MainActivity.height / 2 + 35 - 120);
        txtBestScore.setSize(30);
        btnMenu = new GameButton((int) MainActivity.width / 2 - 5 - 100, (int) MainActivity.height / 2 + 30, 100, 100, Asset.btnMenu);
        btnRestart = new GameButton((int) MainActivity.width / 2 + 10, (int) MainActivity.height / 2 + 30, 100, 100, Asset.btnRestart);
    }

    @Override
    public void update(float d) {

    }

    @Override
    public void render(Drawer drawer) {
        txtScore.render(drawer);
        txtBestScore.render(drawer);
        btnMenu.render(drawer);
        btnRestart.render(drawer);
        //drawer.drawImage(Asset.panelGameOver,(int)MainActivity.width/3,(int)MainActivity.height/2 - 30,250,400);
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent, int touchX, int touchY) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            btnMenu.onTouchDown(touchX, touchY);
            btnRestart.onTouchDown(touchX, touchY);
            if (btnMenu.isPressed(touchX, touchY)) {
                btnMenu.cancel();
                setExistingState(new MenuState());
            } else if (btnRestart.isPressed(touchX, touchY)) {
                btnRestart.cancel();
                setExistingState(new PlayState());
            }
        }
        return false;
    }
}
