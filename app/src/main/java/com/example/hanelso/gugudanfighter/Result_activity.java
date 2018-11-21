package com.example.hanelso.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result_activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int correctAnswer = intent.getIntExtra("data-correctAnswer", -1);
        int questionCount = intent.getIntExtra("data-questionCount", -1);

        TextView textView = findViewById(R.id.textViewResultCorrectAnswerCount);
        textView.setText(Integer.toString(correctAnswer));
        TextView textView2 = findViewById(R.id.textViewResultQuestionCount);
        textView2.setText(Integer.toString(questionCount));
        findViewById(R.id.buttonResultNo).setOnClickListener(this);
        findViewById(R.id.buttonResultYes).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.buttonResultNo:
            {
                finish();
                break;
            }

            case R.id.buttonResultYes:
            {
                Intent intent = new Intent(Result_activity.this,Prob_activity.class);
                finish();
                startActivity(intent);
                break;
            }
        }
    }
}
