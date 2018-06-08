package com.example.wowtancorik.gameofthronesuniverse.Service;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.example.wowtancorik.gameofthronesuniverse.Data.CharactersInfo;
import com.example.wowtancorik.gameofthronesuniverse.Data.DataManager;
import com.example.wowtancorik.gameofthronesuniverse.Data.NameAndNumber;

import java.util.ArrayList;

import static com.example.wowtancorik.gameofthronesuniverse.MainActivity.INFO_OF_PERSON_KEY;
import static com.example.wowtancorik.gameofthronesuniverse.MainActivity.LIST_CODE;
import static com.example.wowtancorik.gameofthronesuniverse.MainActivity.NUMBER_OF_PERSON;
import static com.example.wowtancorik.gameofthronesuniverse.MainActivity.PARAM_PENDING;
import static com.example.wowtancorik.gameofthronesuniverse.MainActivity.PERSON_CODE;
import static com.example.wowtancorik.gameofthronesuniverse.MainActivity.REQUEST_KEY;


/**
 * Сервис для запроса данных и отправки данных в Activity
 *
 * @author Alexandr Karpachev
 *         Created on 28.05.18
 */
public class MyService extends IntentService {

    public static final String RESULT_KEY = "result for callback";
    PendingIntent mPendingIntent;

    /**
     * Конструктор
     */
    public MyService() {
        super("MyService");
    }


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        mPendingIntent = intent.getParcelableExtra(PARAM_PENDING);
        return super.onStartCommand(intent, flags, startId);
    }

    @WorkerThread
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        DataManager dataManager = new DataManager();
        if (intent.getIntExtra(REQUEST_KEY,0) == LIST_CODE) {

            ArrayList<NameAndNumber> list;
            list = (ArrayList<NameAndNumber>) dataManager.loadDataBase();
            intent.putParcelableArrayListExtra(RESULT_KEY, list);
            try {
                mPendingIntent.send(MyService.this, LIST_CODE, intent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
        else if (intent.getIntExtra(REQUEST_KEY,0) == PERSON_CODE) {
            int number = intent.getIntExtra(NUMBER_OF_PERSON,1);
            CharactersInfo charactersInfo = dataManager.loadCharacter(number);
            intent.putExtra(INFO_OF_PERSON_KEY, charactersInfo.toString());
            try {
                mPendingIntent.send(MyService.this, PERSON_CODE, intent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }
}
