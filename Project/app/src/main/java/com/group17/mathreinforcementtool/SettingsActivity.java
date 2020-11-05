package com.group17.mathreinforcementtool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {
    Switch darkSwitch;
    RadioButton smallButt;
    RadioButton medButt;
    RadioButton largeButt;
    TextView title;
    TextView font;
    TextView volume;
    Button userAgree;
    Button privatePolice;
    Button restart;
    Button bugReport;
    int smallSize = 15;
    int medSize = 20;
    int largeSize = 25;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final SharedPreferences darkPreference = getSharedPreferences("DarkStatus", Context.MODE_PRIVATE);
        final SharedPreferences fontPreference = getSharedPreferences("FontSize", Context.MODE_PRIVATE);
        final SharedPreferences.Editor darkEditor = darkPreference.edit();
        final SharedPreferences.Editor fontEditor = fontPreference.edit();
        darkSwitch = findViewById(R.id.DarkSwitch);
        smallButt = findViewById(R.id.SmallButton);
        medButt = findViewById(R.id.MedButton);
        largeButt = findViewById(R.id.LargeButton);
        title = findViewById(R.id.Title);
        font = findViewById(R.id.FontLabel);
        volume = findViewById(R.id.VolumeLabel);
        userAgree = findViewById(R.id.AgreementButton);
        privatePolice = findViewById(R.id.PolicyButton);
        restart = findViewById(R.id.RestartButton);
        bugReport = findViewById(R.id.BugButton);
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.Settings);


//        final ConstraintLayout MainLayout = (ConstraintLayout) findViewById(R.id.MainMenu);





        if(darkPreference.getBoolean("DarkStatus", true) == true){
            layout.setBackgroundColor(Color.BLACK);
            darkSwitch.setChecked(true);
            darkSwitch.setTextColor(Color.WHITE);
            smallButt.setTextColor(Color.WHITE);
            medButt.setTextColor(Color.WHITE);
            largeButt.setTextColor(Color.WHITE);
            title.setTextColor(Color.WHITE);
            font.setTextColor(Color.WHITE);
            volume.setTextColor(Color.WHITE);

//            MainLayout.setBackgroundColor(Color.BLACK);
        }

        if(fontPreference.getInt("Size", medSize) == smallSize){
            medButt.setChecked(false);
            largeButt.setChecked(false);
            smallButt.setTextSize(smallSize);
            medButt.setTextSize(smallSize);
            largeButt.setTextSize(smallSize);
            font.setTextSize(20);
            volume.setTextSize(smallSize);
            title.setTextSize(30);
            darkSwitch.setTextSize(smallSize);
            userAgree.setTextSize(smallSize);
            privatePolice.setTextSize(smallSize);
            restart.setTextSize(smallSize);
            bugReport.setTextSize(smallSize);
        }
        else if(fontPreference.getInt("Size", medSize) == medSize){
            smallButt.setChecked(false);
            largeButt.setChecked(false);
            smallButt.setTextSize(medSize);
            medButt.setTextSize(medSize);
            largeButt.setTextSize(medSize);
            font.setTextSize(25);
            volume.setTextSize(medSize);
            title.setTextSize(45);
            darkSwitch.setTextSize(medSize);
            userAgree.setTextSize(medSize);
            privatePolice.setTextSize(medSize);
            restart.setTextSize(medSize);
            bugReport.setTextSize(medSize);
        }
        else{
            smallButt.setChecked(false);
            medButt.setChecked(false);
            smallButt.setTextSize(largeSize);
            medButt.setTextSize(largeSize);
            largeButt.setTextSize(largeSize);
            font.setTextSize(30);
            volume.setTextSize(largeSize);
            title.setTextSize(60);
            darkSwitch.setTextSize(largeSize);
            userAgree.setTextSize(largeSize);
            privatePolice.setTextSize(largeSize);
            restart.setTextSize(largeSize);
            bugReport.setTextSize(largeSize);
        }

        darkSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (darkSwitch.isChecked()){
                    darkEditor.putBoolean("DarkStatus", true);
                    layout.setBackgroundColor(Color.BLACK);
                    darkSwitch.setTextColor(Color.WHITE);
                    smallButt.setTextColor(Color.WHITE);
                    medButt.setTextColor(Color.WHITE);
                    largeButt.setTextColor(Color.WHITE);
                    title.setTextColor(Color.WHITE);
                    font.setTextColor(Color.WHITE);
                    volume.setTextColor(Color.WHITE);

//                    MainLayout.setBackgroundColor(Color.BLACK);
                }
                else{
                    darkEditor.putBoolean("DarkStatus", false);
                    layout.setBackgroundColor(Color.WHITE);
                    darkSwitch.setTextColor(Color.BLACK);
                    smallButt.setTextColor(Color.BLACK);
                    medButt.setTextColor(Color.BLACK);
                    largeButt.setTextColor(Color.BLACK);
                    title.setTextColor(Color.BLACK);
                    font.setTextColor(Color.BLACK);
                    volume.setTextColor(Color.BLACK);

//                    MainLayout.setBackgroundColor(Color.WHITE);
                }
                darkEditor.commit();
            }
        });

        smallButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                medButt.setChecked(false);
                largeButt.setChecked(false);
                smallButt.setTextSize(smallSize);
                medButt.setTextSize(smallSize);
                largeButt.setTextSize(smallSize);
                font.setTextSize(20);
                volume.setTextSize(smallSize);
                title.setTextSize(30);
                darkSwitch.setTextSize(smallSize);
                userAgree.setTextSize(smallSize);
                privatePolice.setTextSize(smallSize);
                restart.setTextSize(smallSize);
                bugReport.setTextSize(smallSize);
                fontEditor.putInt("Size", smallSize);
                fontEditor.commit();
            }
        });

        medButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                smallButt.setChecked(false);
                largeButt.setChecked(false);
                smallButt.setTextSize(medSize);
                medButt.setTextSize(medSize);
                largeButt.setTextSize(medSize);
                font.setTextSize(25);
                volume.setTextSize(medSize);
                title.setTextSize(45);
                darkSwitch.setTextSize(medSize);
                userAgree.setTextSize(medSize);
                privatePolice.setTextSize(medSize);
                restart.setTextSize(medSize);
                bugReport.setTextSize(medSize);
                fontEditor.putInt("Size", medSize);
                fontEditor.commit();
            }
        });

        largeButt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                smallButt.setChecked(false);
                medButt.setChecked(false);
                smallButt.setTextSize(largeSize);
                medButt.setTextSize(largeSize);
                largeButt.setTextSize(largeSize);
                font.setTextSize(30);
                volume.setTextSize(largeSize);
                title.setTextSize(60);
                darkSwitch.setTextSize(largeSize);
                userAgree.setTextSize(largeSize);
                privatePolice.setTextSize(largeSize);
                restart.setTextSize(largeSize);
                bugReport.setTextSize(largeSize);
                fontEditor.putInt("Size", largeSize);
                fontEditor.commit();
            }
        });
    }
}