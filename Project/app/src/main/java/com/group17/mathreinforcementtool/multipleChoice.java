package com.group17.mathreinforcementtool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;
import java.util.Random;

public class multipleChoice extends AppCompatActivity {
    int difficulty = 0;
    int type = 0;
    float number1 = 0f;
    float number2 = 0f;
    float numAnswer = 0f;
    int max = 9;
    int min = 1;
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
    public void onClick(View view){
        float answer = 0f;

        //answer1
        if(answerRadioButton1.isChecked()){
            answer = Float.valueOf((String) answerRadioButton1.getText());
            answerRadioButton1.setChecked(false);
        }
        //answer2
        else if(answerRadioButton2.isChecked()){
            answer = Float.valueOf((String) answerRadioButton2.getText());
            answerRadioButton2.setChecked(false);
        }
        //answer2
        else if(answerRadioButton3.isChecked()){
            answer = Float.valueOf((String) answerRadioButton3.getText());
            answerRadioButton3.setChecked(false);
        }
        //answer2
        else if(answerRadioButton4.isChecked()){
            answer = Float.valueOf((String) answerRadioButton4.getText());
            answerRadioButton4.setChecked(false);
        }
        //check answer
        correctAnswer(answer);
        return;
    }
    public void correctAnswer(float answer){
        CharSequence text;
        int duration= Toast.LENGTH_SHORT;

        //if answer is correct increment counter and print toast
        if(answer == numAnswer){
            correctCount++;
            text = "Correct!";
        }
        //answer is wrong just print toast message
        else{
            text = "Incorrect";
        }
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

        //generate new question if user hasn't answered 10 correct answers
        if(correctCount < 10) {
            generateQuestion();
        }
        //close activity (save any stats or return anything before finishing)
        else{
            finish();
        }
        return;
    }

