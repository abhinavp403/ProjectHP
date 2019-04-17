package com.example.abhinav.projecthp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String PREFER_NAME = "Reg";
    private SharedPreferences sharedPreferences;
    Editor editor;
    private EditText emailId, Password, confnewPassword;
    private TextView submit, back;
    private Button submitButton;
    SessionManagement session;
    String uEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailId = (EditText) findViewById(R.id.registered_emailid);
        Password = (EditText) findViewById(R.id.new_passowrd);
        confnewPassword = (EditText) findViewById(R.id.conf_new_passowrd);
        submit = (TextView) findViewById(R.id.forgot_button);
        back = (TextView) findViewById(R.id.backToLoginBtn);
        submitButton = (Button) findViewById(R.id.submit_button);
        session = new SessionManagement(getApplicationContext());
        sharedPreferences = getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailId.getText().toString().length() == 0) {
                    emailId.setError("Email not entered");
                    emailId.requestFocus();
                }
                if (emailId.getText().toString().length() > 0 && (!emailId.getText().toString().contains("@") || !emailId.getText().toString().contains(".com"))) {
                    emailId.setError("Incorrect Email ID");
                    emailId.requestFocus();
                }
                else if (emailId.getText().toString().length() > 0 && (emailId.getText().toString().contains("@") && emailId.getText().toString().contains(".com"))){
                    if (sharedPreferences.contains("Email")) {
                        uEmail = sharedPreferences.getString("Email", "");
                    }
                    if ((emailId.getText().toString()).equals(uEmail)) {
                        session.createForgotPasswordSession(uEmail);
                        Password.setVisibility(View.VISIBLE);
                        confnewPassword.setVisibility(View.VISIBLE);
                        submitButton.setVisibility(View.VISIBLE);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Email ID not found", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().remove("Password").commit();
                sharedPreferences.getString("Email",uEmail);
                String password = Password.getText().toString();
                if (Password.getText().toString().length() < 6) {
                    Password.setError("Password should be at least 6 characters");
                    Password.requestFocus();
                }
                if (!Password.getText().toString().equals(confnewPassword.getText().toString())) {
                    confnewPassword.setError("Password not matched");
                    confnewPassword.requestFocus();
                }
                else {
                    editor.putString("Password",password);
                    editor.commit();
                    Intent intent = new Intent(ForgotPasswordActivity.this, LoginScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}