package com.example.james.science;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String name;
    private String username;
    private EditText nameInputValue;
    private EditText usernameInputValue;
    private UserDetails user;

    OnClickListener startButtonHandler = new OnClickListener() {
        @Override
        public void onClick(View v) {
            name = nameInputValue.getText().toString();
            username = usernameInputValue.getText().toString();
            //checks for name not being input
            if(nameInputValue.getText().toString().equals("")) {
                Toast.makeText(v.getContext(), "Missing name! Please fill in all fields",
                        Toast.LENGTH_LONG).show();
            }
            //checks for username not being input
            else if(usernameInputValue.getText().toString().length() == 0){
                Toast.makeText(v.getContext(), "Missing username! Please fill in all fields",
                        Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(v.getContext(), "Hello " + nameInputValue.getText().toString(),
                        Toast.LENGTH_LONG).show();
                user.setUsername(username);
                user.setName(name);
                Intent homeIntent = new Intent(v.getContext(), HomeActivity.class);
                //passing data between activities
                homeIntent.putExtra("name", name);
                homeIntent.putExtra("username", username);
                startActivity(homeIntent);
                //remove this activity from activity stack
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new UserDetails();

        //binding
        TextView title = (TextView)findViewById(R.id.titleTextView);
        TextView nameLabel = (TextView)findViewById(R.id.nameTextView);
        nameInputValue = (EditText)findViewById(R.id.nameInput);
        TextView usernameLabel = (TextView)findViewById(R.id.usernameTextView);
        usernameInputValue = (EditText)findViewById(R.id.usernameInput);
        Button startButton = (Button)findViewById(R.id.startBtn);
        //set the on click listener for the start button
        startButton.setOnClickListener(startButtonHandler);
    }

    //accessor methods
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }
}
