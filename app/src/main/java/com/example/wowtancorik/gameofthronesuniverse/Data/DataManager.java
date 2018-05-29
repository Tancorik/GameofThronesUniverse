package com.example.wowtancorik.gameofthronesuniverse.Data;

import com.example.wowtancorik.gameofthronesuniverse.Interfaces.IDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс управляет порядком обработки и возврата данных
 *
 *  @author Alexandr Karpachev
 *         Created on 28.05.18
 */

public class DataManager implements IDataSource, InfoLoader.IRequestDataString,
        Parser.IRequestParsedNames, Parser.IRequestParsedCharacter {

    private final String DATA_BASE_URL = "https://www.anapioficeandfire.com/api/characters";

    private InfoLoader infoLoader = new InfoLoader();
    private boolean mEnd = false;
    private Parser mParser = new Parser();
    private List<NameAndUrl> mNameList = new ArrayList<>();
    private CharactersInfo mCharacterInfo;
    private String mDataString;

    /**
     * обработать запрос на список имен персонажей
     * заросить информацию с API, отправить на обработку в Парсер
     *
     * @return      возвращает список имен персонажей
     */
    @Override
    public List<NameAndUrl> loadDataBase() {

        int numPage=1;
        do {
            String pageURL = DATA_BASE_URL + "?page="+ numPage +"&pageSize=50";
            infoLoader.loadfromOutsite(pageURL, this);
            if (!mEnd)
                mParser.parseNames(mDataString, this);
            numPage+=1;
        } while(!mEnd);
        return mNameList;
    }


    /**
     * обработать запрос на информацию конкретного прерсонажа
     * запросить информацию, отправить на обработку в Парсер
     *
     * @param number    id номер персонажа
     * @return          возвращает информацию об одном персонаже
     */
    @Override
    public CharactersInfo loadCharacter(int number) {
        String characterURL = DATA_BASE_URL + "/" + number;
        infoLoader.loadfromOutsite(characterURL, this);
        mParser.parseCharacter(mDataString, this);
        return mCharacterInfo;
    }

    /**
     * возвращает данные после загрузки с API
     *
     * @param string    необработанная строка
     * @param end       флаг, что информации больше нет
     */
    @Override
    public void dataStringCallback(String string, boolean end) {
        mDataString = string;
        mEnd = end;
    }

    /**
     * возвращает список имен после Парсера
     *
     * @param nameAndUrlList    список имен и их id
     */
    @Override
    public void namesCallback(List<NameAndUrl> nameAndUrlList) {
        mNameList.addAll(nameAndUrlList);
    }

    /**
     * возращает информацию о конкретном персонаже после Парсера
     *
     * @param charactersInfo    информация о конкретном персонаже
     */
    @Override
    public void characterCallback(CharactersInfo charactersInfo) {
        mCharacterInfo = charactersInfo;
    }
}