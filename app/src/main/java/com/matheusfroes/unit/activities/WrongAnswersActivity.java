package com.matheusfroes.unit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.matheusfroes.unit.R;
import com.matheusfroes.unit.adapters.WrongAnswersAdapter;
import com.matheusfroes.unit.fragments.QuestionFragment;

public class WrongAnswersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_answers);

        ListView listView = (ListView) findViewById(R.id.list_view);

        WrongAnswersAdapter wrongAnswersAdapter = new WrongAnswersAdapter(this, QuestionFragment.wrongQuestionList);

        listView.setAdapter(wrongAnswersAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return onOptionsItemSelected(item);

    }
}
