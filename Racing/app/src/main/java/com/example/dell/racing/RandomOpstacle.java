package com.example.dell.racing;

import java.util.Random;

public class RandomOpstacle {
    private static Random rand = new Random();

    public static int getRandIntBetween(int lowerBound, int upperBound) {
        int so = rand.nextInt(upperBound - lowerBound) + lowerBound;
        if(so<240-75){
            return 240-75-75;
        }
        else if(so>=240-75&&so<240){
            return 240-75;
        }
        else if(so>240&&so<=240+75){
            return 240;
        }
        else if(so>240+75){
            return 240+75;
        }
        return 0;
    }

    public static int getRandInt(int upperBound) {
        return rand.nextInt(upperBound);
    }
}
