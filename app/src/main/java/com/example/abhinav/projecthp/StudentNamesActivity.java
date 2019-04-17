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

public class StudentNamesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lv;
    private ListAdapter adapter;
    private ArrayList<SettersGetters> settersgetters;
    private Button Back, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_names);

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
                Intent intent = new Intent(StudentNamesActivity.this, LandingPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentNamesActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public List<String> populateList(){
        List<String> NamesList = new ArrayList<>();
        DatabaseStudent openHelperClass = new DatabaseStudent(this);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
        Cursor cursor = sqliteDatabase.query(DatabaseStudent.table, null, null, null, null, null, null);
        startManagingCursor(cursor);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseStudent.name_table));
            String course = cursor.getString(cursor.getColumnIndex(DatabaseStudent.course_table));
            String phone = cursor.getString(cursor.getColumnIndex(DatabaseStudent.phone_table));
            String semester = cursor.getString(cursor.getColumnIndex(DatabaseStudent.semester_table));
            String plan = cursor.getString(cursor.getColumnIndex(DatabaseStudent.plan_table));
            String date = cursor.getString(cursor.getColumnIndex(DatabaseStudent.date_table));
            SettersGetters sg = new SettersGetters();
            sg.setName(name);
            sg.setCourse(course);
            sg.setPhone(phone);
            sg.setSemester(semester);
            sg.setPlan(plan);
            sg.setDate(date);
            settersgetters.add(sg);
            NamesList.add(name);
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
        Intent intent = new Intent(StudentNamesActivity.this, StudentDetailsActivity.class);
        SettersGetters clickedObject =  settersgetters.get(arg2);
        Bundle dataBundle = new Bundle();
        dataBundle.putString("Name", clickedObject.getName());
        dataBundle.putString("Course", clickedObject.getCourse());
        dataBundle.putString("Phone", clickedObject.getPhone());
        dataBundle.putString("Semester", clickedObject.getSemester() );
        dataBundle.putString("Plan", clickedObject.getPlan());
        dataBundle.putString("Date", clickedObject.getDate());
        intent.putExtras(dataBundle);
        startActivity(intent);
    }
}