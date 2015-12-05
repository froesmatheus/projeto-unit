package com.matheusfroes.unit.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.matheusfroes.unit.R;

/**
 * Created by Matheus Fróes on 11/08/2015.
 */
public class QuestionControllerFragment extends Fragment implements View.OnClickListener {
    private Button clearButton, nextQuestionButton;
    private QuestionControllerListener listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if ((activity instanceof QuestionControllerListener)) {
            listener = (QuestionControllerListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_controllers, container, false);
        clearButton = (Button) view.findViewById(R.id.cancelButton);
        clearButton.setOnClickListener(this);
        nextQuestionButton = (Button) view.findViewById(R.id.nextQuestionButton);
        nextQuestionButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                listener.onCancelButtonClicked();
                break;
            case R.id.nextQuestionButton:
                listener.onNextQuestionButtonClicked();
                break;
        }
    }

    public interface QuestionControllerListener {
        void onCancelButtonClicked();

        void onNextQuestionButtonClicked();
    }
}

