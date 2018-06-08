package com.example.wowtancorik.gameofthronesuniverse.Data;

import java.util.HashMap;
import java.util.Map;

/**
 *Класс хранения информации о персоне
 *
 * * @author Alexandr Karpachev
 *         Created on 28.05.18
 */
import static com.example.wowtancorik.gameofthronesuniverse.Data.Parser.FIELD;


/**
 * класс хранения информации о конкретном герое
 *
 * @author Alexandr Karpachev
 *         Created on 28.05.18
 */
public class CharactersInfo {

    private Map<String, String> mCharacter = new HashMap<>();

    /**
     * получить информацию о герое
     *
     * @param key       ключ для Map
     * @param info      информация по ключу
     */
    public void setCharacterInfo(String key, String info) {
        mCharacter.put(key, info);
    }

    /**
     * вернуть информацию о персонаже
     *
     * @return  информация типа Map
     */
    public Map<String, String> getCharacter() {
        return mCharacter;
    }


    /**
     * переопределение метода toString
     *
     * @return      инфорация о персонаже в виде строки
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : FIELD) {
            stringBuilder.append(string);
            stringBuilder.append("\n\t\t\t");
            stringBuilder.append(fieldItem(string));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * вернуть информацию по ключу
     *
     * @param key   ключ
     * @return      информация по ключу
     */
    private String fieldItem(String key) {
        return mCharacter.get(key);
    }
}
