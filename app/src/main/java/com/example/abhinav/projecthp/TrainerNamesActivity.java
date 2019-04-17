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

public class TrainerNamesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView lv;
    private ListAdapter adapter;
    private ArrayList<SettersGetters> settersgetters;
    private Button Back, Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_names);

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
                Intent intent = new Intent(TrainerNamesActivity.this, LandingPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrainerNamesActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public List<String> populateList(){
        List<String> NamesList = new ArrayList<>();
        DatabaseTrainer openHelperClass = new DatabaseTrainer(this);
        SQLiteDatabase sqliteDatabase = openHelperClass.getReadableDatabase();
        Cursor cursor = sqliteDatabase.query(DatabaseTrainer.table, null, null, null, null, null, null);
        startManagingCursor(cursor);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(DatabaseTrainer.name_table));
            String course = cursor.getString(cursor.getColumnIndex(DatabaseTrainer.course_table));
            String phone = cursor.getString(cursor.getColumnIndex(DatabaseTrainer.phone_table));
            String college = cursor.getString(cursor.getColumnIndex(DatabaseTrainer.college_table));
            String workplace = cursor.getString(cursor.getColumnIndex(DatabaseTrainer.workplace_table));
            String exp = cursor.getString(cursor.getColumnIndex(DatabaseTrainer.exp_table));
            SettersGetters sg = new SettersGetters();
            sg.setName(name);
            sg.setCourse(course);
            sg.setPhone(phone);
            sg.setCollege(college);
            sg.setWorkPlace(workplace);
            sg.setExperience(exp);
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
        Intent intent = new Intent(TrainerNamesActivity.this, TrainerDetailsActivity.class);
        SettersGetters clickedObject =  settersgetters.get(arg2);
        Bundle dataBundle = new Bundle();
        dataBundle.putString("Name", clickedObject.getName());
        dataBundle.putString("Course", clickedObject.getCourse());
        dataBundle.putString("Phone", clickedObject.getPhone());
        dataBundle.putString("College", clickedObject.getCollege() );
        dataBundle.putString("WorkPlace", clickedObject.getWorkPlace());
        dataBundle.putString("Experience", clickedObject.getExperience());
        intent.putExtras(dataBundle);
        startActivity(intent);
    }
}