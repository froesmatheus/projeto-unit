package com.matheusfroes.unit.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.matheusfroes.unit.R;
import com.matheusfroes.unit.activities.ResultActivity;
import com.matheusfroes.unit.adapters.AlternativeAdapter;
import com.matheusfroes.unit.db.QuestionDAO;
import com.matheusfroes.unit.model.Question;
import com.matheusfroes.unit.model.WrongQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {
    private Question question;
    private TextView questionTitle;
    private TextView quizCounter;
    private QuestionDAO dao;
    private List<Question> questionList = new ArrayList<>();
    List<String> currentAlternatives = new ArrayList<>();
    private AlternativeAdapter adapter;
    private int index = 0;
    private int questionNumber = 1;
    private int score = 0;
    public static List<WrongQuestion> wrongQuestionList;

    public QuestionFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.questions_layout, container, false);

        questionTitle = (TextView) view.findViewById(R.id.questionTitle);
        quizCounter = (TextView) view.findViewById(R.id.quiz_counter);

        ListView alternatives = (ListView) view.findViewById(R.id.list);
        dao = new QuestionDAO(getActivity());

        questionList = dao.getQuestionList();


        adapter = new AlternativeAdapter(getActivity(), currentAlternatives);
        wrongQuestionList = new ArrayList<>();

        alternatives.setAdapter(adapter);


        setQuestion(questionList.get(index));
        question = questionList.get(index);
        updateQuizCounter();

        return view;
    }

    public void setQuestion(Question question) {
        currentAlternatives.clear();
        this.questionTitle.setText(question.getQuestionTitle());
        currentAlternatives.add(question.getA());
        currentAlternatives.add(question.getB());
        currentAlternatives.add(question.getC());
        currentAlternatives.add(question.getD());
        currentAlternatives.add(question.getE());
        adapter.notifyDataSetChanged();
        adapter.clearPoints();
        TimerFragment.remainingPoints.setText("Pontos restantes: 4");
    }

    public void nextQuestion() {
        index++;
        if (index == questionList.size()) {
            this.sendResult(TimerFragment.currentTime);
        } else {
            question = this.questionList.get(index);
            this.setQuestion(question);
            updateQuizCounter();
        }
    }

    public void sendResult() {
        TimerFragment.countDownTimer.cancel();
        Intent intent = new Intent(getActivity(), ResultActivity.class);
        intent.putExtra("score", score);
        getActivity().finish();
        startActivity(intent);
    }

    public void sendResult(String finishedTime) {
        TimerFragment.countDownTimer.cancel();
        Intent intent = new Intent(getActivity(), ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("time", finishedTime);
        getActivity().finish();
        startActivity(intent);
    }

    public boolean checkAnswer() {
        if (AlternativeAdapter.points != 4) {
            Toast.makeText(getActivity(), "Você precisa distribuir todos os pontos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

//        if (radioGroup.getCheckedRadioButtonId() == question.getRightAnswerPosition()) {
//            score += 10;
//        } else {
//            String youChecked = getCheckedAnswer(radioGroup);
//            wrongQuestionList.add(new WrongQuestion(question.getQuestionTitle(), youChecked, question.getRightAnswer(), question.getExplanation()));
//        }
    }

//    public String getCheckedAnswer(RadioGroup radioGroup) {
//        switch (radioGroup.getCheckedRadioButtonId()) {
//            case R.id.a:
//                return question.getA();
//            case R.id.b:
//                return question.getB();
//            case R.id.c:
//                return question.getC();
//            case R.id.d:
//                return question.getD();
//            case R.id.e:
//                return question.getE();
//        }
//
//        return null;
//    }

    public void updateQuizCounter() {
        this.quizCounter.setText((questionNumber++) + "/" + questionList.size());
    }

//    public void uncheckRadioButtons() {
//        radioGroup.clearCheck();
//    }
//
//    public RadioGroup getRadioGroup() {
//        return this.radioGroup;
//    }
}
