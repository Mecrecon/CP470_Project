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
    Switch DarkSwitch;
    RadioButton SmallButt;
    RadioButton MedButt;
    RadioButton LargeButt;
    TextView Title;
    TextView Font;
    TextView Volume;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final SharedPreferences DarkPreference = getSharedPreferences("DarkStatus", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = DarkPreference.edit();
        DarkSwitch = findViewById(R.id.DarkSwitch);
        SmallButt = findViewById(R.id.SmallButton);
        MedButt = findViewById(R.id.MedButton);
        LargeButt = findViewById(R.id.LargeButton);
        Title = findViewById(R.id.Title);
        Font = findViewById(R.id.FontLabel);
        Volume = findViewById(R.id.VolumeLabel);
        final RelativeLayout layout = (RelativeLayout) findViewById(R.id.Settings);


//        final ConstraintLayout MainLayout = (ConstraintLayout) findViewById(R.id.MainMenu);





        if(DarkPreference.getBoolean("DarkStatus", true) == true){
            layout.setBackgroundColor(Color.BLACK);
            DarkSwitch.setChecked(true);
            DarkSwitch.setTextColor(Color.WHITE);
            SmallButt.setTextColor(Color.WHITE);
            MedButt.setTextColor(Color.WHITE);
            LargeButt.setTextColor(Color.WHITE);
            Title.setTextColor(Color.WHITE);
            Font.setTextColor(Color.WHITE);
            Volume.setTextColor(Color.WHITE);

//            MainLayout.setBackgroundColor(Color.BLACK);
        }


        DarkSwitch = findViewById(R.id.DarkSwitch);


        DarkSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DarkSwitch.isChecked()){
                    editor.putBoolean("DarkStatus", true);
                    layout.setBackgroundColor(Color.BLACK);
                    DarkSwitch.setTextColor(Color.WHITE);
                    SmallButt.setTextColor(Color.WHITE);
                    MedButt.setTextColor(Color.WHITE);
                    LargeButt.setTextColor(Color.WHITE);
                    Title.setTextColor(Color.WHITE);
                    Font.setTextColor(Color.WHITE);
                    Volume.setTextColor(Color.WHITE);

//                    MainLayout.setBackgroundColor(Color.BLACK);
                }
                else{
                    editor.putBoolean("DarkStatus", false);
                    layout.setBackgroundColor(Color.WHITE);
                    DarkSwitch.setTextColor(Color.BLACK);
                    SmallButt.setTextColor(Color.BLACK);
                    MedButt.setTextColor(Color.BLACK);
                    LargeButt.setTextColor(Color.BLACK);
                    Title.setTextColor(Color.BLACK);
                    Font.setTextColor(Color.BLACK);
                    Volume.setTextColor(Color.BLACK);

//                    MainLayout.setBackgroundColor(Color.WHITE);
                }
                editor.commit();
            }
        });
    }
}