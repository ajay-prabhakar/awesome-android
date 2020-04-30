package com.example.android.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this,R.raw.music);
    }




    public void paly(View view){
        mediaPlayer.start();

        Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();
  }

      public void pause(View view){
    mediaPlayer.pause();
   }
}
