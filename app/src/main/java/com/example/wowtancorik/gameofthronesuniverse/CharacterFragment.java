package com.example.wowtancorik.gameofthronesuniverse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * класс управляющий фрагментом fragment_character, отображающий информацию о конкретном герое
 *
 *  @author Alexandr Karpachev
 *         Created on 08.06.18
 */
public class CharacterFragment extends Fragment {

    public static final String TAG = "myCharacterFragment";
    public static final String SAVE_KEY = "save_key";

    private TextView mTextView;
    private ScrollView mScrollView;

    public static CharacterFragment newInstance() {
        return new CharacterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = view.findViewById(R.id.text_view);
        mScrollView = view.findViewById(R.id.scroll_view);
        String string;
        if (savedInstanceState != null) {
            string = savedInstanceState.getString(SAVE_KEY);
        }
        else {
            string = "Не выбран персонаж!";
        }
        mTextView.setText(string);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_KEY, mTextView.getText().toString());
    }

    /**
     * получить данные о герое
     *
     * @param text  информация о герое
     */
    public void setText(String text) {
        mTextView.setText(text);
        mScrollView.smoothScrollTo(0,0);
    }
}
