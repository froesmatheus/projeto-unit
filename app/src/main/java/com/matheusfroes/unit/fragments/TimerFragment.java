package com.matheusfroes.unit.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matheusfroes.unit.R;

import java.text.NumberFormat;

/**
 * Created by matheus on 11/08/2015.
 */
public class TimerFragment extends Fragment {
    private TextView counter;
    private TimerFragmentListener timerFragmentListener;
    public static String currentTime;
    private NumberFormat numberFormat;
    public static CountDownTimer countDownTimer;
    public static TextView remainingPoints;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if ((activity instanceof TimerFragmentListener)) {
            timerFragmentListener = (TimerFragmentListener) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timer_layout, container, false);

        counter = (TextView) view.findViewById(R.id.timer);
        numberFormat = NumberFormat.getInstance();
        numberFormat.setMinimumIntegerDigits(2);
        remainingPoints = (TextView) view.findViewById(R.id.remaining_points);

        countDownTimer = new CountDownTimer(300000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = String.valueOf(millisUntilFinished);
                counter.setText(numberFormat.format((millisUntilFinished / 60000) % 60) + ":"
                        + numberFormat.format((millisUntilFinished / 1000) % 60));
            }

            @Override
            public void onFinish() {
                timerFragmentListener.onTimerFinish(currentTime);
            }
        }.start();
        return view;
    }

    public interface TimerFragmentListener {
        void onTimerFinish(String finishedTime);
    }
}
