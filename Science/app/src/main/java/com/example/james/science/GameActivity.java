package com.example.james.science;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity {

    private int score;
    private MosquitoSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setTitle("Mosquito: Game");

        this.score = 40;

        surfaceView = new MosquitoSurfaceView(this);
        RelativeLayout content = (RelativeLayout)findViewById(R.id.activity_game);
        content.addView(surfaceView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mosquito_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.menu_play:
                surfaceView.start();
                return true;
            case R.id.menu_stop:
                surfaceView.stop();
                return true;
            case R.id.action_home:
                surfaceView.stop();
                finish();
                return true;
        }
        if(id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        surfaceView.stop();
        finish();
    }

    public int getScore() {
        return score;
    }
}
