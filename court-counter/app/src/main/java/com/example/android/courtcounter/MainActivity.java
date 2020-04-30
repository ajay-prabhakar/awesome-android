package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scorea;
    int scoreb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(0);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
    public void addThreeteamA(View v){
        scorea=scorea+3;
        displayForTeamA(scorea);

    }
    public void addTwoteamA(View v){
        scorea=scorea+2;

        displayForTeamA(scorea);

    }
    public void addFreethrow(View v){
        scorea=scorea+1;
        displayForTeamA(scorea);

    }
    public void addThreeteamB(View v){
        scoreb=scoreb+3;
        displayForTeamB(scoreb);

    }
    public void addTwoteamB(View v){
        scoreb=scoreb+2;

        displayForTeamB(scoreb);

    }
    public void addFreethrowB(View v){
        scoreb=scoreb+1;
        displayForTeamB(scoreb);

    }
    public void reset(View v){
        scorea=0;
        scoreb=0;
        displayForTeamA(scorea);
        displayForTeamB(scoreb);
    }
}
