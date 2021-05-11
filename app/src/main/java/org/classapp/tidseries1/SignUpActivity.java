package org.classapp.tidseries1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailSingUp , usernameSignUp , passwordSignUp;
    private ImageButton signUpButton;
    private DataBaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailSingUp = findViewById(R.id.signupemail);
        usernameSignUp = findViewById(R.id.signupusername);
        passwordSignUp = findViewById(R.id.signuppassword);

        signUpButton = findViewById(R.id.signupbutton);
        myDB = new DataBaseHelper(this);
        insertUser();
    }
    private void insertUser(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            boolean var = myDB.registerUser(usernameSignUp.getText().toString() , emailSingUp.getText().toString() , passwordSignUp.getText().toString());
            if (var){
                Toast.makeText(SignUpActivity.this, "User Registered Successfully!!", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(SignUpActivity.this,"Registration Error!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}