package com.example.user.threadsex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class AsyncTaskActivity extends AsyncTask<Object, Integer, Void> {

    private String TAG = "AsyncTask";
    @SuppressLint("StaticFieldLeak")
    private View view;
    @SuppressLint("StaticFieldLeak")
    private TextView textView;


    AsyncTaskActivity(View view, TextView textView)
    {
        this.view = view;
        this.textView = textView;
    }

    @Override
    protected Void doInBackground(Object[] voids) {
        Log.i(TAG,"doInBackground");
        for(int i =0; i < 10; i++)
        {
            if (isCancelled())
                break;
            publishProgress(i);
            SystemClock.sleep(500);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        view.findViewById(R.id.AsyncTaskActivity).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.ThreadsActivity).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.CancelButton).setVisibility(View.VISIBLE);
        view.findViewById(R.id.CreateButton).setVisibility(View.VISIBLE);
        view.findViewById(R.id.StartButton).setVisibility(View.VISIBLE);
        view.findViewById(R.id.TextNum).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.i(TAG, "onProgressUpdate");
        super.onProgressUpdate(values);
        String curr = String.valueOf(values[0]);
        textView.setText(curr);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.i(TAG, "onPostExecute");
        super.onPostExecute(aVoid);
        view.findViewById(R.id.AsyncTaskActivity).setVisibility(View.VISIBLE);
        view.findViewById(R.id.ThreadsActivity).setVisibility(View.VISIBLE);
        view.findViewById(R.id.CancelButton).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.CreateButton).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.StartButton).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.TextNum).setVisibility(View.INVISIBLE);
    }
}
