package com.example.user.threadsex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Main";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button asyncButton = findViewById(R.id.AsyncTaskActivity);
        asyncButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickAsync");
                Intent myIntent = new Intent(MainActivity.this, MyAsyncTask.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button threadButton = findViewById(R.id.ThreadsActivity);
        threadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickThread");
                Intent myIntent = new Intent(MainActivity.this, ThreadsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

}