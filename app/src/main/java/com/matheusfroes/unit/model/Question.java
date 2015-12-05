package com.matheusfroes.unit.model;

public class Question {
    private int id;
    private String questionTitle;
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String rightAnswer;
    private String explanation;
    private int rightAnswerId;

    public Question() {

    }

    public Question(String questionTitle, String a, String b, String c, String d, String e, String rightAnswer, String explanation,
                    int rightAnswerId) {
        super();
        this.questionTitle = questionTitle;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.rightAnswer = rightAnswer;
        this.explanation = explanation;
        this.rightAnswerId = rightAnswerId;
    }

    public int getRightAnswerPosition() {
        return rightAnswerId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getE() {
        return e;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public void setC(String c) {
        this.c = c;
    }

    public void setD(String d) {
        this.d = d;
    }

    public void setB(String b) {
        this.b = b;
    }

    public void setE(String e) {
        this.e = e;
    }

    public void setRightAnswerId(int rightAnswerId) {
        this.rightAnswerId = rightAnswerId;
    }

    @Override
    public String toString() {
        return this.getQuestionTitle();
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
