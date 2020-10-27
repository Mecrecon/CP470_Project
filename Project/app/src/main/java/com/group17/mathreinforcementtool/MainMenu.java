package com.group17.mathreinforcementtool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    //swap to settings activity
    public void onClickSettings(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    //easy multiple choice
    public void onClickEasyMultipleChoice(View view){
        Intent intent = new Intent(this, multipleChoice.class);
        intent.putExtra("difficulty", 0);
        intent.putExtra("type", 2);
        startActivity(intent);
    }
    //medium multiple choice
    public void onClickMediumMultipleChoice(View view){
        Intent intent = new Intent(this, multipleChoice.class);
        intent.putExtra("difficulty", 1);
        intent.putExtra("type", 0);
        startActivity(intent);
    }
    //medium multiple choice
    public void onClickHardMultipleChoice(View view){
        Intent intent = new Intent(this, multipleChoice.class);
        intent.putExtra("difficulty", 2);
        intent.putExtra("type", 0);
        startActivity(intent);
    }

    /*
    ----------------------------------------------------
    Parameters:   v (View)
    Return:       None
    Description:  -Called when the buttons related to non-MC Addition are clicked
                  -Records difficulty ('Easy', 'Medium', 'Hard') the user chose and
                  switches to MathTemplateActivity.
                  -!! Although startActivityForResult() is called,
                  onActivityResult() is not yet implemented.

                  -All of these could be one function in the future, however I likely
                  would need a different representation than just buttons.
    ----------------------------------------------------
    */
    public void onAdditionClick(View v)
    {
        Button currentButton = findViewById(v.getId());
        String buttonText = currentButton.getText().toString();
        Intent i = new Intent(this, MathTemplateActivity.class);
        i.putExtra("Type", "Addition");
        i.putExtra("Difficulty", buttonText);
        startActivityForResult(i, 10);
    }

    public void onSubtractionClick(View v)
    {
        Button currentButton = findViewById(v.getId());
        String buttonText = currentButton.getText().toString();
        Intent i = new Intent(this, MathTemplateActivity.class);
        i.putExtra("Type", "Subtraction");
        i.putExtra("Difficulty", buttonText);
        startActivityForResult(i, 10);
    }

    public void onMultiplicationClick(View v)
    {
        Button currentButton = findViewById(v.getId());
        String buttonText = currentButton.getText().toString();
        Intent i = new Intent(this, MathTemplateActivity.class);
        i.putExtra("Type", "Multiplication");
        i.putExtra("Difficulty", buttonText);
        startActivityForResult(i, 10);
    }

    public void onDivisionClick(View v)
    {
        Button currentButton = findViewById(v.getId());
        String buttonText = currentButton.getText().toString();
        Intent i = new Intent(this, MathTemplateActivity.class);
        i.putExtra("Type", "Division");
        i.putExtra("Difficulty", buttonText);
        startActivityForResult(i, 10);
    }
}