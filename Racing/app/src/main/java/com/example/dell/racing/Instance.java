package com.example.dell.racing;

import android.widget.LinearLayout;

import com.example.dell.racing.DataModel.Score;
import com.example.dell.racing.DataModel.User;

import java.util.ArrayList;
import java.util.List;

public class Instance {
    public static User user = new User();
    public static List<Score> topTen = new ArrayList<>();
    public static int bestScore = 0;
}
