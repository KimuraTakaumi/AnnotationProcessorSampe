package com.hatenablog.techium.annotation.processor.sampe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hatenablog.techium.annotation.processor.CustomAnnotation;
import com.hatenablog.techium.annotation.processor.generated.GeneratedClass;

@CustomAnnotation
public class MainActivity extends AppCompatActivity {

    @CustomAnnotation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GeneratedClass gen = new GeneratedClass();
        Log.d("MainActivity", gen.getMessage());
    }
}
