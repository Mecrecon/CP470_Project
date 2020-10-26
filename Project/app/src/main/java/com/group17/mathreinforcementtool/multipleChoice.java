package com.group17.mathreinforcementtool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class multipleChoice extends AppCompatActivity {

    //need to populate the question and answer lists
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    ArrayList<Integer> usedQuestions = new ArrayList<Integer>();
    int difficulty = 0;
    int type = 0;
    float number1 = 0f;
    float number2 = 0f;
    TextView questionTextView = null;
    RadioButton answerRadioButton1 = null;
    RadioButton answerRadioButton2 = null;
    RadioButton answerRadioButton3 = null;
    RadioButton answerRadioButton4 = null;
    int correctCount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
        Intent intent = getIntent();

        //get difficult and type of questions to generate
        difficulty = intent.getIntExtra("difficulty", 0);
        type = intent.getIntExtra("type", 0);

        //find questionTextView and answerRadioButton's
        questionTextView = findViewById(R.id.questionTextView);
        answerRadioButton1 = findViewById(R.id.answerRadioButton1);
        answerRadioButton2 = findViewById(R.id.answerRadioButton2);
        answerRadioButton3 = findViewById(R.id.answerRadioButton3);
        answerRadioButton4 = findViewById(R.id.answerRadioButton4);

        generateQuestion();
    }

    //generate 2 numbers based on the difficulty and put into activity
    public void generateQuestion(){
        Log.i(this.getLocalClassName(), "Started generateQuestion");
        //generate numbers
        Random rand = new Random();
        //easy difficulty
        if(difficulty == 0){
            //addition or subtraction
            if(type == 0 || type == 1) {
                int max = 9;
                int min = 1;
                number1 = rand.nextInt((max-min) + min);
                number2 = rand.nextInt((max-min) + min);
            }
            //multiplication or division
            if(type == 2 || type == 3) {
                float max = 9f;
                float min = 1f;
                number1 = min + rand.nextFloat() * (max-min);
                number2 = min + rand.nextFloat() * (max-min);
            }
        }
        //create question string and put in textview
        questionTextView.setText(R.string.questionText);

        return;
    }
}