package com.example.abhinav.projecthp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread th = new Thread(){
            public void run(){
                try{
                    sleep(3500);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally{
                    onPause();
                    startActivity(new Intent(MainActivity.this,LoginScreenActivity.class));
                }
            }
        };
        th.start();
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}