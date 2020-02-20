package com.example.lab4milestone1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button buttonStartThread;

    private  volatile boolean stopThread = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStartThread = findViewById(R.id.startButt);
    }

    public void startThread(View view)
    {
        stopThread = false;
        final ExampleRunnable runnable = new ExampleRunnable(10);
        Thread thread = new Thread(runnable) {
            @Override
            public void run(){
                for(int i = 0; i< runnable.seconds; i++)
                {
                    if(stopThread)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonStartThread.setText("Start");
                            }
                        });
                        return;
                    }
                    if(i == 5){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                buttonStartThread.setText("50%");
                            }
                        });
                    }
                    Log.d(TAG,"startThread: " + i);
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        buttonStartThread.setText("Start");
                    }
                });
            }
        };
        thread.start();
    }
    public void stopTread(View view)
    {
        stopThread = true;
    }

}
