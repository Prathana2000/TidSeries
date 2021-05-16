package org.classapp.tidseries1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailSingUp, usernameSignUp, passwordSignUp;
    private ImageButton signUpButton;
    private DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        emailSingUp = findViewById(R.id.signupemail);
        usernameSignUp = findViewById(R.id.signupusername);
        passwordSignUp = findViewById(R.id.signuppassword);

        signUpButton = findViewById(R.id.signupbutton);
        myDB = new DataBaseHelper(this);
        insertUser();
    }

    private void insertUser() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameSignUp.getText().toString().trim();
                String password = passwordSignUp.getText().toString();
                String email = emailSingUp.getText().toString();

                //check email
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailSingUp.setError("Invalid email!");
                    emailSingUp.requestFocus();
                    return;
                }

                if (username.isEmpty()) {
                    usernameSignUp.setError("Username Required!");
                    usernameSignUp.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    passwordSignUp.setError("Password Required!");
                    passwordSignUp.requestFocus();
                    return;
                }

                boolean singupSuccess = myDB.registerUser(usernameSignUp.getText().toString(), emailSingUp.getText().toString(), passwordSignUp.getText().toString());
                if (singupSuccess) {
                    Toast.makeText(SignUpActivity.this, "User Registered Successfully!!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                    finish();
                } else
                    Toast.makeText(SignUpActivity.this, "Registration Error!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}