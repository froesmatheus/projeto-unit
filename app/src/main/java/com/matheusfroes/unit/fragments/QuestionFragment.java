package com.matheusfroes.unit.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
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
import com.matheusfroes.unit.model.Alternative;
import com.matheusfroes.unit.model.Question;
import com.matheusfroes.unit.model.WrongQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment {
    public static List<WrongQuestion> wrongQuestionList;
    public static List<Pair<String, Integer>> rightAlternatives;
    private Question question;
    private TextView questionTitle;
    private TextView quizCounter;
    private QuestionDAO dao;
    private List<Question> questionList = new ArrayList<>();
    private List<Alternative> currentAlternatives = new ArrayList<>();
    private AlternativeAdapter adapter;
    private int index = 0;
    private int questionNumber = 1;
    private int score = 0;

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
        rightAlternatives = new ArrayList<>();

        alternatives.setAdapter(adapter);


        setQuestion(questionList.get(index));
        question = questionList.get(index);
        updateQuizCounter();

        return view;
    }

    public void setQuestion(Question question) {
        currentAlternatives.clear();
        this.questionTitle.setText(question.getQuestionTitle());
        currentAlternatives.add(new Alternative(question.getA()));
        currentAlternatives.add(new Alternative(question.getB()));
        currentAlternatives.add(new Alternative(question.getC()));
        currentAlternatives.add(new Alternative(question.getD()));
        currentAlternatives.add(new Alternative(question.getE()));
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
            Toast.makeText(getActivity(), R.string.alert_distribute_points, Toast.LENGTH_SHORT).show();
            return false;
        }

        List<Alternative> alternativesList = adapter.getAlternativesList();
        List<Alternative> betAlternatives = new ArrayList<>();

        int rightAlternativePosition = question.getRightAnswerPosition();
        Alternative rightAlternative = alternativesList.get(rightAlternativePosition);

        for (Alternative alternative : alternativesList) {
            if (alternative.getBetPoints() > 0) {
                betAlternatives.add(alternative);
            }
        }

        if (betAlternatives.contains(rightAlternative)) {
            score += rightAlternative.getBetPoints();
            rightAlternatives.add(new Pair<>(question.getQuestionTitle(), rightAlternative.getBetPoints()));
        }


        return true;
    }

    public void updateQuizCounter() {
        this.quizCounter.setText((questionNumber++) + "/" + questionList.size());
    }
}
