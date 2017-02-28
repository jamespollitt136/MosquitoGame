package com.example.james.science;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MosquitoFourthQuestionActivity extends AppCompatActivity {

    private int score;
    private EditText answer;
    private MediaPlayer correctPlayer;
    private MediaPlayer wrongPlayer;

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(answer.getText().toString().equalsIgnoreCase("B")
                    || answer.getText().toString().equalsIgnoreCase("B.")
                    || answer.getText().toString().equalsIgnoreCase("3 times it's body weight")
                    || answer.getText().toString().equalsIgnoreCase("3")
                    || answer.getText().toString().contains("3 times")) {
                //body
                correctPlayer.start(); //MediaPlayer
                score = score + 10;
                Intent gameIntent = new Intent(v.getContext(), GameActivity.class);
                startActivity(gameIntent);
                finish();
            }
            else {
                wrongPlayer.start();
                answer.setText("");
                Toast.makeText(MosquitoFourthQuestionActivity.this, "Sorry! That is the wrong answer",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosquito_fourth_question);

        // retrieves the extras that were put in the intent from MosquitoThirdQuestionActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.score = extras.getInt("score");
        }

        // binding
        TextView question = (TextView) findViewById(R.id.questionFourText);
        Button continueButton = (Button)findViewById(R.id.fourthContBtn);
        answer = (EditText)findViewById(R.id.answerInput);
        answer.setHint("e.g. 'C' or '5 times its body weight'");
        continueButton.setOnClickListener(buttonListener);

        // sound via MediaPlayer
        correctPlayer = MediaPlayer.create(this, R.raw.correct);
        wrongPlayer = MediaPlayer.create(this, R.raw.wrong);
    }
}
