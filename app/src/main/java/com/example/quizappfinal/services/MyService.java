package com.example.quizappfinal.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.quizappfinal.R;

public class MyService extends Service {
    MediaPlayer myPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        myPlayer = MediaPlayer.create(this, R.raw.sos);
        myPlayer.setLooping(true); // Set looping
    }
    @Override
    public void onStart(Intent intent, int startid) {
        myPlayer.start();
    }
    @Override
    public void onDestroy() {
        myPlayer.stop();
    }
}
