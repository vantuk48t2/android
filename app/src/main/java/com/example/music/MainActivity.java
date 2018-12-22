package com.example.music;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonStart;
    Button buttonStop;
    Integer isPlay = 0;
    //Notification

    private static final int NOTIFICATION_ID = 123;
    MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        //Log.i("abc", "1");
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

        @Override
        protected void onPause() {
            super.onPause();
            if(isPlay ==0) {
                isPlay = 1;
                Intent startIntent = new Intent(this, MyService.class);
                startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);//notification
                startService(startIntent);
            }
        }

        @Override
        protected void onResume() {
            super.onResume();
            if(isPlay==1) {
                isPlay = 0;
                Intent stopIntent = new Intent(this, MyService.class);
                stopIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);//notification
                stopService(stopIntent);
            }
        }

        @Override
    public void onClick(View v) {
        if (v == buttonStart && isPlay ==0) {
            isPlay =1;
            player = MediaPlayer.create(this, R.raw.minute);
            player.setLooping(true);
            player.start();
            Intent startIntent = new Intent(this, MyService.class);
            startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);//notification
            startService(startIntent);
        } else if (v == buttonStop ) {
            player.stop();
        }
    }

}

