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
    float numAnswer = 0f;
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

        //generate numbers
        Random rand = new Random();
        int max = 9;
        int min = 1;

        //easy difficulty
        if(difficulty == 0){
            //addition or subtraction
            if(type == 0 || type == 1) {
                number1 = (float) rand.nextInt((max-min) + min);
                number2 = (float) rand.nextInt((max-min) + min);
            }
            //multiplication or division
            else if(type == 2 || type == 3) {
                number1 = (float) (min + rand.nextInt() * (max-min));
                number2 = (float) (min + rand.nextInt() * (max-min));
            }
        }

        //create question string and put in textview
        if(type == 0) {
            //set text for question
            questionTextView.setText(getString(R.string.questionText) + Float.toString(number1) + " + " + Float.toString(number2) + " equal?");

            //answer to question
            numAnswer = number1 + number2;
        }
        //set the answer to one of the radio buttons and fill the remaining 3 with random values
        int answerSpot = rand.nextInt((3 - 0) + 0);
        Log.i(this.getLocalClassName(), "AnswerSpot = " + Integer.toString(answerSpot));
            switch (answerSpot) {
                case 0:
                    answerRadioButton1.setText(Float.toString(numAnswer));
                    break;
                case 1:
                    answerRadioButton2.setText(Float.toString(numAnswer));
                    break;
                case 2:
                    answerRadioButton3.setText(Float.toString(numAnswer));
                    break;
                case 3:
                    answerRadioButton4.setText(Float.toString(numAnswer));
                    break;
            }
            for(int i =0; i < 4; i++){
                //button 1
                if(i == 0) {
                    if (answerRadioButton1.getText() == "") {
                        answerRadioButton1.setText(Float.toString(rand.nextInt((max - min) + min)));
                        Log.i(this.getLocalClassName(), "Button 1 was empty");
                    }
                }
                //button 2
                if(i == 1) {
                    if (answerRadioButton2.getText() == "") {
                        answerRadioButton2.setText(Float.toString(rand.nextInt((max - min) + min)));
                        Log.i(this.getLocalClassName(), "Button 2 was empty");
                    }
                }
                //button 3
                if(i == 2) {
                    if (answerRadioButton3.getText() == "") {
                        answerRadioButton3.setText(Float.toString(rand.nextInt((max - min) + min)));
                        Log.i(this.getLocalClassName(), "Button 3 was empty");
                    }
                }
                //button 4
                if(i == 3) {
                    if (answerRadioButton4.getText() == "") {
                        answerRadioButton4.setText(Float.toString(rand.nextInt((max - min) + min)));
                        Log.i(this.getLocalClassName(), "Button 4 was empty");
                    }
                }

        }
        return;
    }
}