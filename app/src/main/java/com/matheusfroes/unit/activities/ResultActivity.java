package com.matheusfroes.unit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.matheusfroes.unit.R;
import com.matheusfroes.unit.adapters.RightAlternativeAdapter;
import com.matheusfroes.unit.fragments.QuestionFragment;

import java.text.NumberFormat;

import mehdi.sakout.fancybuttons.FancyButton;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    private NumberFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        format = NumberFormat.getInstance();
        format.setMinimumIntegerDigits(2);

        int score = getIntent().getIntExtra("score", -1);

        long time = 0;
        if (getIntent().getStringExtra("time") != null)
            time = Long.valueOf(getIntent().getStringExtra("time"));

        time -= 300000;

        String timeStr = format.format(Math.abs((time / 60000) % 60)) + ":" + format.format(Math.abs((time / 1000) % 60));

        TextView quizTime = (TextView) findViewById(R.id.quiz_time);
        TextView scoreTxt = (TextView) findViewById(R.id.score_txt);
        ListView rightAlternativesLv = (ListView) findViewById(R.id.list);

        if (QuestionFragment.rightAlternatives.size() != 0) {
            RightAlternativeAdapter adapter = new RightAlternativeAdapter(this, QuestionFragment.rightAlternatives);

            rightAlternativesLv.setAdapter(adapter);
        }


        quizTime.setText(getResources().getString(R.string.quiz_time, timeStr));
        scoreTxt.setText(getResources().getString(R.string.score, score));


        FancyButton playAgainButton = (FancyButton) findViewById(R.id.play_again_button);
        playAgainButton.setOnClickListener(this);

        FancyButton exitButton = (FancyButton) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);

        FancyButton wrongAnswersButton = (FancyButton) findViewById(R.id.see_wrong_answers);
        wrongAnswersButton.setOnClickListener(this);

        if (QuestionFragment.wrongQuestionList.size() == 0) {
            wrongAnswersButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;

        switch (v.getId()) {

            case R.id.play_again_button:
                intent = new Intent(getApplicationContext(), QuizActivity.class);
                finish();
                startActivity(intent);
                break;
            case R.id.exit_button:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                finish();
                startActivity(intent);
                break;
            case R.id.see_wrong_answers:
                intent = new Intent(this, WrongAnswersActivity.class);
                startActivity(intent);
                break;
        }
    }
}
