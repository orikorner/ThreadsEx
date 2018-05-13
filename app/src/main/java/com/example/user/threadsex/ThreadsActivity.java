package com.example.user.threadsex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThreadsActivity extends AppCompatActivity {

    private String TAG = "ThreadsActivity";
    private TextView textView;
    private Thread workerThread;
    private Handler handler;
    private Boolean canStart = false;


    @SuppressLint("HandlerLeak")
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_activity);
        Log.i(TAG, "onCreateThread");

        textView = findViewById(R.id.TextNum);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.i(TAG, "handleMessage");
                String num = String.valueOf(msg.arg1);
                if (msg.arg1 != -1)
                    textView.setText(num);
                else
                    textView.setText("Done");
            }
        };


        Button create = findViewById(R.id.CreateButton);
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickCreateThread");
                workerThread = new Thread(new ThreadWorker());
                workerThread.start();
            }
        });

        Button cancel = findViewById(R.id.CancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickCancelThread");
                canStart = false;
                textView.setText("");
            }
        });

        Button start = findViewById(R.id.StartButton);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i(TAG, "onClickStartThread");
                textView.setText("");
                canStart = true;
            }
        });
    }

    public class ThreadWorker implements Runnable {

        private String TAG = "WorkerThread";
        @Override
        public void run() {
            Log.i(TAG, "run");
            int i = 1;
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (canStart) {
                        Log.i(TAG, String.valueOf(i));

                        Message msg = Message.obtain();
                        msg.arg1 = i;
                        handler.sendMessage(msg);

                        Thread.sleep(500);

                        if (i == 10) {
                            Log.i(TAG, "WorkerFinished");
                            canStart = false;

                            Message msg2 = Message.obtain();
                            msg2.arg1 = -1;
                            handler.sendMessage(msg2);

                            break;
                        } else if (!canStart) {
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "WorkerDied");
        }
    }
}
