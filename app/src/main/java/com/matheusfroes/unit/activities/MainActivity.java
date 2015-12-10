package com.matheusfroes.unit.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.matheusfroes.unit.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean firstTime;
    private String userName;
    private SharedPreferences.Editor preferencesEditor;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencesEditor = getSharedPreferences("Preferences", MODE_PRIVATE).edit();
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);

        firstTime = preferences.getBoolean("firstTime", true);

        if (firstTime) {
            showEnterYourNameDialog();
            firstTime = false;
            preferencesEditor.putBoolean("firstTime", firstTime);
            preferencesEditor.apply();
        }

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
                break;
            case R.id.exit_button:
                this.onBackPressed();
                break;
            case R.id.instructions_button:
                intent = new Intent(this, InstructionsActivity.class);
                startActivity(intent);
        }
    }

    private void showEnterYourNameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Qual é o seu nome completo?");

        final EditText nomeEt = new EditText(this);

        builder.setView(nomeEt);

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                userName = nomeEt.getText().toString();
                preferencesEditor.putString("userName", userName);
                Toast.makeText(MainActivity.this, "Olá, " + userName, Toast.LENGTH_LONG).show();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
