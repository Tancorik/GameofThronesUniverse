package com.example.wowtancorik.gameofthronesuniverse;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.wowtancorik.gameofthronesuniverse.Data.NameAndNumber;
import com.example.wowtancorik.gameofthronesuniverse.Service.MyService;

import java.util.List;

import static com.example.wowtancorik.gameofthronesuniverse.SecondActivity.CHARACTER_KEY;
import static com.example.wowtancorik.gameofthronesuniverse.Service.MyService.RESULT_KEY;

/**
 * стартовое активити, отображает фрагмент fragment_list
 *      и дополнительно fragment_character если активити в альбомном формате
 *
 * @author Alexandr Karpachev
 *         Created on 28.05.18
 */
public class MainActivity extends AppCompatActivity implements MyAdapter.IListener{

    public static final int LIST_CODE = 1;
    public static final int PERSON_CODE = 2;
    public static final int FROM_SECOND_ACTIVITY_CODE = 3;

    public static final String PARAM_PENDING = "pending result";
    public static final String REQUEST_KEY = "request_key";
    public static final String NUMBER_OF_PERSON = "number_of_person";
    public static final String INFO_OF_PERSON_KEY = "info of person key";

    private PendingIntent mPendingIntent;
    private MyListFragment mMyListFragment;
    private CharacterFragment mCharacterFragment;

    Intent mIntentforService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIntentforService = new Intent(this, MyService.class);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mMyListFragment = MyListFragment.newInstance();

            fragmentManager.beginTransaction()
                    .replace(R.id.list_fragment_holder, mMyListFragment, MyListFragment.TAG)
                    .commit();

            mPendingIntent = createPendingResult(LIST_CODE, mIntentforService, 0);

            mIntentforService.putExtra(REQUEST_KEY,LIST_CODE);
            mIntentforService.putExtra(PARAM_PENDING, mPendingIntent);
            startService(mIntentforService);
        }
        else {
            mMyListFragment = (MyListFragment) fragmentManager.findFragmentByTag(MyListFragment.TAG);
        }
        mMyListFragment.setListener(this);

        if (isLandscape()) {
            mCharacterFragment = (CharacterFragment) fragmentManager.findFragmentByTag(CharacterFragment.TAG);
            if (mCharacterFragment == null) {
                mCharacterFragment = CharacterFragment.newInstance();
                fragmentManager.beginTransaction()
                        .replace(R.id.character_fragment_holder, mCharacterFragment, CharacterFragment.TAG)
                        .commit();
            }
        }
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == LIST_CODE) {
                List<NameAndNumber> list = data.getParcelableArrayListExtra(RESULT_KEY);
                mMyListFragment.onDataLoaded(list);
            }
            else if (requestCode == PERSON_CODE) {
                String charactersInfo = data.getStringExtra(INFO_OF_PERSON_KEY);
                if (!isLandscape()) {
                    Intent intent = new Intent(this, SecondActivity.class);
                    intent.putExtra(INFO_OF_PERSON_KEY, charactersInfo);
                    startActivityForResult(intent, FROM_SECOND_ACTIVITY_CODE);
                }
                else {
                    mCharacterFragment.setText(charactersInfo);
                }
            }
            else if (requestCode == FROM_SECOND_ACTIVITY_CODE) {
                if (isLandscape()) {
                    mCharacterFragment.setText(data.getStringExtra(CHARACTER_KEY));
                }
            }
        }


    @Override
    public void clickListener(int number) {
        mPendingIntent = createPendingResult(PERSON_CODE, mIntentforService, 0);
        mIntentforService.putExtra(REQUEST_KEY,PERSON_CODE);
        mIntentforService.putExtra(PARAM_PENDING, mPendingIntent);
        mIntentforService.putExtra(NUMBER_OF_PERSON, number);
        startService(mIntentforService);
    }

    /**
     * определение альбомного режима
     *
     * @return
     */
    private boolean isLandscape() {
        View fragmentCharacter = findViewById(R.id.character_fragment_holder);
        return fragmentCharacter != null;
    }
}
