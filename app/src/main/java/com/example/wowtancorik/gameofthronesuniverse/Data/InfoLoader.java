package com.example.wowtancorik.gameofthronesuniverse.Data;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * Класс загружает данные с API
 *
 *  @author Alexandr Karpachev
 *         Created on 28.05.18
 */


public class InfoLoader {

    private final String LOG_TAG = "myLog";

    /**
     * Загруизть данные с строку и данного URL
     *
     * @param url                   адрес запроса данных
     * @param requestDataString     переменная для возврата скаченной информации
     */
    public void loadfromOutsite(String url, IRequestDataString requestDataString) {
        StringBuilder dataString = new StringBuilder();
        boolean endInfo = false;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestProperty("Accept", "application/vnd.anapioficeandfire+json; version=1");
            String responseMessage = connection.getResponseMessage();
            if (responseMessage.equals("OK")) {
                try (BufferedReader bufReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = bufReader.readLine()) != null) {
                        dataString.append(line);
                    }
                    if (dataString.length() == 2) endInfo = true;
                }
            }
            else {
                endInfo = true;
            }
        } catch (MalformedURLException e) {
            Log.i(LOG_TAG , "что-то не то с URL");
        } catch (IOException e) {
            Log.i(LOG_TAG, "что-то не то с соединением");
        }
       requestDataString.dataStringCallback(dataString.toString(), endInfo);
    }

    /**
     * Интерфейс возврата данных в виде строки
     */
    interface  IRequestDataString {
        void dataStringCallback(String string, boolean end);
    }
}
