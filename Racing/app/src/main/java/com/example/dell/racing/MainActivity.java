package com.example.dell.racing;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.dell.racing.DoAPI.DoGetScore;
import com.example.dell.racing.View.GameView;

public class MainActivity extends AppCompatActivity {
    public static int width =480;
    public static int height = 720;
    public static AssetManager assets;
    public static GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        assets = getAssets();
        gameView = new GameView(this,width,height);
        setContentView(gameView);

        new DoGetScore().execute("http://192.168.42.107:10101/api/Scores?top=10");
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
