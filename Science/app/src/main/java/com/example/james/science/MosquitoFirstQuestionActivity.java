package com.example.james.science;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MosquitoFirstQuestionActivity extends AppCompatActivity {

    private int score;
    private MediaPlayer correctPlayer;
    private MediaPlayer wrongPlayer;

    OnClickListener sevenListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            correctPlayer.start();
            score = score + 5;
            Intent secondQuestionIntent =
                    new Intent(v.getContext(),MosquitoSecondQuestionActivity.class);
            secondQuestionIntent.putExtra("score", score);
            startActivity(secondQuestionIntent);
            finish();
        }
    };

    OnClickListener wrongListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            wrongPlayer.start();
            Toast.makeText(v.getContext(),"That's incorrect! Try again.",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosquito_questions);

        setTitle("Mosquito: Quiz");

        // binding
        TextView questionOne = (TextView)findViewById(R.id.mosQuestionOneText);
        TextView firstQuestion = (TextView)findViewById(R.id.mosFirstQuestion);
        Button sevenMillionButton = (Button)findViewById(R.id.sevenButton);
        Button twoMillionButton = (Button)findViewById(R.id.twoButton);
        Button fourHalfMillionButton = (Button)findViewById(R.id.fourButton);
        Button eightHundredThousandButton = (Button)findViewById(R.id.eightButton);

        // event handlers
        sevenMillionButton.setOnClickListener(sevenListener);
        twoMillionButton.setOnClickListener(wrongListener);
        fourHalfMillionButton.setOnClickListener(wrongListener);
        eightHundredThousandButton.setOnClickListener(wrongListener);

        correctPlayer = MediaPlayer.create(this, R.raw.correct);
        wrongPlayer = MediaPlayer.create(this, R.raw.wrong);
    }
}
