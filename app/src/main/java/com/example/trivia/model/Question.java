package com.example.trivia.model;

public class Question {
    private String question;
    private boolean trueAnswer;


    public Question() {
    }

    public Question(String question, boolean trueAnswer) {
        this.question = question;
        this.trueAnswer = trueAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(boolean trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    @Override
    public String toString() {
        return "Question = " + question + '\n' +
                "Answer = " + trueAnswer;
    }
}