    //generate 2 numbers based on the difficulty and put into activity
    public void generateQuestion(){
        //reset button texts
        answerRadioButton1.setText("");
        answerRadioButton2.setText("");
        answerRadioButton3.setText("");
        answerRadioButton4.setText("");

        //generate numbers
        Random rand = new Random();

        //easy difficulty
        if(difficulty == 0){
            //addition or subtraction
            if(type == 0 || type == 1) {
                max = 9;
                min = 1;
                number1 = (float) rand.nextInt((max-min) + min);
                number2 = (float) rand.nextInt((max-min) + min);
            }
            //multiplication or division
            else if(type == 2 || type == 3) {
                max = 4;
                min = 1;
                number1 = (float) rand.nextInt((max-min) + min);
                number2 = (float) rand.nextInt((max-min) + min);
            }
        }
        //medium difficulty
        if(difficulty == 1){
            //addition or subtraction
            if(type == 0 || type == 1) {
                max = 99;
                min = 10;
                number1 = (float) rand.nextInt((max-min) + min);
                number2 = (float) rand.nextInt((max-min) + min);
            }
            //multiplication or division
            else if(type == 2 || type == 3) {
                max = 6;
                min = 1;
                number1 = (float) rand.nextInt((max-min) + min);
                number2 = (float) rand.nextInt((max-min) + min);
            }
        }
        //hard difficulty
        if(difficulty == 2){
            //addition or subtraction
            if(type == 0 || type == 1) {
                max = 999;
                min = 100;
                number1 = (float) rand.nextInt((max-min) + min);
                number2 = (float) rand.nextInt((max-min) + min);
            }
            //multiplication or division
            else if(type == 2 || type == 3) {
                max = 8;
                min = 1;
                number1 = (float) rand.nextInt((max-min) + min);
                number2 = (float) rand.nextInt((max-min) + min);
            }
        }

        //addition question
        if(type == 0) {
            //set text for question

            questionTextView.setText(getString(R.string.questionText) + String.format(" %d + %d equals?", Math.round(number1), Math.round(number2)));

            //answer to question
            numAnswer = number1 + number2;
        }
        //subtraction question
        else if(type == 1){
            questionTextView.setText(getString(R.string.questionText) + String.format(" %d - %d equals?", Math.round(number1), Math.round(number2)));

            //answer to question
            numAnswer = number1 - number2;
        }
        //multiplication question
        else if(type == 2){
            questionTextView.setText(getString(R.string.questionText) + String.format(" %d * %d equals?",Math.round(number1), Math.round(number2)));

            //answer to question
            numAnswer = number1 * number2;
        }
        //division question
        else if(type == 3){
            questionTextView.setText(getString(R.string.questionText) + String.format(" %d / %d equals?", Math.round(number1), Math.round(number2)));

            //answer to question
            numAnswer = number1 / number2;
        }

        //set the answer to one of the radio buttons and fill the remaining 3 with random values
        int answerSpot = rand.nextInt((3 - 0) + 0);
        String stringAnswer;

        //if the number is whole then print number as integer (no decimal)
        if(numAnswer %1 == 0){
            stringAnswer = String.format("%d", Math.round(numAnswer));
        }
        //number is a decimal so print as decimal number (up to 3 decimal points)
        else{
            stringAnswer = String.format("%.3f", Math.round(numAnswer));
        }
        //Log.i(this.getLocalClassName(), "AnswerSpot = " + Integer.toString(answerSpot));
            switch (answerSpot) {
                case 0:
                    answerRadioButton1.setText(stringAnswer);
                    break;
                case 1:
                    answerRadioButton2.setText(stringAnswer);
                    break;
                case 2:
                    answerRadioButton3.setText(stringAnswer);
                    break;
                case 3:
                    answerRadioButton4.setText(stringAnswer);
                    break;
            }
            float randomAnswer = 0f;
            ArrayList<Float> generatedAnswers = new ArrayList<Float>();

            //for each button update text with a random answer if it doesn't contain the answer
            for(int i =0; i < 4; i++){
                String genAnswer = "";

                //randomize answer for addition/subtraction questions
                if(type < 2){
                    randomAnswer = (float) rand.nextInt((max-min) + min);

                    //keep randomizing answer until it doesn't match the actual answer
                    while (randomAnswer == numAnswer || generatedAnswers.equals(randomAnswer) == true) {
                        randomAnswer = (float) rand.nextInt((max-min) + min);
                    }
                    genAnswer = String.format("%d", Math.round(randomAnswer));
                }
                //randomize answer for multiplication questions
                else if(type == 2){
                    randomAnswer = (((float) rand.nextInt((max-min) + min)) * ((float) rand.nextInt((max-min) + min)));

                    //keep randomizing answer until it doesn't match the actual answer
                    while (randomAnswer == numAnswer || generatedAnswers.equals(randomAnswer) == true) {
                        Log.i("generateAuestion", "Number not in array and not the answer = " + Integer.toString(Math.round(randomAnswer)));
                        randomAnswer = (((float) rand.nextInt((max-min) + min)) * ((float) rand.nextInt((max-min) + min)));
                    }
                    genAnswer = String.format("%d", Math.round(randomAnswer));
                }
                //randomize answer for division questions
                else{
                    randomAnswer =(((float) rand.nextInt((max-min) + min)) / ((float) rand.nextInt((max-min) + min)));

                        //keep randomizing answer until it doesn't match the actual answer or is one of the current
                        while (randomAnswer == numAnswer || generatedAnswers.equals(randomAnswer) == true) {
                            randomAnswer =(((float) rand.nextInt((max-min) + min)) / ((float) rand.nextInt((max-min) + min)));
                        }
                    genAnswer = String.format("%.3f", randomAnswer);
                    }
                generatedAnswers.add(randomAnswer);

                //button 1
                if(i == 0) {
                    if (answerRadioButton1.getText() == "") {
                        answerRadioButton1.setText(genAnswer);
                        //Log.i(this.getLocalClassName(), "Button 1 set number= " + Float.toString(randomAnswer));
                    }
                }
                //button 2
                else if(i == 1) {
                    if (answerRadioButton2.getText() == "") {
                        answerRadioButton2.setText(genAnswer);
                        //Log.i(this.getLocalClassName(), "Button 2 set number= " + Float.toString(randomAnswer));
                    }
                }
                //button 3
                else if(i == 2) {
                    if (answerRadioButton3.getText() == "") {
                        answerRadioButton3.setText(genAnswer);
                        //Log.i(this.getLocalClassName(), "Button 3 set number= " + Float.toString(randomAnswer));
                    }
                }
                //button 4
                else if(i == 3) {
                    if (answerRadioButton4.getText() == "") {
                        answerRadioButton4.setText(genAnswer);
                        //Log.i(this.getLocalClassName(), "Button 4 set number= " + Float.toString(randomAnswer));
                    }
                }

        }
        return;
    }
}