package com.group17.mathreinforcementtool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.Set;

public class LevelSelect extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LevelSelect";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        // Get the request code (the math activity to select levels for)
        Intent intent = getIntent();
        String requestCode = intent.getStringExtra("requestCode");

        // Load list of levels for the math activity from shared preferences (completed, uncompleted, unattempted, unavailable)
        SharedPreferences sharedPref = getSharedPreferences(requestCode, Context.MODE_PRIVATE);
        Set<String> levels = sharedPref.getStringSet("Levels", null);
        Set<String> levels_test = new HashSet<String>();
        levels_test.add("1");
        levels_test.add("2");
        levels_test.add("3");
        levels_test.add("4");
        levels_test.add("5");
        levels_test.add("6");
        levels_test.add("7");
        levels_test.add("8");
        levels_test.add("9");
        levels_test.add("10");
        levels_test.add("11");
        levels_test.add("12");
        levels_test.add("13");
        levels_test.add("14");
        levels_test.add("15");
        levels_test.add("16");
        levels_test.add("17");
        levels_test.add("18");
        levels_test.add("19");
        levels_test.add("20");
        levels_test.add("21");
        levels_test.add("22");

        // Populate layout with n buttons and set their values and onclick functions
        populateLayout(levels_test);
        // Save level progression
    }

    private int getDeviceWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    private void populateLayout(Set<String> levels){
        Log.i(ACTIVITY_NAME, "In populateLayout()");
        int numLevels = levels.size();
        int columns = 4;
        int rows = (numLevels / columns) + 1;
        int deviceWidth = getDeviceWidth();
        int paddingValue = 20;
        int buttonWidth = (deviceWidth-8*paddingValue)/columns;
        String buttonText = getString(R.string.levelText);

        int colorUnattempted = ResourcesCompat.getColor(getResources(), R.color.colorLightUnattempted, null);
        int colorCorrect = ResourcesCompat.getColor(getResources(), R.color.colorLightCorrect, null);

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayoutLevels);
        grid.removeAllViews();
        grid.setColumnCount(columns);
        grid.setRowCount(rows);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = paddingValue;
        layoutParams.rightMargin = paddingValue;
        layoutParams.topMargin = paddingValue;
        layoutParams.bottomMargin = paddingValue;
        for (int i = 1; i <= numLevels; i++){
            Button buttonLevel = new Button(this);
            buttonLevel.setId(i);
            buttonLevel.setText(buttonText + " " + Integer.toString(i));
            buttonLevel.setWidth(buttonWidth);
            buttonLevel.setLayoutParams(layoutParams);
            // Set dynamic colour based on level completion
            buttonLevel.setBackgroundColor(colorCorrect);
            
            // Adds the button to the grid
            grid.addView(buttonLevel);
            // Sets the on click listener (Adding a if statement should be able to lock a button)
            int buttonLevelId =  buttonLevel.getId();
            Button button = (Button) findViewById(buttonLevelId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the level
                }
            });
        }

    }

    @Override
    protected void onResume(){
        Log.i(ACTIVITY_NAME, "In onResume()");
        super.onResume();
    }

    @Override
    protected void onStart(){
        Log.i(ACTIVITY_NAME, "In onStart()");
        super.onStart();
    }

    @Override
    protected void onPause(){
        Log.i(ACTIVITY_NAME, "In onPause()");
        super.onPause();
    }

    @Override
    protected void onStop(){
        Log.i(ACTIVITY_NAME, "In onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        Log.i(ACTIVITY_NAME, "In onDestroy()");
        super.onDestroy();
    }

    public void saveData(View view){
        /*
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        SharedPreferences sharedPref = getSharedPreferences("Login_Activity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        EditText editText = findViewById(R.id.edit_email);
        String email = editText.getText().toString();
        editor.putString("DefaultEmail", email);
        editor.apply();

        startActivity(intent);
         */
    }
}