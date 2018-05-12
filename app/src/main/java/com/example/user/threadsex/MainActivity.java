package com.example.user.threadsex;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Main";
    private AsyncTask asyncTask;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View mainView = findViewById(R.id.mainView);
        textView = findViewById(R.id.TextNum);

        Button asyncButton = findViewById(R.id.AsyncTaskActivity);
        asyncButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                asyncTask = new AsyncTaskActivity(mainView, textView);
                asyncTask.execute();
            }
        });

        Button threadButton = findViewById(R.id.ThreadsActivity);
        threadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
    }

    @SuppressLint("SetTextI18n")
    protected void onProgressUpdate(Integer... progress) {
        Log.i(TAG,"doInBackground");
        String currCount = String.valueOf(progress[0]);
        textView.setText(currCount);
    }
}