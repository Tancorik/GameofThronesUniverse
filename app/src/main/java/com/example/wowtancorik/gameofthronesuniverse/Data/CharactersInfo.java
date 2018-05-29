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


public class CharactersInfo {

    private Map<String, String> mCharacter = new HashMap();

    public void setCharacterInfo(String key, String info) {
        mCharacter.put(key, info);
    }

    public Map<String, String> getCharacter() {
        return mCharacter;
    }

    public String fieldItem(String key) {
        return mCharacter.get(key);
    }


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
}
