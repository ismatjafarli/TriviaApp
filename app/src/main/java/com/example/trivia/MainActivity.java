package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.trivia.controller.AppController;
import com.example.trivia.data.AnswerListAsyncResponse;
import com.example.trivia.data.Repository;
import com.example.trivia.databinding.ActivityMainBinding;
import com.example.trivia.model.Question;

import org.json.JSONArray;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> questions;
    private ActivityMainBinding binding;
    private  int currentQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        questions = new Repository().getQuestions(questionArrayList ->
                {
         }
        );
        binding.questionIndexText.setText("Question: " + String.valueOf(currentQuestionNumber)+"/"+String.valueOf(questions.size()));

        binding.nextButton.setOnClickListener(v -> {
            nextQuestion();
            currentQuestionNumber = (currentQuestionNumber + 1) % questions.size();
            binding.questionIndexText.setText("Question: " +
                    String.valueOf(currentQuestionNumber)+"/"+String.valueOf(questions.size()));

        });


    }

    private void nextQuestion() {
        String question = questions.get(currentQuestionNumber).getQuestion();
        binding.questionTextView.setText(question);
 }
}