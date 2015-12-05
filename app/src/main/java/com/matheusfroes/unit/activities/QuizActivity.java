package com.matheusfroes.unit.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.matheusfroes.unit.R;
import com.matheusfroes.unit.fragments.QuestionControllerFragment;
import com.matheusfroes.unit.fragments.QuestionFragment;
import com.matheusfroes.unit.fragments.TimerFragment;


public class QuizActivity extends Activity implements QuestionControllerFragment.QuestionControllerListener, TimerFragment.TimerFragmentListener {
    private QuestionFragment questionFragment;
    private FragmentTransaction ft;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_quiz);

        fm = getFragmentManager();
        questionFragment = new QuestionFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.questions, questionFragment, "Question Fragment");
        ft.commit();

    }

    @Override
    public void onCancelButtonClicked() {
        AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sair");
        builder.setMessage("Tem certeza que deseja sair?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
                questionFragment.sendResult(TimerFragment.currentTime);
                finish();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onNextQuestionButtonClicked() {
        ft = fm.beginTransaction();
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        if (questionFragment.checkAnswer()) {
            questionFragment.nextQuestion();
        }
        ft.commit();
        //questionFragment.uncheckRadioButtons();
//        if (questionFragment.getRadioGroup().getCheckedRadioButtonId() == -1) {
//            makeToast("Você precisa selecionar uma alternativa");
//        } else {
//            ft = fm.beginTransaction();
//            ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
//            //questionFragment.checkAnswer();
//            questionFragment.nextQuestion();
//            //questionFragment.uncheckRadioButtons();
//            ft.commit();
//        }
    }

    public void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTimerFinish(final String finishedTime) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);

        builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                questionFragment.sendResult(finishedTime);
            }
        });

        builder.setMessage("Acabou o tempo!");

        Dialog dialog = builder.create();
        dialog.show();
    }
}