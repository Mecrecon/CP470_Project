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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LevelSelect extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LevelSelect";
    protected int COLOR_LOCKED = 0;
    protected int COLOR_UNATTEMPTED = 1;
    protected int COLOR_INPROGRESS = 2;
    protected int COLOR_COMPLETED = 3;

    private int colorLocked;
    private int colorUnattempted;
    private int colorCompleted;
    private int colorInprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(ACTIVITY_NAME, "In onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
        // Get the request code (the math activity to select levels for)
        Intent intent = getIntent();
        String requestCode = intent.getStringExtra("requestCode");

        colorLocked = ResourcesCompat.getColor(getResources(), R.color.colorLightLocked, null);
        colorUnattempted = ResourcesCompat.getColor(getResources(), R.color.colorLightUnattempted, null);
        colorCompleted = ResourcesCompat.getColor(getResources(), R.color.colorLightCompleted, null);
        colorInprogress = ResourcesCompat.getColor(getResources(), R.color.colorLightInprogress, null);

        getSharedPreferences(requestCode, Context.MODE_PRIVATE).edit().clear().commit();

        // Load list of levels for the math activity from shared preferences (completed, uncompleted, unattempted, unavailable)
        ArrayList<String> levelsData = loadLevelsFromPrefrences(requestCode);
        if (levelsData.isEmpty()){
            Log.i(ACTIVITY_NAME, "Generating Prefrences for " + requestCode);
            levelsData = generateSharedPrefs(requestCode);
        }
        if (levelsData.isEmpty()){
            Log.i(ACTIVITY_NAME, "Could not load level data for file: " + requestCode);
            finish();
        }
        ArrayList<String> levels_test = new ArrayList<String>();
        for (int level = 1; level <= 22; level++){
            levels_test.add(Integer.toString(level) + "/" + Integer.toString(COLOR_UNATTEMPTED));
        }

        // Populate layout with n buttons and set their values and onclick functions
        populateLayout(levelsData);

        // Save level progression
    }

    private ArrayList<String> generateSharedPrefs(String requestCode){
        ArrayList<String> levelData = new ArrayList<String>();
        if (requestCode.equals("AdditionDifficulty")){
            for (int level = 1; level <= 5; level++){
                levelData.add(Integer.toString(level) + "/" + Integer.toString(COLOR_UNATTEMPTED));
            }
        } else if (requestCode.equals("SubtractionDifficulty")) {
            for (int level = 1; level <= 7; level++) {
                levelData.add(Integer.toString(level) + "/" + Integer.toString(COLOR_COMPLETED));
            }
        } else if (requestCode.equals("MultiplicationDifficulty")) {
            for (int level = 1; level <= 4; level++) {
                levelData.add(Integer.toString(level) + "/" + Integer.toString(COLOR_INPROGRESS));
            }
        } else if (requestCode.equals("DivisionDifficulty")) {
            levelData.add(Integer.toString(1) + "/" + Integer.toString(COLOR_INPROGRESS));
            levelData.add(Integer.toString(2) + "/" + Integer.toString(COLOR_COMPLETED));
            levelData.add(Integer.toString(3) + "/" + Integer.toString(COLOR_UNATTEMPTED));
            levelData.add(Integer.toString(4) + "/" + Integer.toString(COLOR_LOCKED));
        }
        saveLevelsToPrefrences(levelData, requestCode);
        return levelData;
    }

    private int getDeviceWidth(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    private void populateLayout(ArrayList<String> levelData){
        Log.i(ACTIVITY_NAME, "In populateLayout()");
        int numLevels = levelData.size();
        int columns = 4;
        int rows = (numLevels / columns) + 1;
        int deviceWidth = getDeviceWidth();
        int paddingValue = 20;
        int buttonWidth = (deviceWidth-8*paddingValue)/columns;
        String buttonText = getString(R.string.levelText);

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
        for (String data : levelData){
            String dataArray[] = data.split("/");
            int id = Integer.parseInt(dataArray[0]);
            int colorCode = Integer.parseInt(dataArray[1]);
            Boolean isLocked = false;
            Button buttonLevel = new Button(this);
            buttonLevel.setId(id);
            buttonLevel.setText(buttonText + " " + Integer.toString(id));
            buttonLevel.setWidth(buttonWidth);
            buttonLevel.setLayoutParams(layoutParams);
            // Set dynamic colour based on level completion
            if (colorCode == COLOR_COMPLETED) {
                buttonLevel.setBackgroundColor(colorCompleted);
            } else if (colorCode == COLOR_INPROGRESS) {
                buttonLevel.setBackgroundColor(colorInprogress);
            } else if (colorCode == COLOR_UNATTEMPTED) {
                buttonLevel.setBackgroundColor(colorUnattempted);
            } else if (colorCode == COLOR_LOCKED) {
                buttonLevel.setBackgroundColor(colorLocked);
                isLocked = true;
            }
            // Adds the button to the grid
            grid.addView(buttonLevel);
            if (isLocked == false) {
                // Sets the on click listener (Adding a if statement should be able to lock a button)
                int buttonLevelId = buttonLevel.getId();
                Button button = (Button) findViewById(buttonLevelId);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Start the level
                    }
                });
            }
        }
    }

    private void saveLevelsToPrefrences(ArrayList<String> levelData, String file){
        String data = "";
        if (levelData.size() > 0){
            data = levelData.get(0);
        }
        for (int i = 1; i < levelData.size(); i++){
            data += "@" + levelData.get(i);
        }
        SharedPreferences sharedPref = getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("LevelData", data);
        editor.apply();
        return;
    }

    private ArrayList<String> loadLevelsFromPrefrences(String file){
        SharedPreferences sharedPref = getSharedPreferences(file, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String data = sharedPref.getString("LevelData", null);
        if (data == null) {
            ArrayList<String> levelData = new ArrayList<String>();
            return levelData;
        }
        String dataArray[] = data.split("@");
        ArrayList<String> levelData = new ArrayList(Arrays.asList(dataArray));
        return levelData;
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
}