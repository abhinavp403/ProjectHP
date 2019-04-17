package com.example.abhinav.projecthp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LandingPageActivity extends AppCompatActivity {

    private TextView StudentDetails, TrainerDetails, CourseDetails, AboutUs, Enrollment;
    Button Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        StudentDetails = (TextView) findViewById(R.id.student_enroll_details);
        TrainerDetails = (TextView) findViewById(R.id.trainer_details);
        CourseDetails = (TextView) findViewById(R.id.course_details);
        AboutUs = (TextView) findViewById(R.id.about_us);
        Enrollment = (TextView) findViewById(R.id.enrollment);
        Logout = (Button) findViewById(R.id.logout);

        StudentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, StudentNamesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        TrainerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, TrainerNamesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        CourseDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, CourseNamesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, AboutUsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Enrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, EnrollmentActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPageActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}