package com.matheusfroes.unit.model;

/**
 * Created by matheus on 25/10/2015.
 */
public class WrongQuestion {
    private String questionTitle;
    private String youChecked;
    private String rightAnswer;
    private String explanation;

    public WrongQuestion(String questionTitle, String youChecked, String rightAnswer, String explanation) {
        this.questionTitle = questionTitle;
        this.youChecked = youChecked;
        this.rightAnswer = rightAnswer;
        this.explanation = explanation;
    }

    public String getYouChecked() {
        return youChecked;
    }

    public void setYouChecked(String youChecked) {
        this.youChecked = youChecked;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
