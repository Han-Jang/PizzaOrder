package com.example.pizzaorder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ToppingActivity extends AppCompatActivity {

    //https://www.tutorialspoint.com/how-to-change-app-language-when-user-selects-language-in-android
    Spinner spinner;
    Locale locale;
    String currentLanguage = "en", currentLang;
//    ArrayAdapter<String> adapter;
    Button button;
    ArrayAdapter<CharSequence> adapter;

    //언어
    SharedPreferences LastSelect;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topping);

        //언어
        LastSelect = getSharedPreferences("LastSetting",MODE_PRIVATE);
        editor = LastSelect.edit();

        final String LastClickspinner = String.valueOf(LastSelect.getInt("LastClickspinner", 0));


//        currentLanguage = getIntent().getStringExtra(currentLang);
        spinner = findViewById(R.id.spinner);

//        List<String> list = new ArrayList<>();
//        list.add("Select Language");
//        list.add("English");
//        list.add("French");
//        list.add("Korean");

        adapter = ArrayAdapter.createFromResource(this,R.array.Languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //언어
        spinner.setSelected(Boolean.parseBoolean(LastClickspinner));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //언어
                editor.putInt("LastClick",position).commit();

                switch (position) {
                    case 0:
                        break;
                    case 1:
                        setLocale("en");
                        break;
                    case 2:
                        setLocale("fr");
                        break;
                    case 3:
                        setLocale("ko");
                        break;

//                    case 0:
//                        setLocale("en");
//                        break;
//                    case 1:
//                        setLocale("fr");
//                        break;
//                    case 2:
//                        setLocale("ko");
//                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        //Intent page change
//        button = (Button)findViewById(R.id.topping_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),AddressActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    private void setLocale(String localeName) {
        if (!localeName.equals(currentLanguage)) {
            locale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = locale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this,
                    ToppingActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);
        }
//        else {
//            Toast.makeText(ToppingActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
//        }
    }
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}