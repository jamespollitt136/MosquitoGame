package com.example.james.science;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private String username;
    private RatingBar gamesRating;
    private RatingBar appRating;
    private ArrayList<SurveyItem>survey = new ArrayList<SurveyItem>();
    private SurveyAdapter adapter;

    // submit button listener
    OnClickListener submitListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SurveyItem surveyItem = new SurveyItem(getName(), getUsername(),
                    gamesRating.getRating(), appRating.getRating());
            adapter.add(surveyItem);
            Toast.makeText(FeedbackActivity.this, "Thank you for your feedback",
                    Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set title in app bar
        setTitle("Feedback");

        // retrieves the extras that were put in the intent from MainActivity's button handler
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.name = extras.getString("name");
            this.username = extras.getString("username");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //set the name and username displayed in the menu
        TextView navHeaderName =
                (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_feedback_name);
        navHeaderName.setText(name);
        TextView navHeaderUsername =
                (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_feedback_username);
        navHeaderUsername.setText(username);

        // binding
        TextView feedbackInfo = (TextView)findViewById(R.id.infoTextView);
        TextView gameLabel = (TextView)findViewById(R.id.gamesRatingTextView);
        gamesRating = (RatingBar)findViewById(R.id.gamesRatingBar);
        TextView appLabel = (TextView)findViewById(R.id.appRatingTextView);
        appRating = (RatingBar)findViewById(R.id.appRatingBar);
        Button submit = (Button)findViewById(R.id.submitButton);
        ListView feedbackResults = (ListView)findViewById(R.id.feedbackListView);

        // put 1 dummy value in array
        survey.add(new SurveyItem("James Pollitt", "spaceinvader1978", 4, 5));
        adapter = new SurveyAdapter(this, android.R.layout.simple_list_item_1, survey);
        feedbackResults.setAdapter(adapter);

        submit.setOnClickListener(submitListener);
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
        getMenuInflater().inflate(R.menu.feedback, menu);
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
        else if (id == R.id.action_about) {
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
            finish();
        } else if (id == R.id.nav_about) {
            aboutIntent();
        } else if (id == R.id.nav_settings) {
            settingsIntent();
        } else if (id == R.id.nav_feedback) {
            // do nothing
        } else if (id == R.id.nav_logout) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return name;
    }

    /*I have put this code into it's own method as it will need to be re-used. This code will be
    needed for the drawer menu about & the action about options, therefore its more efficient to
    call this method than type the same code twice
    */
    private void aboutIntent() {
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        aboutIntent.putExtra("name", name);
        aboutIntent.putExtra("username", username);
        startActivity(aboutIntent);
        finish();
    }

    private void settingsIntent() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsIntent.putExtra("name", name);
        settingsIntent.putExtra("username", username);
        startActivity(settingsIntent);
        finish();
    }
}
