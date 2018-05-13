package com.example.user.threadsex;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

public class AsyncTaskActivity extends AsyncTask<Object, Integer, Void> {

    private String TAG = "AsyncTask";
    @SuppressLint("StaticFieldLeak")
    private TextView textView;


    AsyncTaskActivity(TextView textView)
    {
        Log.i(TAG,"ConstructorAsync");
        this.textView = textView;
    }

    @Override
    protected Void doInBackground(Object[] voids) {
        Log.i(TAG,"doInBackgroundAsync");
        for(int i = 1; i < 11; i++)
        {
            if (isCancelled()) {
                break;
            }
            publishProgress(i);
            SystemClock.sleep(500);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.i(TAG,"onPreExecuteAsync");
        super.onPreExecute();
        textView.setText("");
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.i(TAG, "onProgressUpdateAsync");
        super.onProgressUpdate(values);
        String curr = String.valueOf(values[0]);
        textView.setText(curr);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.i(TAG,"onCanceledAsync");
        textView.setText("");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.i(TAG, "onPostExecuteAsync");
        super.onPostExecute(aVoid);
        textView.setText("Done");
    }
}
