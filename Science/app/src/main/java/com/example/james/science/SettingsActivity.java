package com.example.james.science;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import static com.example.james.science.R.string.app_name;

public class SettingsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // sets title in navigation bar
        setTitle("Settings");

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
                (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_settings_name);
        navHeaderName.setText(name);
        TextView navHeaderUsername =
                (TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_settings_username);
        navHeaderUsername.setText(username);
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
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            aboutIntent();
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
            finish();
        } else if (id == R.id.nav_about) {
            aboutIntent();
            finish();
        } else if (id == R.id.nav_settings) {
            //do nothing
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

    /*
      I have put this code into it's own method as it will need to be re-used. This code will be
      needed for the drawer menu about & the action about options, therefore its more efficient
      to call this method than type the same code twice
     */
    private void aboutIntent() {
        Intent aboutIntent = new Intent(this, AboutActivity.class);
        aboutIntent.putExtra("name", name);
        aboutIntent.putExtra("username", username);
        startActivity(aboutIntent);
    }
}
