package com.example.user.threadsex;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyAsyncTask extends AppCompatActivity {

    private AsyncTask asyncTask;
    private TextView textView;
    private String TAG = "AsyncActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_activity);
        Log.i(TAG, "onCreateAsync");

        textView = findViewById(R.id.TextNum);

        Button create = findViewById(R.id.CreateButton);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickCreateAsync");
                asyncTask = new AsyncTaskActivity(textView);
            }
        });

        Button cancel = findViewById(R.id.CancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickCancelAsync");
                asyncTask.cancel(true);
            }
        });

        Button start = findViewById(R.id.StartButton);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickStartAsync");
                asyncTask.execute();
            }
        });

    }

}
