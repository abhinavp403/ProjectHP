package com.example.abhinav.projecthp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Editor editor;
    Button button;
    EditText FirstName, Password, ConfPassword, Email, Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirstName = (EditText) findViewById(R.id.firstname);
        Phone = (EditText) findViewById(R.id.phone);
        Password = (EditText) findViewById(R.id.pass);
        ConfPassword = (EditText) findViewById(R.id.confirmPass);
        Email = (EditText) findViewById(R.id.email);
        button = (Button) findViewById(R.id.button);
        sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);
        editor = sharedPreferences.edit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String firstname = FirstName.getText().toString();
                String phone = Phone.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String confpassword = ConfPassword.getText().toString();
                if (firstname.length() == 0) {
                    FirstName.setError("First name not entered");
                    FirstName.requestFocus();
                }
                if (!email.contains("@") || !email.contains(".com")) {
                    Email.setError("Incorrect Email ID");
                    Email.requestFocus();
                }
                if (phone.length() != 10) {
                    Phone.setError("Phone Number must be 10 digits");
                    Phone.requestFocus();
                }
                if (password.length() < 6) {
                    Password.setError("Password should be at least 6 characters");
                    Password.requestFocus();
                }
                if (!password.equals(confpassword)) {
                    ConfPassword.setError("Password not matched");
                    ConfPassword.requestFocus();
                }
                if (firstname.length() > 0 && (email.contains("@") && email.contains(".com")) && phone.length() == 10 && password.length() >= 6 && password.equals(confpassword)) {
                    editor.putString("First Name", firstname);
                    editor.putString("Email",email);
                    editor.putString("Password",password);
                    editor.putString("Phone",phone);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginScreenActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
