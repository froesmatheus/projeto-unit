package com.matheusfroes.unit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.matheusfroes.unit.R;
import com.matheusfroes.unit.fragments.TimerFragment;
import com.matheusfroes.unit.model.Alternative;

import java.util.List;

/**
 * Created by Waldson on 04/12/2015.
 */
public class AlternativeAdapter extends BaseAdapter {
    public static int points;
    private Context context;
    private List<Alternative> alternatives;

    public AlternativeAdapter(Context context, List<Alternative> alternatives) {
        this.context = context;
        points = 0;
        this.alternatives = alternatives;
    }

    @Override
    public int getCount() {
        return alternatives.size();
    }

    @Override
    public Object getItem(int i) {
        return alternatives.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View alternativeView = LayoutInflater.from(context).inflate(R.layout.alternative_view, null);

        ImageView btnMinus = (ImageView) alternativeView.findViewById(R.id.btn_minus);
        ImageView btnPlus = (ImageView) alternativeView.findViewById(R.id.btn_plus);

        final TextView incrementer = (TextView) alternativeView.findViewById(R.id.increment_txt);
        final TextView alternativeTxt = (TextView) alternativeView.findViewById(R.id.alternative);

        final Alternative alternative = alternatives.get(i);
        alternativeTxt.setText(alternative.getAlternative());

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPoints = Integer.valueOf(incrementer.getText().toString());
                if (currentPoints > 0) {
                    points--;
                    alternative.decrementBetPoints();
                    TimerFragment.remainingPoints.setText("Pontos restantes: " + (4 - points));
                    incrementer.setText(String.valueOf(currentPoints - 1));
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (points < 4) {
                    points++;
                    TimerFragment.remainingPoints.setText("Pontos restantes: " + (4 - points));
                    int currentPoints = Integer.valueOf(incrementer.getText().toString());
                    alternative.incrementBetPoints();
                    incrementer.setText(String.valueOf(currentPoints + 1));
                }
            }
        });

        return alternativeView;
    }

    public void clearPoints() {
        points = 0;
    }

    public int getPoints() {
        return points;
    }

    public List<Alternative> getAlternativesList() {
        return this.alternatives;
    }
}
