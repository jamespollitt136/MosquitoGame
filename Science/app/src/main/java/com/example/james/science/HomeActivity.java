package com.example.james.science;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private String username;

    OnClickListener mosquitoQuizListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent mosquitoQuizIntent = new Intent(v.getContext(), MosquitoQuizActivity.class);
            startActivity(mosquitoQuizIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // sets title in the appbar to "Home"
        setTitle("Home");

        // retrieves the extras that were put in the intent from MainActivity's button handler
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.name = extras.getString("name");
            this.username = extras.getString("username");
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //set the name and username displayed in the menu
        TextView navHeaderName =
                (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_name);
        navHeaderName.setText(name);
        TextView navHeaderUsername =
                (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_username);
        navHeaderUsername.setText(username);

        // binding
        RelativeLayout homeLayout = (RelativeLayout)findViewById(R.id.content_home);
        ImageView mosquitoGame = (ImageView)findViewById(R.id.mosquitoGame);
        TextView mosquitoText = (TextView)findViewById(R.id.mosquitoTextView);

        mosquitoGame.setOnClickListener(mosquitoQuizListener);

        // snackbar for rating the app
        Snackbar feedbackSnackbar =
                Snackbar.make(homeLayout, "Would you like to rate this app?", Snackbar.LENGTH_LONG)
                        .setAction("RATE", new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // snack bar on click listener, if user clicks RATE, takes them to feedback
                                feedbackIntent();
                            }
                        });
        feedbackSnackbar.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            settingsIntent();
            return true;
        }
        else if(id == R.id.action_about) {
            aboutIntent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // drawer should close on click leaving home activity open
        } else if (id == R.id.nav_about) {
            aboutIntent();
        } else if (id == R.id.nav_settings) {
            settingsIntent();
        } else if (id == R.id.nav_feedback) {
            feedbackIntent();
        } else if (id == R.id.nav_scores) {
            Intent scoresIntent = new Intent(this, ScoreboardActivity.class);
            scoresIntent.putExtra("username", username);
            startActivity(scoresIntent);
        } else if (id == R.id.nav_logout) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // accessor methods
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    /*
      I have put this code into it's own method as it will need to be re-used. This code will be
      needed for the drawer menu about & the action about options, therefore its more efficient
      to call this method than type the same code twice
     */
    public void aboutIntent() {
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        aboutIntent.putExtra("name", name);
        aboutIntent.putExtra("username", username);
        startActivity(aboutIntent);
    }

    public void settingsIntent() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsIntent.putExtra("name", name);
        settingsIntent.putExtra("username", username);
        startActivity(settingsIntent);
    }

    public void feedbackIntent(){
        Intent feedbackIntent = new Intent(this, FeedbackActivity.class);
        feedbackIntent.putExtra("name", name);
        feedbackIntent.putExtra("username", username);
        startActivity(feedbackIntent);
    }

    public void logout() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
