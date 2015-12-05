package com.matheusfroes.unit.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.matheusfroes.unit.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FancyButton startButton = (FancyButton) findViewById(R.id.start_button);
        FancyButton instructionsButton = (FancyButton) findViewById(R.id.instructions_button);
        FancyButton exitButton = (FancyButton) findViewById(R.id.exit_button);

        startButton.setOnClickListener(this);
        instructionsButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {

            case R.id.start_button:
                intent = new Intent(this, QuizActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.exit_button:
                this.onBackPressed();
                break;
            case R.id.instructions_button:
                intent = new Intent(this, InstructionsActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);

        builder.setMessage("Tem certeza que deseja sair?");
        builder.setTitle("Confirmar saída");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }
}
