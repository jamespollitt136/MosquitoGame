package com.example.james.science;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MosquitoThirdQuestionActivity extends AppCompatActivity {

    private int score;
    private MediaPlayer correctPlayer;
    private MediaPlayer wrongPlayer;


    View.OnClickListener dengueListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            correctPlayer.start(); //MediaPlayer
            score = score + 10;
            Intent questionFourIntent = new Intent(v.getContext(), MosquitoFourthQuestionActivity.class);
            questionFourIntent.putExtra("score", score);
            startActivity(questionFourIntent);
            finish();
        }
    };

    View.OnClickListener herpesListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            wrongPlayer.start();
            Toast.makeText(v.getContext(), "Sorry, that's herpes.", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener cancerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            wrongPlayer.start();
            Toast.makeText(v.getContext(), "Sorry, that's skin cancer.", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosquito_third_question);

        setTitle("Mosquito: Quiz");

        // retrieves the extras that were put in the intent from MosquitoSecondQuestionActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.score = extras.getInt("score");
        }

        // binding
        ImageButton cancerImage = (ImageButton)findViewById(R.id.cancerImageButton);
        ImageButton herpesImage = (ImageButton)findViewById(R.id.herpesImageButton);
        ImageButton dengueImage = (ImageButton)findViewById(R.id.dengueImageButton);

        cancerImage.setOnClickListener(cancerListener);
        herpesImage.setOnClickListener(herpesListener);
        dengueImage.setOnClickListener(dengueListener);

        // sound via MediaPlayer
        correctPlayer = MediaPlayer.create(this, R.raw.correct);
        wrongPlayer = MediaPlayer.create(this, R.raw.wrong);
    }

    public int getScores(){
        return score;
    }
}
