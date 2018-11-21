package com.example.hanelso.gugudanfighter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Prob_activity extends AppCompatActivity implements View.OnClickListener {

    private static final int TIME_LIMIT = 20;

    private int correctAnswer = 0;
    private int getCorrectAnswer = 0;
    private int questionCount = 0;

    private TextView text;
    private TextView time;

    private TextView rndQuesNum;
    int rnd1 = randomize(2,8);
    int rnd2 = randomize(1,9);

    private int[] answerBtnIds = {
            R.id.answer1,R.id.answer2,R.id.answer3,
            R.id.answer4,R.id.answer5,R.id.answer6,
            R.id.answer7,R.id.answer8,R.id.answer9,
    };

    private Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // inflate
        setContentView(R.layout.activity_prob);

        // View 찾기
        time = findViewById(R.id.timer);
        text = findViewById(R.id.count);

        // ui 초기화(여기가 메인 쓰레드)
        updateLastTime(TIME_LIMIT);
        updateScore(getCorrectAnswer, questionCount);

        // 타이머 시작 - 1초 뒤부터 1초마다 run이 계속 실행됨
        timer.schedule(new GamePlayTimerTask(), 1000, 1000);

        rndQuesSetting();
        rndAnswerSetting();

        for(int i=0;i<9;i++)
        {
            Button btn = findViewById(answerBtnIds[i]);
            btn.setOnClickListener(this);
        }

    }

    private int randomize(int from, int to){
        return (int)(Math.random() * to) + from;
    }

    private void rndQuesSetting(){
        rndQuesNum = findViewById(R.id.question);

        rndQuesNum.setText("" + rnd1 + " X " + rnd2 + " = ?");
    }

    private void rndAnswerSetting() {
        int[] data = new int[9];

        for(int i=0;i<9;i++){
            Button btn = findViewById(answerBtnIds[i]);

            int val = randomize(2,8) * randomize(1,9);
            data[i] = val;

            if(val == rnd1 * rnd2){
                i--;
                continue;
            }
            for(int j=0;j<i; j++){
                if(val == data[j])
                    i--; break;
            }
            btn.setText(Integer.toString(val));
        }
        Button answer = findViewById(answerBtnIds[randomize(0,8)]);
        answer.setText(Integer.toString(rnd1 * rnd2));
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button)view;
        String str = btn.getText().toString();
        int val = Integer.parseInt(str);

        int answer = rnd1 * rnd2;
        if(val == answer)
            correctAnswer++;
        questionCount++;

        // 다시 초기화 해주는 과정
        updateScore(correctAnswer, questionCount);
        rnd1 = randomize(2,8);
        rnd2 = randomize(1,9);
        rndQuesSetting();
        rndAnswerSetting();
    }


    private void updateScore(int correctAnswer, int questionCount) {

        // 문제 출제 될때마다 questionCount++ 해주는 if문 작성
        // 맞는 버튼(답)을 누를 때에만 getCorrectAnswer++ 해주는 if문도 작성

        text.setText("현황 : "+ correctAnswer + "/" + questionCount);
    }


    private void updateLastTime(int lastTime) {

        time.setText("남은시간 : " + lastTime);
    }


    private class GamePlayTimerTask extends TimerTask {

        private int seconds = 0;

        @Override
        public void run() {
            if(seconds >= TIME_LIMIT){
                // 게임 종료 단계

                // 1. time stop
                timer.cancel();

                // 2. result activity 이동
                Intent intent = new Intent(Prob_activity.this,Result_activity.class);
                intent.putExtra("data-correctAnswer",correctAnswer);
                intent.putExtra("data-questionCount",questionCount);
                setResult(1,intent);
                startActivity(intent);

                // 3. activity 종료
                finish();
                return;
            }
            seconds++;

            // ui 변경은 메인 쓰레드에서 해야함.
            runOnUiThread(new Runnable(){

                @Override
                public void run() {
                    updateLastTime(TIME_LIMIT - seconds);
                }
            });

        }
    }
}


//    public class DataSet {
//        int L_val;
//        int R_val;
//        int[] result = new int[9];
//        int index;
//        int answer;
//
//        public DataSet() {
//            Random rand = new Random();
//            L_val = rand.nextInt(9) + 1;
//            R_val = rand.nextInt(9) + 1;
//
//            for (int i = 0; i < 9; i++) {
//                int val = (rand.nextInt(9) + 1) * (rand.nextInt(9) + 1);
//                result[i] = val;
//                if (val == L_val * R_val) {
//                    i--;
//                    continue;
//                }
//                for (int j = 0; j < i; j++)
//                    if (val == result[j]) {
//                        i--;
//                        break;
//                    }
//            }
//
//
//            index = rand.nextInt(9);
//            answer = L_val * R_val;
//            result[index] = answer;
//
//        }
//    }