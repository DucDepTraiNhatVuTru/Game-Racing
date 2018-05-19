package com.example.dell.racing.State;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.dell.racing.Asset;
import com.example.dell.racing.DataModel.Opstacle;
import com.example.dell.racing.Drawer;
import com.example.dell.racing.Instance;
import com.example.dell.racing.UI.GameButton;
import com.example.dell.racing.UI.GameText;
import com.example.dell.racing.MainActivity;
import com.example.dell.racing.View.GameBackground;
import com.example.dell.racing.DataModel.MyCar;

import java.util.ArrayList;
import java.util.List;

public class PlayState extends State {
    private GameBackground gameBackground;
    private MyCar car;
    private List<Opstacle> opstacles;
    private GameText textScore;
    private int score = 0;
    private GameButton btnPause;

    @Override
    public void init() {
        gameBackground = new GameBackground(MainActivity.width, MainActivity.height);
        car = new MyCar(175, 480, 50, 100);
        textScore = new GameText("0", MainActivity.width / 2 - 4, 100);
        opstacles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Opstacle opstacle = new Opstacle(55, 75);
            opstacles.add(opstacle);
        }
        btnPause = new GameButton(400, 10, 64, 64, Asset.btnPause);
    }

    @Override
    public void update(float d) {
        gameBackground.update();
        car.update(d);
        for (Opstacle opstacle : opstacles
                ) {
            opstacle.update();
        }
        if (!checkGameOver()) {
            updateScore();
            textScore.setText(score + "");
        }
    }

    @Override
    public void render(Drawer drawer) {
        renderBackground(drawer);
        car.render(drawer);
        textScore.render(drawer);
        for (Opstacle opstacle : opstacles
                ) {
            opstacle.render(drawer);
        }
        btnPause.render(drawer);
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent, int touchX, int touchY) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            btnPause.onTouchDown(touchX, touchY);

            if (!btnPause.isPressed(touchX, touchY)) {
                car.onTouch(motionEvent, touchX, touchY);

            } else if (btnPause.isPressed(touchX, touchY)) {
                btnPause.cancel();
                setPauseGame();
            }
        }

        return false;
    }

    private void renderBackground(Drawer drawer) {
        Rect fromRect = new Rect(0, 0, gameBackground.getWidth(),
                gameBackground.getHeight() - gameBackground.getyClip());
        Rect toRect = new Rect(0, gameBackground.getyClip(), MainActivity.width, gameBackground.getHeight());

        Rect fromRectReverse = new Rect(0, gameBackground.getHeight() - gameBackground.getyClip(), gameBackground.getWidth(), gameBackground.getHeight());
        Rect toRectReverse = new Rect(0, 0, MainActivity.width, gameBackground.getyClip());

        if (!gameBackground.isReversedFirst()) {
            drawer.getCanvas().drawBitmap(gameBackground.getBackground(), fromRect, toRect, drawer.getPaint());
            drawer.getCanvas().drawBitmap(gameBackground.getBackgroundReversed(), fromRectReverse, toRectReverse, drawer.getPaint());
        } else {
            drawer.getCanvas().drawBitmap(gameBackground.getBackground(), fromRectReverse, toRectReverse, drawer.getPaint());
            drawer.getCanvas().drawBitmap(gameBackground.getBackgroundReversed(), fromRect, toRect, drawer.getPaint());
        }
    }

    private boolean checkGameOver() {
        for (Opstacle opstacle : opstacles
                ) {
            if (car.getRect().intersect(opstacle.getRect())) {
                Asset.playSound(Asset.dead);
                if (score > Instance.bestScore) {

                    Instance.bestScore = score;
                }
                setExistingState(new GameOverState(score));
                return true;
            }
        }
        return false;
    }

    private void updateScore() {
        int opstacle = opstacles.get(0).getRect().top;
        int myCar = car.getRect().bottom;
        if (opstacle > myCar && !opstacles.get(0).isPassed()) {
            Asset.playSound(Asset.ping);
            score++;
            opstacles.get(0).setPassed(true);
        }
    }
}
