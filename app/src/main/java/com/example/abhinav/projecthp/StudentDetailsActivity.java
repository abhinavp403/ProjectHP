package com.example.abhinav.projecthp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Name, Course, Phone, Semester, Plan, Date;
    String bundledName, bundledCourse, bundledPhone, bundledSemester, bundledPlan, bundledDate;
    String Namevalue, Coursevalue, Phonevalue, Semestervalue, Planvalue, Datevalue;
    Button Back, Logout, Update, Delete;
    DatabaseStudent database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        database = new DatabaseStudent(StudentDetailsActivity.this);
        Name = (EditText) findViewById(R.id.name);
        Course = (EditText) findViewById(R.id.course);
        Phone = (EditText) findViewById(R.id.phone);
        Semester = (EditText) findViewById(R.id.semester);
        Plan = (EditText) findViewById(R.id.plan);
        Date = (EditText) findViewById(R.id.doj);
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
        bundledSemester = takeBundledData.getString("Semester");
        bundledPlan = takeBundledData.getString("Plan");
        bundledDate = takeBundledData.getString("Date");
        Name.setText(bundledName);
        Course.setText(bundledCourse);
        Phone.setText(bundledPhone);
        Semester.setText(bundledSemester);
        Plan.setText(bundledPlan);
        Date.setText(bundledDate);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailsActivity.this, StudentNamesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDetailsActivity.this, LoginScreenActivity.class);
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
        Semestervalue = Semester.getText().toString();
        Planvalue = Plan.getText().toString();
        Datevalue = Date.getText().toString();
        SettersGetters sg = new SettersGetters();
        sg.setName(bundledName);
        sg.setCourse(bundledCourse);
        sg.setPhone(bundledPhone);
        sg.setSemester(bundledSemester);
        sg.setPlan(bundledPlan);
        sg.setDate(bundledDate);
        if(v.getId() == R.id.update){
            updateDetails(sg);
        }
        else if(v.getId() == R.id.delete){
            deleteDetails(sg);
        }
    }

    private void updateDetails(SettersGetters sg) {
        DatabaseStudent androidOpenDbHelper = new DatabaseStudent(this);
        SQLiteDatabase db = androidOpenDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseStudent.name_table, Namevalue);
        contentValues.put(DatabaseStudent.course_table, Coursevalue);
        contentValues.put(DatabaseStudent.phone_table, Phonevalue);
        contentValues.put(DatabaseStudent.semester_table, Semestervalue);
        contentValues.put(DatabaseStudent.plan_table, Planvalue);
        contentValues.put(DatabaseStudent.date_table, Datevalue);
        String[] whereClauseArgument = new String[1];
        whereClauseArgument[0] = sg.getName();
        System.out.println("whereClauseArgument[0] is :" + whereClauseArgument[0]);
        db.update(DatabaseStudent.table, contentValues,DatabaseStudent.name_table+"=?", whereClauseArgument);
        db.close();
        Intent intent = new Intent(StudentDetailsActivity.this, StudentNamesActivity.class);
        startActivity(intent);
        finish();
    }

    private void deleteDetails(SettersGetters sg) {
        DatabaseStudent androidOpenDbHelper = new DatabaseStudent(this);
        SQLiteDatabase db = androidOpenDbHelper.getWritableDatabase();
        String[] whereClauseArgument = new String[1];
        whereClauseArgument[0] = sg.getName();
        db.delete(DatabaseStudent.table, DatabaseStudent.name_table+"=?", whereClauseArgument);
        db.close();
        Intent intent = new Intent(StudentDetailsActivity.this, StudentNamesActivity.class);
        startActivity(intent);
        finish();
    }
}