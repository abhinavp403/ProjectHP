package com.example.abhinav.projecthp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CourseDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Course, TrainerNo, Plan;
    String bundledCourse, bundledTrainerNo, bundledPlan;
    String Coursevalue, TrainerNovalue, Planvalue;
    Button Back, Logout, Update, Delete;
    DatabaseCourse database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        database = new DatabaseCourse(CourseDetailsActivity.this);
        Course = (EditText) findViewById(R.id.course);
        TrainerNo = (EditText) findViewById(R.id.trainerno);
        Plan = (EditText) findViewById(R.id.plan);
        Back = (Button) findViewById(R.id.back);
        Logout = (Button) findViewById(R.id.logout);
        Update = (Button) findViewById(R.id.update);
        Delete = (Button) findViewById(R.id.delete);
        Update.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Bundle takeBundledData = getIntent().getExtras();
        bundledCourse = takeBundledData.getString("Course");
        bundledTrainerNo = takeBundledData.getString("TrainerNo");
        bundledPlan = takeBundledData.getString("Plan");
        Course.setText(bundledCourse);
        TrainerNo.setText(bundledTrainerNo);
        Plan.setText(bundledPlan);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailsActivity.this, CourseNamesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseDetailsActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Coursevalue = Course.getText().toString();
        TrainerNovalue = TrainerNo.getText().toString();
        Planvalue = Plan.getText().toString();
        SettersGetters sg = new SettersGetters();
        sg.setCourse(bundledCourse);
        sg.setTrainerNo(bundledTrainerNo);
        sg.setPlan(bundledPlan);
        if(v.getId() == R.id.update){
            updateDetails(sg);
        }
        else if(v.getId() == R.id.delete){
            deleteDetails(sg);
        }
    }

    private void updateDetails(SettersGetters sg) {
        DatabaseCourse androidOpenDbHelper = new DatabaseCourse(this);
        SQLiteDatabase db = androidOpenDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseCourse.course_table, Coursevalue);
        contentValues.put(DatabaseCourse.trainerno_table, TrainerNovalue);
        contentValues.put(DatabaseCourse.plan_table, Planvalue);
        String[] whereClauseArgument = new String[1];
        whereClauseArgument[0] = sg.getName();
        System.out.println("whereClauseArgument[0] is :" + whereClauseArgument[0]);
        db.update(DatabaseCourse.table, contentValues,DatabaseCourse.course_table+"=?", whereClauseArgument);
        db.close();
        Intent intent = new Intent(CourseDetailsActivity.this, CourseNamesActivity.class);
        startActivity(intent);
        finish();
    }

    private void deleteDetails(SettersGetters sg) {
        DatabaseCourse androidOpenDbHelper = new DatabaseCourse(this);
        SQLiteDatabase db = androidOpenDbHelper.getWritableDatabase();
        String[] whereClauseArgument = new String[1];
        whereClauseArgument[0] = sg.getName();
        db.delete(DatabaseCourse.table, DatabaseCourse.course_table+"=?", whereClauseArgument);
        db.close();
        Intent intent = new Intent(CourseDetailsActivity.this, CourseNamesActivity.class);
        startActivity(intent);
        finish();
    }
}