package com.example.abhinav.projecthp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EnrollmentActivity extends AppCompatActivity {

    private TextView addStudent, addTrainer, addCourse;
    private Button Back, Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        Back = (Button) findViewById(R.id.back);
        Logout = (Button) findViewById(R.id.logout);
        addStudent = (TextView) findViewById(R.id.add_student);
        addTrainer = (TextView) findViewById(R.id.add_trainer);
        addCourse = (TextView) findViewById(R.id.add_course);

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollmentActivity.this, NewStudentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollmentActivity.this, NewTrainerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollmentActivity.this, NewCourseActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollmentActivity.this, LandingPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollmentActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}