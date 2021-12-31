package com.example.quizappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class WonActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView textView;
    int correct, wrong, total;
    LinearLayout btShare;
    TextView exitText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        //passed them cai tong so cau hoi
        correct = getIntent().getIntExtra("correct", 0);
        wrong = getIntent().getIntExtra("wrong", 0);
        total = getIntent().getIntExtra("total", 0);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        textView = findViewById(R.id.result_text);
        btShare = findViewById(R.id.bt_share);
        exitText = findViewById(R.id.ic_exit);

        circularProgressBar.setProgress(correct);
        //set cung /20
        //thay /20 thay la cai list.size();
        textView.setText(correct + " / " + total);

        btShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My ");
                    String shareMessage = "\n i got " + correct + " Out of " + total + " you can also try";
                    shareMessage = shareMessage + "https://play.google.com/store/app/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {

                }
            }
        });
        exitText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WonActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}