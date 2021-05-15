package org.classapp.tidseries1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText loginUsername , loginPassword;
    private ImageButton loginButton;
    private DataBaseHelper myDb;
    private TextView signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpButton = findViewById(R.id.main_btn_signin);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        loginUsername = findViewById(R.id.loginusername);
        loginPassword = findViewById(R.id.loginpassword);
        loginButton = findViewById(R.id.loginbutton);

        myDb = new DataBaseHelper(this);

        loginUser();
    }
    private void loginUser(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = myDb.checkUser(loginUsername.getText().toString() , loginPassword.getText().toString() );
                if (var){
                    Toast.makeText(LoginActivity.this,"Login Successfully" , Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this , HomeActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Login Failed!!", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}