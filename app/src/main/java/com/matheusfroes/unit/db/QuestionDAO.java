package com.matheusfroes.unit.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.matheusfroes.unit.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matheus on 25/10/2015.
 */
public class QuestionDAO {
    private SQLiteOpenHelper dbCore;
    private SQLiteDatabase db;


    public QuestionDAO(Context context) {
        dbCore = new DBCore(context);
        db = dbCore.getWritableDatabase();
    }

    public boolean insertQuestion(Question question) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBCore.COLUNA_ENUNCIADO, question.getQuestionTitle());
        contentValues.put(DBCore.COLUNA_ALTERNATIVA_A, question.getA());
        contentValues.put(DBCore.COLUNA_ALTERNATIVA_B, question.getB());
        contentValues.put(DBCore.COLUNA_ALTERNATIVA_C, question.getC());
        contentValues.put(DBCore.COLUNA_ALTERNATIVA_D, question.getD());
        contentValues.put(DBCore.COLUNA_ALTERNATIVA_E, question.getE());
        contentValues.put(DBCore.COLUNA_ALTERNATIVA_CORRETA, question.getRightAnswer());
        contentValues.put(DBCore.COLUNA_ALTERNATIVA_CORRETA_ID, question.getRightAnswerPosition());
        contentValues.put(DBCore.COLUNA_EXPLICACAO, question.getExplanation());

        long rowID = db.insert(DBCore.TABELA_PERGUNTAS, null, contentValues);

        return rowID != -1;
    }

    public boolean insertQuestions(List<Question> questionList) {
        boolean inseriu = true;
        for (Question question : questionList) {
            inseriu &= this.insertQuestion(question);
        }

        return inseriu;
    }

    public List<Question> getQuestionList() {
        List<Question> questionList = new ArrayList<>();

        String[] colunas = {"_id", DBCore.COLUNA_ENUNCIADO, DBCore.COLUNA_ALTERNATIVA_A, DBCore.COLUNA_ALTERNATIVA_B,
                DBCore.COLUNA_ALTERNATIVA_C, DBCore.COLUNA_ALTERNATIVA_D, DBCore.COLUNA_ALTERNATIVA_E, DBCore.COLUNA_ALTERNATIVA_CORRETA,
                DBCore.COLUNA_ALTERNATIVA_CORRETA_ID, DBCore.COLUNA_EXPLICACAO};

        Cursor cursor = db.query(DBCore.TABELA_PERGUNTAS, colunas, null, null, null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Question question = new Question();

                question.setQuestionTitle(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_ENUNCIADO)));
                question.setA(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_ALTERNATIVA_A)));
                question.setB(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_ALTERNATIVA_B)));
                question.setC(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_ALTERNATIVA_C)));
                question.setD(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_ALTERNATIVA_D)));
                question.setE(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_ALTERNATIVA_E)));
                question.setRightAnswer(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_ALTERNATIVA_CORRETA)));
                question.setExplanation(cursor.getString(cursor.getColumnIndex(DBCore.COLUNA_EXPLICACAO)));
                question.setRightAnswerId(cursor.getInt(cursor.getColumnIndex(DBCore.COLUNA_ALTERNATIVA_CORRETA_ID)));

                questionList.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return questionList;
    }
}
