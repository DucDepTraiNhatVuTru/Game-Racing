package com.example.dell.racing;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import java.io.IOException;
import java.io.InputStream;

public class Asset {

    public static Bitmap gameBackground, gameLoading, car, opstacle, nen;
    public static Bitmap btnPlay, btnPause, btnResume, btnRestart, btnMenu, panelGameOver, btnHightScore;
    public static Typeface typeface;
    public static SoundPool soundPool;
    public static int ping=-1,dead=-1;

    public static void load(){
        gameBackground = loadImageBitmap("background.png",true);
        gameLoading = loadImageBitmap("loading.jpg", true);
        car = loadImageBitmap("car2.png",true);
        opstacle = loadImageBitmap("barrel.png",true);
        nen = loadImageBitmap("nen.jpg",true);

        btnPlay = loadImageBitmap("play.png",true);
        btnPause = loadImageBitmap("pause.png",true);
        btnResume = loadImageBitmap("resume.png",true);
        btnRestart = loadImageBitmap("restart.png",true);
        btnMenu = loadImageBitmap("menu.png",true);
        panelGameOver = loadImageBitmap("panel.png",true);
        btnHightScore = loadImageBitmap("hightscore.png",true);

        typeface = Typeface.create(Typeface.createFromAsset(MainActivity.assets,"fonts/FlappyBirdFont.TTF"),Typeface.BOLD_ITALIC);

        soundPool = buildSoundPool();
        ping=loadSound("sounds/Ping.mp3");
        dead=loadSound("sounds/Dead.mp3");
    }

    public static Bitmap loadImageBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;
        try {
            inputStream = MainActivity.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        //  options.inDither = true;
        if (transparency) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Bitmap.Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null,
                options);
        return bitmap;
    }
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static SoundPool buildSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(25)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        return soundPool;
    }

    public static int loadSound(String filename) {
        int soundID = 0;
        if (soundPool == null) {
            buildSoundPool();
        }
        try {
            soundID = soundPool.load(MainActivity.assets.openFd(filename), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soundID;
    }
    public static void playSound(int soundID) {
        if (soundPool != null) {
            soundPool.play(soundID, 1, 1, 1, 0, 1);
        }
    }
}
