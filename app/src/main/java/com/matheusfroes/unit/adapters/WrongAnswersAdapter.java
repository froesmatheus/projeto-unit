package com.matheusfroes.unit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matheusfroes.unit.R;
import com.matheusfroes.unit.model.WrongQuestion;

import java.util.List;

/**
 * Created by matheus on 25/10/2015.
 */
public class WrongAnswersAdapter extends BaseAdapter {
    private Context context;
    private List<WrongQuestion> questionList;

    public WrongAnswersAdapter(Context context, List<WrongQuestion> questionList) {
        this.context = context;
        this.questionList = questionList;
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public Object getItem(int i) {
        return questionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View wrongAnswerView = LayoutInflater.from(context).inflate(R.layout.wrong_answers_layout_view, null);

        TextView enunciado = (TextView) wrongAnswerView.findViewById(R.id.enunciado_txt);
        TextView voceMarcou = (TextView) wrongAnswerView.findViewById(R.id.voce_marcou_txt);
        TextView respostaCorreta = (TextView) wrongAnswerView.findViewById(R.id.respostas_correta_txt);
        TextView explicacao = (TextView) wrongAnswerView.findViewById(R.id.explicacao_txt);

        WrongQuestion question = questionList.get(i);

        enunciado.setText(question.getQuestionTitle());
        voceMarcou.setText(question.getYouChecked());
        respostaCorreta.setText(question.getRightAnswer());
        explicacao.setText(question.getExplanation());

        return wrongAnswerView;
    }
}
