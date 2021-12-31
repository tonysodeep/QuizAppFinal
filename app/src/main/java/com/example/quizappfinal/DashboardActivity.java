package com.example.quizappfinal;

import static com.example.quizappfinal.SplashActivity.list;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.quizappfinal.models.ModelClass;
import com.example.quizappfinal.services.MyService;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timerValue = 20;
    RoundedHorizontalProgressBar progressBar;
    List<ModelClass> allQuestionList;
    ModelClass modelClass;
    int index = 0;
    TextView card_question, optionA, optionB, optionC, optionD;
    CardView cardOfA, cardOfB, cardOfC, cardOfD;
    int correctCount = 0;
    int wrongCount = 0;
    LinearLayout nextBtn;
    TextView exitText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        startService(new Intent(this, MyService.class));
        hocks();

        allQuestionList = list;
        Collections.shuffle(allQuestionList);
        modelClass = list.get(index);

        cardOfA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOfB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOfC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOfD.setBackgroundColor(getResources().getColor(R.color.white));

        nextBtn.setClickable(false);

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                //custom layout
                Dialog dialog = new Dialog(DashboardActivity.this);
                WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                lp.dimAmount = 0.7f;
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.CENTER);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.setCancelable(false);
                dialog.findViewById(R.id.bt_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, SplashActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                Log.d("AAA", "finished");

                dialog.show();
            }
        }.start();
        setAllData();
        exitText = findViewById(R.id.ic_exit);
        exitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setAllData() {
        card_question.setText(modelClass.getQuestion());
        optionA.setText(modelClass.getoA());
        optionB.setText(modelClass.getoB());
        optionC.setText(modelClass.getoC());
        optionD.setText(modelClass.getoD());
        timerValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void hocks() {
        progressBar = findViewById(R.id.quiz_timer);
        card_question = findViewById(R.id.card_question);
        optionA = findViewById(R.id.card_optionA);
        optionB = findViewById(R.id.card_optionB);
        optionC = findViewById(R.id.card_optionC);
        optionD = findViewById(R.id.card_optionD);

        cardOfA = findViewById(R.id.card_a);
        cardOfB = findViewById(R.id.card_b);
        cardOfC = findViewById(R.id.card_c);
        cardOfD = findViewById(R.id.card_d);

        nextBtn = findViewById(R.id.bt_next);
    }

    public void correct(CardView card) {
        card.setBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                index++;
                modelClass = list.get(index);
                resetColor();
                setAllData();
                enableButton();
            }
        });


    }

    public void wrong(CardView card) {
        Log.d("AAA", "error");
        card.setBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if (index < list.size() - 1) {
                    index++;
                    modelClass = list.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                } else {
                    gameWon();
                }
            }
        });

    }

    private void gameWon() {
        Intent intent = new Intent(DashboardActivity.this, WonActivity.class);
        intent.putExtra("correct", correctCount);
        intent.putExtra("wrong", wrongCount);
        intent.putExtra("total", list.size());
        startActivity(intent);
        finish();
    }

    public void enableButton() {
        cardOfA.setClickable(true);
        cardOfB.setClickable(true);
        cardOfC.setClickable(true);
        cardOfD.setClickable(true);
    }

    public void disableButton() {
        cardOfA.setClickable(false);
        cardOfB.setClickable(false);
        cardOfC.setClickable(false);
        cardOfD.setClickable(false);
    }

    public void resetColor() {
        cardOfA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOfB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOfC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOfD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionAClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        countDownTimer.cancel();
        if (modelClass.getoA().equals(modelClass.getAns())) {
            cardOfA.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {
                correct(cardOfA);

            } else {
                gameWon();
            }
        } else {
            wrong(cardOfA);
        }
    }

    public void OptionBClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        countDownTimer.cancel();
        if (modelClass.getoB().equals(modelClass.getAns())) {
            cardOfB.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {
                correct(cardOfB);

            } else {
                gameWon();
            }
        } else {
            wrong(cardOfB);
        }
    }

    public void OptionCClick(View view) {
        Log.d("AAA", "click A");
        disableButton();
        nextBtn.setClickable(true);
        countDownTimer.cancel();
        if (modelClass.getoC().equals(modelClass.getAns())) {

            cardOfC.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {
                correct(cardOfC);
            } else {
                gameWon();
            }
        } else {
            wrong(cardOfC);
        }
    }

    public void OptionDClick(View view) {
        disableButton();
        nextBtn.setClickable(true);
        countDownTimer.cancel();
        if (modelClass.getoD().equals(modelClass.getAns())) {
            cardOfD.setCardBackgroundColor(getResources().getColor(R.color.green));
            if (index < list.size() - 1) {
                correct(cardOfD);
            } else {
                gameWon();
            }
        } else {
            wrong(cardOfD);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this,MyService.class));
    }
}