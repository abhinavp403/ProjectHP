package com.example.abhinav.projecthp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewCourseActivity extends AppCompatActivity {
    EditText Course, TrainerNo, Plan;
    Button AddButton, Back, Logout;
    DatabaseCourse database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        database = new DatabaseCourse(NewCourseActivity.this);
        Course = (EditText) findViewById(R.id.course);
        TrainerNo = (EditText) findViewById(R.id.trainerno);
        Plan = (EditText) findViewById(R.id.plan);
        AddButton = (Button) findViewById(R.id.add_button);
        Back = (Button) findViewById(R.id.back);
        Logout = (Button) findViewById(R.id.logout);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course = Course.getText().toString();
                String trainerno = TrainerNo.getText().toString();
                String plan = Plan.getText().toString();
                SettersGetters contact = new SettersGetters();
                contact.setCourse(course);
                contact.setTrainerNo(trainerno);
                contact.setPlan(plan);
                if (course.length()>0 && trainerno.length()>0 && plan.length()>0) {
                    database.insertIntoDB(course, trainerno, plan);
                    Toast.makeText(NewCourseActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NewCourseActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(NewCourseActivity.this,"Fill up all fields", Toast.LENGTH_LONG).show();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewCourseActivity.this, EnrollmentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewCourseActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

class DatabaseCourse extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Course.db";
    private static final int DATABASE_VERSION = 1;
    public static final String table = "Course";
    public static final String course_table = "Course";
    public static final String trainerno_table = "TrainerNO";
    public static final String plan_table = "Plan";

    public DatabaseCourse(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    public void insertIntoDB(String course, String trainerno, String plan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(course_table, course);
        contentValues.put(trainerno_table, trainerno);
        contentValues.put(plan_table, plan);
        db.insert(table, null, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table + "(" +
                course_table + " VARCHAR NOT NULL, " +
                trainerno_table + " INTEGER NOT NULL, " +
                plan_table + " VARCHAR NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }
}