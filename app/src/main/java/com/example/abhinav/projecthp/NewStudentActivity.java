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

public class NewStudentActivity extends AppCompatActivity {
    EditText Name, Course, Phone, Semester, Plan, Date;
    Button AddButton, Back, Logout;
    DatabaseStudent database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        database = new DatabaseStudent(NewStudentActivity.this);
        Name = (EditText) findViewById(R.id.name);
        Course = (EditText) findViewById(R.id.course);
        Phone = (EditText) findViewById(R.id.phone);
        Semester = (EditText) findViewById(R.id.semester);
        Plan = (EditText) findViewById(R.id.plan);
        Date = (EditText) findViewById(R.id.doj);
        AddButton = (Button) findViewById(R.id.add_button);
        Back = (Button) findViewById(R.id.back);
        Logout = (Button) findViewById(R.id.logout);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String course = Course.getText().toString();
                String phone = Phone.getText().toString();
                String semester = Semester.getText().toString();
                String plan = Plan.getText().toString();
                String date = Date.getText().toString();
                SettersGetters contact = new SettersGetters();
                contact.setName(name);
                contact.setCourse(course);
                contact.setSemester(semester);
                contact.setPhone(phone);
                contact.setPlan(plan);
                contact.setDate(date);
                if (name.length() > 0 && course.length()>0 && phone.length()>0 && semester.length()>0 && plan.length()>0 && date.length()>0) {
                    database.insertIntoDB(name, course, phone, semester, plan, date);
                    Toast.makeText(NewStudentActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NewStudentActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(NewStudentActivity.this,"Fill up all fields", Toast.LENGTH_LONG).show();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewStudentActivity.this, EnrollmentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewStudentActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

class DatabaseStudent extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Student.db";
    private static final int DATABASE_VERSION = 1;
    public static final String table = "Student";
    public static final String name_table = "Name";
    public static final String course_table = "Course";
    public static final String phone_table= "Phone";
    public static final String semester_table = "Semester";
    public static final String plan_table = "Plan";
    public static final String date_table = "Date";

    public DatabaseStudent(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    public void insertIntoDB(String name, String course, String phone, String semester, String plan, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name_table, name);
        contentValues.put(course_table, course);
        contentValues.put(phone_table, phone);
        contentValues.put(semester_table, semester);
        contentValues.put(plan_table, plan);
        contentValues.put(date_table, date);
        db.insert(table, null, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table + "(" +
                name_table + " TEXT NOT NULL, " +
                course_table + " VARCHAR NOT NULL, " +
                phone_table + " INTEGER NOT NULL, " +
                semester_table + " INTEGER NOT NULL, " +
                plan_table + " VARCHAR NOT NULL, " +
                date_table + " VARCHAR NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }
}