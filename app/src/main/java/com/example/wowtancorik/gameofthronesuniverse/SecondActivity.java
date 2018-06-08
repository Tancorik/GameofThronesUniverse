package com.example.wowtancorik.gameofthronesuniverse;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
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

    public static final String CHARACTER_KEY = "character_key";

    public CharacterFragment mCharacterFragment;
    public String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mCharacterFragment = CharacterFragment.newInstance();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_holder, mCharacterFragment, CharacterFragment.TAG)
                    .commit();

        }
        else {
            mCharacterFragment = (CharacterFragment) fragmentManager.findFragmentByTag(CharacterFragment.TAG);
        }

        Intent intent = getIntent();
        mText = intent.getStringExtra(INFO_OF_PERSON_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCharacterFragment.setText(mText);
    }

    /**
     * при нажатии "назад" возвращаем в стартовую активите текст который был в этой активити
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(CHARACTER_KEY, mText);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }
}
