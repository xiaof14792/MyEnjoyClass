package com.enjoy.enjoyclass03;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @InjectView(R.id.text)
    TextView textView;

    @AutoWired()
    String name;
    @AutoWired("female")
    boolean isFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            InjectUtils.injectView(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        textView.setText("SecondActivity!!!");

        /*Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        isFemale = bundle.getBoolean("isFemale");*/

        InjectUtils.autoWired(this);
        String result = "name: " + name + "; isFemale: " + isFemale;
        textView.setText(result);
    }
}