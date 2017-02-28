package com.example.james.science;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MosquitoQuizActivity extends AppCompatActivity {

    OnClickListener homeListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    OnClickListener continueListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent questionsIntent = new Intent(v.getContext(), MosquitoFirstQuestionActivity.class);
            startActivity(questionsIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosquito_start);

        setTitle("Mosquito: Info");

        // binding
        TextView title = (TextView)findViewById(R.id.gameTitleTextView);
        TextView mosGameInfo = (TextView)findViewById(R.id.gameInfoTextView);
        Button homeBtn = (Button)findViewById(R.id.homeButton);
        Button contBtn = (Button)findViewById(R.id.continueButton);

        homeBtn.setOnClickListener(homeListener);
        contBtn.setOnClickListener(continueListener);
    }
}
