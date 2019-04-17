package com.example.abhinav.projecthp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {

    private static final String PREFER_NAME = "Reg";
    private SharedPreferences sharedPreferences;
    Button Button,Exit;
    private EditText Password, Email;
    TextView Signup, forgotPassword;
    SessionManagement session;

    protected void validateData() {
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        if (email.length() == 0) {
            Email.setError("Email not entered");
            Email.requestFocus();
        }
        if (!email.contains("@") || !email.contains(".com")) {
            Email.setError("Incorrect Email ID");
            Email.requestFocus();
        }
        if (password.length() < 6) {
            Password.setError("Password should be at least of 6 characters");
            Password.requestFocus();
        }
        else {
            String uEmail = null;
            String uPassword = null;
            if (sharedPreferences.contains("Email")) {
                uEmail = sharedPreferences.getString("Email", "");
            }
            if (sharedPreferences.contains("Password")) {
                uPassword = sharedPreferences.getString("Password", "");
            }
            if (email.equals(uEmail) && password.equals(uPassword)) {
                session.createLoginSession(uEmail, uPassword);
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginScreenActivity.this, LandingPageActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Email ID or Password is incorrect", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        Password = (EditText) findViewById(R.id.pass_login);
        Email = (EditText) findViewById(R.id.username);
        Button = (Button) findViewById(R.id.button_login);
        Exit = (Button) findViewById(R.id.button_exit);
        Signup = (TextView) findViewById(R.id.tv_signup);
        forgotPassword = (TextView) findViewById(R.id.tv_forgotpassword);
        session = new SessionManagement(getApplicationContext());
        sharedPreferences = getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreenActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginScreenActivity.this, ForgotPasswordActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}