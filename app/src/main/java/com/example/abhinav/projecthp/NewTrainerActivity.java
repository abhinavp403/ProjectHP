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

public class NewTrainerActivity extends AppCompatActivity {
    EditText Name, Course, Phone, College, WorkPlace, Experience;
    Button AddButton, Back, Logout;
    DatabaseTrainer database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trainer);

        database = new DatabaseTrainer(NewTrainerActivity.this);
        Name = (EditText) findViewById(R.id.name);
        Course = (EditText) findViewById(R.id.course);
        Phone = (EditText) findViewById(R.id.phone);
        College = (EditText) findViewById(R.id.college);
        WorkPlace = (EditText) findViewById(R.id.workplace);
        Experience = (EditText) findViewById(R.id.experience);
        AddButton = (Button) findViewById(R.id.add_button);
        Back = (Button) findViewById(R.id.back);
        Logout = (Button) findViewById(R.id.logout);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String course = Course.getText().toString();
                String phone = Phone.getText().toString();
                String college = College.getText().toString();
                String workplace = WorkPlace.getText().toString();
                String exp = Experience.getText().toString();
                SettersGetters contact = new SettersGetters();
                contact.setName(name);
                contact.setCourse(course);
                contact.setCollege(college);
                contact.setPhone(phone);
                contact.setWorkPlace(workplace);
                contact.setExperience(exp);
                if (name.length() > 0 && course.length()>0 && phone.length()>0 && college.length()>0 && workplace.length()>0 && exp.length()>0) {
                    database.insertIntoDB(name, course, phone, college, workplace, exp);
                    Toast.makeText(NewTrainerActivity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(NewTrainerActivity.this, LandingPageActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                    Toast.makeText(NewTrainerActivity.this,"Fill up all fields", Toast.LENGTH_LONG).show();
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTrainerActivity.this, EnrollmentActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewTrainerActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

class DatabaseTrainer extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Trainer.db";
    private static final int DATABASE_VERSION = 1;
    public static final String table = "Trainer";
    public static final String name_table = "Name";
    public static final String course_table = "Course";
    public static final String phone_table= "Phone";
    public static final String college_table = "College";
    public static final String workplace_table = "WorkPlace";
    public static final String exp_table = "Experience";

    public DatabaseTrainer(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    public void insertIntoDB(String name, String course, String phone, String college, String workplace, String exp){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name_table, name);
        contentValues.put(course_table, course);
        contentValues.put(phone_table, phone);
        contentValues.put(college_table, college);
        contentValues.put(workplace_table, workplace);
        contentValues.put(exp_table, exp);
        db.insert(table, null, contentValues);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table + "(" +
                name_table + " TEXT NOT NULL, " +
                course_table + " VARCHAR NOT NULL, " +
                phone_table + " INTEGER NOT NULL, " +
                college_table + " VARCHAR NOT NULL, " +
                workplace_table + " VARCHAR NOT NULL, " +
                exp_table + " INTEGER NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }
}