package com.ives.jlogapp;

import android.app.Activity;
import android.os.Bundle;

import com.ives.jlog.JLog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        someLogs();
    }
    private void someLogs(){
        String aa = "a string here";


        JLog.d(2, "sf", aa);
    }
}
