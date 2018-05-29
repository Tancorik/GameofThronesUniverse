package com.example.wowtancorik.gameofthronesuniverse;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.wowtancorik.gameofthronesuniverse.Data.NameAndUrl;
import com.example.wowtancorik.gameofthronesuniverse.Service.MyService;

import java.util.ArrayList;
import java.util.List;

import static com.example.wowtancorik.gameofthronesuniverse.Service.MyService.RESULT_KEY;

/**
 * Главное активити для показа списка всех персонажей в RecyclerView
 *
 * @author Alexandr Karpachev
 *         Created on 28.05.18
 */
public class MainActivity extends AppCompatActivity implements MyAdapter.IListener{

    public static final int LIST_CODE = 1;
    public static final int PERSON_CODE = 2;

    public static final String PARAM_PENDING = "pending result";
    public static final String REQUEST_KEY = "request_key";
    public static final String NUMBER_OF_PERSON = "number_of_person";
    public static final String INFO_OF_PERSON_KEY = "info of person key";

    private ProgressBar mProgressBar;
    private PendingIntent mPendingIntent;
    MyAdapter mAdapter;

    RecyclerView mRecyclerView;
    List<NameAndUrl> mList = new ArrayList<>();
    Intent mIntentforService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progress_bar);

        mIntentforService = new Intent(this, MyService.class);

        mAdapter = new MyAdapter(this);
        mRecyclerView = findViewById(R.id.recicler_view);
        mRecyclerView.setAdapter(mAdapter);

        mPendingIntent = createPendingResult(LIST_CODE, mIntentforService, 0);

        mIntentforService.putExtra(REQUEST_KEY,LIST_CODE);
        mIntentforService.putExtra(PARAM_PENDING, mPendingIntent);
        startService(mIntentforService);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == LIST_CODE) {
                mProgressBar.setVisibility(View.INVISIBLE);
                mList = (data.getParcelableArrayListExtra(RESULT_KEY));
                mAdapter.addDate(mList);
                mAdapter.notifyDataSetChanged();
            }
            else if (requestCode == PERSON_CODE) {
                Intent intent = new Intent(this, SecondActivity.class);
            String charactersInfo = data.getStringExtra(INFO_OF_PERSON_KEY);
                intent.putExtra(INFO_OF_PERSON_KEY, charactersInfo);
                startActivity(intent);
            }

        }

    @Override
    public void clickCallback(int number) {
        mPendingIntent = createPendingResult(PERSON_CODE, mIntentforService, 0);
        mIntentforService.putExtra(REQUEST_KEY,PERSON_CODE);
        mIntentforService.putExtra(PARAM_PENDING, mPendingIntent);
        mIntentforService.putExtra(NUMBER_OF_PERSON, number);
        startService(mIntentforService);
    }
}
