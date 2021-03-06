package com.example.wowtancorik.gameofthronesuniverse.Data;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс извлекает из строки определенные данные
 *
 *  @author Alexandr Karpachev
 */
public class Parser {

    public static final String[] FIELD = {
                                            "name",
                                            "culture",
                                            "born",
                                            "died",
                                            "titles",
                                            "aliases",
                                            "father",
                                            "mother",
                                            "tvSeries",
                                            "playedBy"};

    /**
     * извлеч имена персонажей и id персонажа
     *
     * @param string                необработанная строка
     * @param requestParsedNames    переменная возврата
     */
    public void parseNames(String string, IRequestParsedNames requestParsedNames) {
        List<NameAndNumber> nameAndNumberList = new ArrayList<>();
        try {
            JSONArray dataList = new JSONArray(string);
            for (int count = 0; count < dataList.length(); count++) {
                JSONObject jsonObject = (JSONObject) dataList.get(count);
                String name = jsonObject.getString("name");
                String url = jsonObject.getString("url");
                url = url.substring(url.lastIndexOf("/")+1);
                int number = Integer.parseInt(url);
                if (name.equals(""))
                    name = "someone";
                NameAndNumber onePerson = new NameAndNumber(name, number);
                nameAndNumberList.add(onePerson);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParsedNames.namesCallback(nameAndNumberList);
    }

    /**
     * извлекает из строки данные о конкретном персонаже
     *
     * @param string                    необработанная строка
     * @param requestParsedCharacter    переменная для возврата
     */
    public void parseCharacter(String string, IRequestParsedCharacter requestParsedCharacter) {
        CharactersInfo infolist = new CharactersInfo();
        try {
            JSONObject jsonObject = new JSONObject(string);
            for(String str : FIELD) {
                String info = jsonObject.getString(str);
                infolist.setCharacterInfo(str, info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        requestParsedCharacter.characterCallback(infolist);
    }

    /**
     * интерфейс для возврата списка имен
     */
    interface IRequestParsedNames {
        void namesCallback(List<NameAndNumber> nameAndNumberList);
    }

    /**
     * интекфейс для возврата информации о конкретном герое
     */
    interface IRequestParsedCharacter {
        void characterCallback(CharactersInfo charactersInfo);
    }
}
