package com.example.dell.racing.State;

import android.view.MotionEvent;

import com.example.dell.racing.Asset;
import com.example.dell.racing.Drawer;
import com.example.dell.racing.Instance;
import com.example.dell.racing.MainActivity;
import com.example.dell.racing.UI.GameButton;
import com.example.dell.racing.UI.GameText;

import java.util.ArrayList;

public class HighScoreState extends State {
    private GameText high1,high2, high3, high4, high5 ;
    private int top = 100;
    private int left = 250;
    private int count = 1;
    private GameButton btnMenu;
    @Override
    public void init() {
        high1= new GameText("1. " + Instance.topTen.get(0).getEmail()+ "  " +
        Instance.topTen.get(0).getScore(), left, top+30*count);
        count++;
        high1.setSize(28);

        high2= new GameText("2. " + Instance.topTen.get(1).getEmail()+ "  " +
                Instance.topTen.get(1).getScore(), left, top+30*count);
        count++;
        high2.setSize(28);

        high3= new GameText("3. " + Instance.topTen.get(2).getEmail()+ "  " +
                Instance.topTen.get(2).getScore(), left, top+30*count);
        count++;
        high3.setSize(28);

        high4= new GameText("4. " + Instance.topTen.get(3).getEmail()+ "  " +
                Instance.topTen.get(3).getScore(), left, top+30*count);
        count++;
        high4.setSize(28);

        high5= new GameText("5. " + Instance.topTen.get(4).getEmail()+ "  " +
                Instance.topTen.get(4).getScore(), left, top+30*count);
        count++;
        high5.setSize(28);
        btnMenu = new GameButton((int) MainActivity.width / 2-50, (int) MainActivity.height - 200, 100, 100, Asset.btnMenu);
    }

    @Override
    public void update(float d) {

    }

    @Override
    public void render(Drawer drawer) {
        drawer.drawImage(Asset.nen,0,0, MainActivity.width,MainActivity.height);
        high1.render(drawer);
        high2.render(drawer);
        high3.render(drawer);
        high4.render(drawer);
        high5.render(drawer);
        btnMenu.render(drawer);
    }

    @Override
    public boolean onTouch(MotionEvent motionEvent, int touchX, int touchY) {
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            btnMenu.onTouchDown(touchX,touchY);
            if(btnMenu.isPressed(touchX,touchY)){
                btnMenu.cancel();
                setExistingState(new MenuState());
            }
        }
        return false;
    }
}
