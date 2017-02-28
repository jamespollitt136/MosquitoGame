package com.example.james.science;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set the title in the appbar
        setTitle("About");

        // retrieve the extras
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

        // set the name and username displayed in the menu
        TextView navHeaderName =
                (TextView)navigationView.getHeaderView(0).findViewById(R.id.about_nav_name);
        navHeaderName.setText(name);
        TextView navHeaderUsername =
                (TextView)navigationView.getHeaderView(0).findViewById(R.id.about_nav_username);
        navHeaderUsername.setText(username);

        //binding
        LinearLayout aboutLinearLayout = (LinearLayout) findViewById(R.id.aboutLinLayout);
        ImageView androidLogo = (ImageView) findViewById(R.id.androidImageView);
        TextView androidText = (TextView) findViewById(R.id.androidTextView);
        TextView versionText = (TextView) findViewById(R.id.versionTextView);
        ImageView studioLogo = (ImageView) findViewById(R.id.asImageView);
        TextView studioText = (TextView) findViewById(R.id.asTextView);
        ImageView salfordLogo = (ImageView) findViewById(R.id.uosImageView);
        TextView salfordText = (TextView) findViewById(R.id.uosTextView);
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
        getMenuInflater().inflate(R.menu.about, menu);
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
            finish();
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
            /*
              As the home activity launched this (about) activity without using finish() it is still
              on the activity stack, so we finish this activity to go back to the home activity.
            */
            finish();
        } else if (id == R.id.nav_about) {
            // navigation drawer should close on its own leaving activity open
        } else if (id == R.id.nav_settings) {
            settingsIntent();
        } else if (id == R.id.nav_feedback) {
            Intent feedbackIntent = new Intent(this, FeedbackActivity.class);
            feedbackIntent.putExtra("name", name);
            feedbackIntent.putExtra("username", username);
            startActivity(feedbackIntent);
            finish();
        } else if (id == R.id.nav_logout) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void settingsIntent() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsIntent.putExtra("name", name);
        settingsIntent.putExtra("username", username);
        startActivity(settingsIntent);
        finish();
    }
}
