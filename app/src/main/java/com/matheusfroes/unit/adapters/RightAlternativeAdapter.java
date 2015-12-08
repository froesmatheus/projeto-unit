package com.matheusfroes.unit.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.matheusfroes.unit.R;

import java.util.List;

/**
 * Created by Waldson on 07/12/2015.
 */
public class RightAlternativeAdapter extends BaseAdapter {
    private Context context;
    private List<Pair<String, Integer>> rightAlternatives;

    public RightAlternativeAdapter(Context context, List<Pair<String, Integer>> rightAlternatives) {
        this.context = context;
        this.rightAlternatives = rightAlternatives;
    }

    @Override
    public int getCount() {
        return rightAlternatives.size();
    }

    @Override
    public Object getItem(int i) {
        return rightAlternatives.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View alternativeView = LayoutInflater.from(context).inflate(R.layout.right_alternative_view, null);

        TextView questionTitle = (TextView) alternativeView.findViewById(R.id.right_question_title);
        TextView betPoints = (TextView) alternativeView.findViewById(R.id.points_bet);

        Pair<String, Integer> pair = rightAlternatives.get(i);

        questionTitle.setText(pair.first);
        betPoints.setText(String.valueOf(pair.second));

        return alternativeView;
    }
}
