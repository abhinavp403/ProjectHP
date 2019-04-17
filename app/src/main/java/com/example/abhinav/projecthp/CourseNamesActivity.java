package com.example.abhinav.projecthp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class CourseNamesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv;
    private ListAdapter adapter;
    private ArrayList<SettersGetters> settersgetters;
    private Button Back, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_names);

        lv = (ListView) findViewById(R.id.listview);
        lv.setOnItemClickListener(this);
        settersgetters = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item, populateList());
        lv.setAdapter(adapter);
        Back = (Button) findViewById(R.id.back);
        Logout = (Button) findViewById(R.id.logout);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseNamesActivity.this, LandingPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseNamesActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public List<String> populateList(){
        List<String> NamesList = new ArrayList<>();
        DatabaseCourse openHelperClass = new DatabaseCourse(this);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
        Cursor cursor = sqliteDatabase.query(DatabaseCourse.table, null, null, null, null, null, null);
        startManagingCursor(cursor);
        while (cursor.moveToNext()) {
            String course = cursor.getString(cursor.getColumnIndex(DatabaseCourse.course_table));
            String trainerno = cursor.getString(cursor.getColumnIndex(DatabaseCourse.trainerno_table));
            String plan = cursor.getString(cursor.getColumnIndex(DatabaseCourse.plan_table));
            SettersGetters sg = new SettersGetters();
            sg.setCourse(course);
            sg.setTrainerNo(trainerno);
            sg.setPlan(plan);
            settersgetters.add(sg);
            NamesList.add(course);
        }
        sqliteDatabase.close();
        return NamesList;
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new ArrayAdapter(this, R.layout.customlistview, populateList());
        lv.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(CourseNamesActivity.this, CourseDetailsActivity.class);
        SettersGetters clickedObject =  settersgetters.get(arg2);
        Bundle dataBundle = new Bundle();
        dataBundle.putString("Course", clickedObject.getCourse());
        dataBundle.putString("TrainerNo", clickedObject.getTrainerNo());
        dataBundle.putString("Plan", clickedObject.getPlan());
        intent.putExtras(dataBundle);
        startActivity(intent);
    }
}