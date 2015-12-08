package com.matheusfroes.unit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.matheusfroes.unit.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class ChooseQuizModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_quiz_mode);

        FancyButton soloButton = (FancyButton) findViewById(R.id.solo_button);

        soloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseQuizModeActivity.this, QuizActivity.class));
            }
        });
    }
}
