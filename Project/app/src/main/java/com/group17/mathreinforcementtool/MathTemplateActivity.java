package com.group17.mathreinforcementtool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MathTemplateActivity extends AppCompatActivity
{
    String ACTIVITY_NAME = "MathTemplateActivity";

    Button buttonBack;
    Button buttonOK;
    TextView textNum1;
    TextView textNum2;
    TextView textUserInput;
    TextView textDisplayResult;
    int min;
    int max;
    int num1;
    int num2;

    /*
    ----------------------------------------------------
    Parameters:   savedInstanceState (bundle)
    Return:       None
    Description:  -onCreate() function for MathTemplateActivity
                  -Gets information from relevant views
                  -Calculates and displays first question
    ----------------------------------------------------
    */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_template);

        buttonBack = findViewById(R.id.buttonBack);
        buttonOK = findViewById(R.id.buttonOK);
        textNum1 = findViewById(R.id.textNum1);
        textNum2 = findViewById(R.id.textNum2);
        textUserInput = findViewById(R.id.textUserInput);
        textDisplayResult = findViewById(R.id.textDisplayResult);

        String currentDifficulty = getIntent().getStringExtra("AdditionDifficulty");
        if(currentDifficulty.equals("Easy"))
        {
            min = 1;
            max = 49;
        }
        else if (currentDifficulty.equals("Medium"))
        {
            min = 10;
            max = 499;
        }
        else if (currentDifficulty.equals("Hard"))
        {
            min = 100;
            max = 4999; // Answer will have 4 digits at maximum if unchanged
        }
        else
        {
            Log.e(ACTIVITY_NAME, "Difficulty was not 'Easy', 'Medium', or 'Hard'.");
            throw new IllegalStateException();
        }

        num1 = _getRandomNumber(min, max);
        num2 = _getRandomNumber(min, max);
        textNum1.setText(String.valueOf(num1));
        textNum2.setText(String.valueOf(num2));
    }

    /*
    ----------------------------------------------------
    Parameters:   v (View)
    Return:       None
    Description:  -Called when any numbered buttons (button0 ~ button9) are clicked
                  -Appends value of the button to textUserInput
    ----------------------------------------------------
    */
    public void NumberedButtonClick(View v)
    {
        Log.i(ACTIVITY_NAME, "Button Num pressed");
        Button currentButton = findViewById(v.getId());
        String buttonText = currentButton.getText().toString();
        String userInputText = textUserInput.getText().toString();
        textUserInput.setText(userInputText + buttonText);
    }

    /*
    ----------------------------------------------------
    Parameters:   v (View)
    Return:       None
    Description:  -Called when the back button is clicked
                  -Removes last value of textUserInput
    ----------------------------------------------------
    */
    public void ButtonBackClick(View v)
    {
        String userInputText = textUserInput.getText().toString();
        int userInputLen = userInputText.length();
        if(userInputLen > 0)
        {
            userInputText = userInputText.substring(0, userInputLen - 1);
            textUserInput.setText(userInputText);
        }
    }

    /*
    ----------------------------------------------------
    Parameters:   v (View)
    Return:       None
    Description:  -Called when the OK button is clicked
                  -If user input is blank, does nothing
                  -Determines if the user input was correct, and
                  updates textDisplayResult accordingly
                  -Prepares the next question
    ----------------------------------------------------
    */
    public void ButtonOKClick(View v)
    {
        String userInputText = textUserInput.getText().toString();

        if(userInputText.equals(""))
        {
            return;
        }

        int userInputNum = Integer.parseInt(userInputText);

        if(userInputNum == num1 + num2)
        {
            textDisplayResult.setText("Yes");
        }
        else
        {
            textDisplayResult.setText("No");
        }

        num1 = _getRandomNumber(min, max);
        num2 = _getRandomNumber(min, max);
        textNum1.setText(String.valueOf(num1));
        textNum2.setText(String.valueOf(num2));
        textUserInput.setText("");
    }

    /*
    ----------------------------------------------------
    Parameters:   min (int), max (int)
    Return:       num (int)
    Description:  -Helper function that generates a random number
                  between min and max
    ----------------------------------------------------
    */
    public int _getRandomNumber(int min, int max)
    {
        Random numGenerator = new Random();
        return numGenerator.nextInt((max - min) + 1) + min;
    }
}