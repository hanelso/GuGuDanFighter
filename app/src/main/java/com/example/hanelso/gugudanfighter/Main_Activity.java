package com.example.hanelso.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main_Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // inflate
        setContentView(R.layout.activity_main);

        // make button objects!
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.history).setOnClickListener(this);
        findViewById(R.id.help).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.start: {
                Intent intent = new Intent(Main_Activity.this, Prob_activity.class);
                startActivity(intent);
                break;
            }

            case R.id.history: {
                Intent intent = new Intent(Main_Activity.this, Hist_activity.class);
                startActivity(intent);
                break;
            }

            case R.id.help: {
                Intent intent = new Intent(Main_Activity.this, Help_activity.class);
                startActivity(intent);
                break;
            }

            default: {
                break;
            }
        }
    }
}
