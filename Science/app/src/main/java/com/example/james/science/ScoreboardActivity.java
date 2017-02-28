package com.example.james.science;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ScoreboardActivity extends AppCompatActivity {
    private String first;
    private String second;
    private String third;
    private TextView firstPlace; // leaderboard text views
    private TextView secondPlace;
    private TextView thirdPlace;
    private int listSize = 0;
    private TextView scores; // for checking reading from a file
    private ArrayList<String> scoresList = new ArrayList<String>();

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent gameIntent = new Intent(v.getContext(), MosquitoQuizActivity.class);
            startActivity(gameIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        setTitle("Scoreboard");

        firstPlace = (TextView)findViewById(R.id.playerOne);
        secondPlace = (TextView)findViewById(R.id.playerTwo);
        thirdPlace = (TextView)findViewById(R.id.playerThree);

        Button leaveButton = (Button)findViewById(R.id.beatThemButton);
        leaveButton.setOnClickListener(buttonListener);

        String dummyText = "spaceinvader1978: 30, xbobbyx: 0, ghost: 15, beanstalkjack: 20, jackblack: 5";
        fileWriter(dummyText, "scores");

        scores = (TextView)findViewById(R.id.scoresTextView);
        readFile("scores");
        readScores(this, "scores");
    }

    public void updateFile(String username, int userScore) {
        String passToFile = username + ": " + userScore + ", ";
        fileWriter(passToFile, "scores");
    }

    public String readFile(String fileName) {
        Context context = this;
        StringBuilder builder = new StringBuilder();
        String line;
        BufferedReader reader = null;
        String[] scoresList = new String[100];
        try {
            File pointer = new File(context.getFilesDir(), fileName);
            reader = new BufferedReader(new FileReader(pointer));
            while ((line = reader.readLine()) != null){
                scoresList = line.split(",");
                builder.append(line);
            }
        } catch(FileNotFoundException f) {
            Log.d("ID", f.getMessage());
        } catch(IOException i) {
            Log.d("ID", i.getMessage());
        }
        Log.d("ID", builder.toString());
        scores.setText(builder.toString());

        listSize = scoresList.length;
        TextView playerCountTextView = (TextView)findViewById(R.id.playerCountTextView);
        playerCountTextView.setText("Player Count: " + listSize);
        return builder.toString();
    }

    public String readScores(Context context, String filename) {
        String [] scoresList;
        String tempStr = "";
        try {
            InputStream in = context.getAssets().open(filename);

            if (in != null) {

                InputStreamReader input = new InputStreamReader(in);
                BufferedReader br = new BufferedReader(input);
                scoresList = new String[100];
                String line = br.readLine();

                while ((line=br.readLine()) != null)  {
                    scoresList = line.split(",");

                }
                in.close();

                listSize = scoresList.length;
                TextView playerCountTextView = (TextView)findViewById(R.id.playerCountTextView);
                if(scoresList.length == 0) {
                    playerCountTextView.setText("Player Count: 0");
                }
                else {
                    playerCountTextView.setText("Player Count: " + listSize);
                }

                // sorts the arraylist
                Arrays.sort(scoresList);
                for (int i = 0; i < 3; i++) { //gets the top 3 from arraylist
                    tempStr = tempStr + "Player:" + i + " Score" + scoresList[i];
                    Toast.makeText(this,"Player:" + i + " Score" + tempStr,Toast.LENGTH_LONG);
                }
                first = scoresList[0];
                firstPlace.setText(first);
                return tempStr;
            }
            else {
                Log.d("ID", "It's the assests");
            }
        } catch (IOException e) {
            Log.d("ID", "Couldn't Read File Correctly");
        }
        return "";
    }

    public void fileWriter(String fileContents, String fileName) {
        Context context = this;
        Log.d("ID", "file dir = " + context.getFilesDir());
        try {
            File pointer = new File(context.getFilesDir(), fileName);
            FileWriter writer = new FileWriter(pointer);
            writer.write(fileContents);
            writer.close();
        } catch (IOException e) {
            Log.d("Me", "File Error: " + e);
        }
    }
}
