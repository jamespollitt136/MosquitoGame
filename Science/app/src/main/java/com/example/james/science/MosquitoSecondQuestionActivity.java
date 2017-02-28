package com.example.james.science;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MosquitoSecondQuestionActivity extends AppCompatActivity {

    private CheckBox bloodButton;
    private CheckBox sexButton;
    private CheckBox pregnancyButton;
    private CheckBox noneButton;
    private MediaPlayer correctPlayer;
    private SoundPool soundPool;
    private int score;
    private int wrongSoundId;

    OnClickListener questionTwoSubmitListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(bloodButton.isChecked() && sexButton.isChecked() && pregnancyButton.isChecked()
                    && !noneButton.isChecked()){ // the correct answer
                correctPlayer.start(); //MediaPlayer
                score += 15;
                Intent questionThreeIntent = new Intent(v.getContext(),
                        MosquitoThirdQuestionActivity.class);
                questionThreeIntent.putExtra("score", score);
                startActivity(questionThreeIntent);
                finish();
            }
            else if(noneButton.isChecked()){
                soundPool.play(wrongSoundId, 1.0f, 1.0f, 1, 0, 1); //SoundPool
                Toast.makeText(v.getContext(),"That's incorrect! Try again.",
                        Toast.LENGTH_SHORT).show();
                bloodButton.setChecked(false);
                sexButton.setChecked(false);
                pregnancyButton.setChecked(false);
                noneButton.setChecked(false);
            }
            else {
                soundPool.play(wrongSoundId, 1.0f, 1.0f, 1, 0, 1); //SoundPool
                Toast.makeText(v.getContext(),"Please select all correct answers! Try again.",
                        Toast.LENGTH_SHORT).show();
                bloodButton.setChecked(false);
                sexButton.setChecked(false);
                pregnancyButton.setChecked(false);
                noneButton.setChecked(false);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosquito_second_question);
        // app bar title
        setTitle("Mosquito: Quiz");

        // retrieves the extras that were put in the intent from MosquitoFirstQuestionActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.score = extras.getInt("score");
        }

        bloodButton = (CheckBox)findViewById(R.id.bloodBox);
        sexButton = (CheckBox)findViewById(R.id.sexBox);
        pregnancyButton = (CheckBox)findViewById(R.id.pregnancyBox);
        noneButton = (CheckBox)findViewById(R.id.noneBox);

        Button questionTwoSubmit = (Button)findViewById(R.id.qTwoSubmitButton);
        // submit button event handler
        questionTwoSubmit.setOnClickListener(questionTwoSubmitListener);
        // sound via MediaPlayer
        correctPlayer = MediaPlayer.create(this, R.raw.correct);
        // sound via SoundPool
        AudioAttributes audioAttributes;
        // if API level greater/equal to 21 (Lollipop) use SoundPool.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else { // if API level is lower than 21 (Lollipop)
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 1);
        }
        wrongSoundId = soundPool.load(this, R.raw.wrong, 1);
    }
}
