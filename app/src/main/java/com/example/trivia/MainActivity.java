package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
import com.google.android.material.snackbar.Snackbar;

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

        questions = new Repository().getQuestions(questionArrayList -> {
            binding.questionTextView.setText(questions.get(currentQuestionNumber).getQuestion());
            updateCounter();
        });

        binding.nextButton.setOnClickListener(v -> {
            nextQuestion();
            currentQuestionNumber = (currentQuestionNumber + 1) % questions.size();
            updateCounter();
        });

        binding.trueButton.setOnClickListener(v -> {
            checkAnswer(true);
            nextQuestion();

        });

        binding.falseButton.setOnClickListener(v -> {
            checkAnswer(false);
            nextQuestion();
        });
    }

    private void checkAnswer(boolean answerTrue) {
        int snackBarId = 0;
        if(answerTrue == questions.get(currentQuestionNumber).isTrueAnswer()) {
            snackBarId = R.string.correct_answer;
            fadeAnimation();
        } else {
            snackBarId = R.string.wrong_answer;
            shakeAnimation();
        }
        Snackbar.make(binding.cardView, snackBarId, Snackbar.LENGTH_SHORT)
                .show();
    }

    private void nextQuestion() {
        String question = questions.get(currentQuestionNumber).getQuestion();
        binding.questionTextView.setText(question);
    }

    private void updateCounter() {
        binding.questionIndexText.setText("Question: " + String.valueOf(currentQuestionNumber) + "/" + String.valueOf(questions.size()));
    }

    private void shakeAnimation(){
        Animation shake = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.shake_animation);
        binding.cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        }

    private void fadeAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.questionTextView.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.questionTextView.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}