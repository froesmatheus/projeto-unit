package com.matheusfroes.unit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.matheusfroes.unit.R;
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
        TextView rightQuestions = (TextView) findViewById(R.id.right_questions);
        TextView scoreTxt = (TextView) findViewById(R.id.score_txt);

        quizTime.setText(getResources().getString(R.string.quiz_time, timeStr));
        rightQuestions.setText(getResources().getString(R.string.right_questions, (score / 10)));
        scoreTxt.setText(getResources().getString(R.string.score, score));

        ImageView resultImage = (ImageView) findViewById(R.id.result_image);

        int ratingImage;
        if (score < 40) {
            ratingImage = R.drawable.bad_rating;
        } else if (score < 80) {
            ratingImage = R.drawable.average_rating;
        } else {
            ratingImage = R.drawable.good_rating;
        }

        resultImage.setImageResource(ratingImage);

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
