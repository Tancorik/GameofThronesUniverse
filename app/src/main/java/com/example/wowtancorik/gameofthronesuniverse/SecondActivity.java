package com.example.wowtancorik.gameofthronesuniverse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.wowtancorik.gameofthronesuniverse.MainActivity.INFO_OF_PERSON_KEY;

/**
 * Второе активити для отображения всей информации по определенному Персонажу
 *
 * @author Alexandr Karpachev
 *         Created on 28.05.18
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.text_view);

        Intent intent = getIntent();
        String string = intent.getStringExtra(INFO_OF_PERSON_KEY);

        textView.setText(string);
    }
}
