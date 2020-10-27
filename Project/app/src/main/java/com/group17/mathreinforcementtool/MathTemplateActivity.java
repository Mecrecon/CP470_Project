package com.group17.mathreinforcementtool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
    TextView textOperator;
    int min;
    int max;
    int num1;
    int num2;

    /*
    ----------------------------------------------------
    Parameters:   savedInstanceState (bundle)
    Return:       None
    Description:  -onCreate() function for MathTemplateActivity
                  -Gets Operation Type & Difficulty from relevant views, and
                  sets up the activity according to those options
                  -Calculates and displays first question
    ----------------------------------------------------
    */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_template);

        buttonBack = findViewById(R.id.buttonBack);
        buttonOK = findViewById(R.id.buttonOKAddition);
        textNum1 = findViewById(R.id.textNum1);
        textNum2 = findViewById(R.id.textNum2);
        textUserInput = findViewById(R.id.textUserInput);
        textDisplayResult = findViewById(R.id.textDisplayResult);
        textOperator = findViewById(R.id.textOperator);

        String currentOperation = getIntent().getStringExtra("Type");
        String currentDifficulty = getIntent().getStringExtra("Difficulty");

        if(currentOperation.equals("Addition"))
        {
            textOperator.setText("+");
            buttonOK = findViewById(R.id.buttonOKAddition);
            buttonOK.setVisibility(View.VISIBLE);
            switch(currentDifficulty)
            {
                case "Easy":
                    min = 1; max = 49;
                    break;
                case "Medium":
                    min = 10; max = 499;
                    break;
                case "Hard":
                    min = 100; max = 4999;
                    break;
                default:
                    Log.e(ACTIVITY_NAME, "Invalid Difficulty");
                    throw new IllegalStateException();
            }

            num1 = _getRandomNumber(min, max);
            num2 = _getRandomNumber(min, max);
            textNum1.setText(String.valueOf(num1));
            textNum2.setText(String.valueOf(num2));
            textUserInput.setText("");
        }

        else if(currentOperation.equals("Subtraction"))
        {
            textOperator.setText("-");
            buttonOK = findViewById(R.id.buttonOKSubtraction);
            buttonOK.setVisibility(View.VISIBLE);
            switch(currentDifficulty)
            {
                case "Easy":
                    min = 1; max = 49;
                    break;
                case "Medium":
                    min = 10; max = 499;
                    break;
                case "Hard":
                    min = 100; max = 4999;
                    break;
                default:
                    Log.e(ACTIVITY_NAME, "Invalid Difficulty");
                    throw new IllegalStateException();
            }

            num1 = _getRandomNumber(min, max);
            num2 = _getRandomNumber(min, max);

            // Ensures no negative answer
            if(num2 > num1)
            {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }

            textNum1.setText(String.valueOf(num1));
            textNum2.setText(String.valueOf(num2));
            textUserInput.setText("");
        }

        else if(currentOperation.equals("Multiplication"))
        {
            textOperator.setText("*");
            buttonOK = findViewById(R.id.buttonOKMultiplication);
            buttonOK.setVisibility(View.VISIBLE);
            switch(currentDifficulty)
            {
                case "Easy":
                    min = 2; max = 6;
                    break;
                case "Medium":
                    min = 2; max = 12;
                    break;
                case "Hard":
                    min = 2; max = 16;
                    break;
                default:
                    Log.e(ACTIVITY_NAME, "Invalid Difficulty");
                    throw new IllegalStateException();
            }

            num1 = _getRandomNumber(min, max);
            num2 = _getRandomNumber(min, max);
            textNum1.setText(String.valueOf(num1));
            textNum2.setText(String.valueOf(num2));
            textUserInput.setText("");
        }

        else if(currentOperation.equals("Division"))
        {
            textOperator.setText("/");
            buttonOK = findViewById(R.id.buttonOKDivision);
            buttonOK.setVisibility(View.VISIBLE);
            switch(currentDifficulty)
            {
                case "Easy":
                    min = 8; max = 49;
                    break;
                case "Medium":
                    min = 15; max = 199;
                    break;
                case "Hard":
                    min = 30; max = 499;
                    break;
                default:
                    Log.e(ACTIVITY_NAME, "Invalid Difficulty");
                    throw new IllegalStateException();
            }

            num1 = _getRandomNumber(min, max);
            num2 = _getRandomNumber(2, min); // Ensures answer is always above 0
            textNum1.setText(String.valueOf(num1));
            textNum2.setText(String.valueOf(num2));
            textUserInput.setText("");
        }

        else
        {
            Log.e(ACTIVITY_NAME, "Invalid Type");
            throw new IllegalStateException();
        }
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
    Description:  -Called when an OK button is clicked
                  -The function ran depends on the operation type of the activity, as only
                  the OK button corresponding to 'currentOperation' from onCreate() is visible
                  -If user input is blank, does nothing
                  -Determines if the user input was correct, and
                  updates textDisplayResult accordingly
                  -Prepares the next question
    ----------------------------------------------------
    */
    public void ButtonOKAdditionClick(View v)
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

    public void ButtonOKSubtractionClick(View v)
    {
        String userInputText = textUserInput.getText().toString();
        if(userInputText.equals(""))
        {
            return;
        }

        int userInputNum = Integer.parseInt(userInputText);
        if(userInputNum == num1 - num2)
        {
            textDisplayResult.setText("Yes");
        }
        else
        {
            textDisplayResult.setText("No");
        }

        num1 = _getRandomNumber(min, max);
        num2 = _getRandomNumber(min, max);

        if(num2 > num1)
        {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        textNum1.setText(String.valueOf(num1));
        textNum2.setText(String.valueOf(num2));
        textUserInput.setText("");
    }

    public void ButtonOKMultiplicationClick(View v)
    {
        String userInputText = textUserInput.getText().toString();
        if(userInputText.equals(""))
        {
            return;
        }

        int userInputNum = Integer.parseInt(userInputText);
        if(userInputNum == num1 * num2)
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

    public void ButtonOKDivisionClick(View v)
    {
        String userInputText = textUserInput.getText().toString();
        if(userInputText.equals(""))
        {
            return;
        }

        int userInputNum = Integer.parseInt(userInputText);
        if(userInputNum == num1 / num2)
        {
            textDisplayResult.setText("Yes");
        }
        else
        {
            textDisplayResult.setText("No");
        }

        num1 = _getRandomNumber(min, max);
        num2 = _getRandomNumber(2, min);
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