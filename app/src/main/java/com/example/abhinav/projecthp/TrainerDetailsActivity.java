package com.example.abhinav.projecthp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TrainerDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Name, Course, Phone, College, WorkPlace, Experience;
    String bundledName, bundledCourse, bundledPhone, bundledCollege, bundledWorkPlace, bundledExperience;
    String Namevalue, Coursevalue, Phonevalue, Collegevalue, WorkPlacevalue, Experiencevalue;
    Button Back, Logout, Update, Delete;
    DatabaseTrainer database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_details);

        database = new DatabaseTrainer(TrainerDetailsActivity.this);
        Name = (EditText) findViewById(R.id.name);
        Course = (EditText) findViewById(R.id.course);
        Phone = (EditText) findViewById(R.id.phone);
        College = (EditText) findViewById(R.id.college);
        WorkPlace = (EditText) findViewById(R.id.workplace);
        Experience = (EditText) findViewById(R.id.experience);
        Back = (Button) findViewById(R.id.back);
        Logout = (Button) findViewById(R.id.logout);
        Update = (Button) findViewById(R.id.update);
        Delete = (Button) findViewById(R.id.delete);
        Update.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Bundle takeBundledData = getIntent().getExtras();
        bundledName = takeBundledData.getString("Name");
        bundledCourse = takeBundledData.getString("Course");
        bundledPhone = takeBundledData.getString("Phone");
        bundledCollege = takeBundledData.getString("College");
        bundledWorkPlace = takeBundledData.getString("WorkPlace");
        bundledExperience = takeBundledData.getString("Experience");
        Name.setText(bundledName);
        Course.setText(bundledCourse);
        Phone.setText(bundledPhone);
        College.setText(bundledCollege);
        WorkPlace.setText(bundledWorkPlace);
        Experience.setText(bundledExperience);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainerDetailsActivity.this, TrainerNamesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainerDetailsActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Namevalue = Name.getText().toString();
        Coursevalue = Course.getText().toString();
        Phonevalue = Phone.getText().toString();
        Collegevalue = College.getText().toString();
        WorkPlacevalue = WorkPlace.getText().toString();
        Experiencevalue = Experience.getText().toString();
        SettersGetters sg = new SettersGetters();
        sg.setName(bundledName);
        sg.setCourse(bundledCourse);
        sg.setPhone(bundledPhone);
        sg.setCollege(bundledCollege);
        sg.setWorkPlace(bundledWorkPlace);
        sg.setExperience(bundledExperience);
        if(v.getId() == R.id.update){
            updateDetails(sg);
        }
        else if(v.getId() == R.id.delete){
            deleteDetails(sg);
        }
    }

    private void updateDetails(SettersGetters sg) {
        DatabaseTrainer androidOpenDbHelper = new DatabaseTrainer(this);
        SQLiteDatabase db = androidOpenDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseTrainer.name_table, Namevalue);
        contentValues.put(DatabaseTrainer.course_table, Coursevalue);
        contentValues.put(DatabaseTrainer.phone_table, Phonevalue);
        contentValues.put(DatabaseTrainer.college_table, Collegevalue);
        contentValues.put(DatabaseTrainer.workplace_table, WorkPlacevalue);
        contentValues.put(DatabaseTrainer.exp_table, Experiencevalue);
        String[] whereClauseArgument = new String[1];
        whereClauseArgument[0] = sg.getName();
        System.out.println("whereClauseArgument[0] is :" + whereClauseArgument[0]);
        db.update(DatabaseTrainer.table, contentValues,DatabaseTrainer.name_table+"=?", whereClauseArgument);
        db.close();
        Intent intent = new Intent(TrainerDetailsActivity.this, TrainerNamesActivity.class);
        startActivity(intent);
        finish();
    }

    private void deleteDetails(SettersGetters sg) {
        DatabaseTrainer androidOpenDbHelper = new DatabaseTrainer(this);
        SQLiteDatabase db = androidOpenDbHelper.getWritableDatabase();
        String[] whereClauseArgument = new String[1];
        whereClauseArgument[0] = sg.getName();
        db.delete(DatabaseTrainer.table, DatabaseTrainer.name_table+"=?", whereClauseArgument);
        db.close();
        Intent intent = new Intent(TrainerDetailsActivity.this, TrainerNamesActivity.class);
        startActivity(intent);
        finish();
    }
}
