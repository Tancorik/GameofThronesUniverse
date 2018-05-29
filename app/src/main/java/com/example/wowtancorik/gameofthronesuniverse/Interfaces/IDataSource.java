package com.example.wowtancorik.gameofthronesuniverse.Interfaces;

import com.example.wowtancorik.gameofthronesuniverse.Data.CharactersInfo;
import com.example.wowtancorik.gameofthronesuniverse.Data.NameAndUrl;

import java.util.List;

/**
 * Интерфейс для реализации в DataManager
 *
 *  @author Alexandr Karpachev
 *         Created on 28.05.18
 */

public interface IDataSource {

    /**
     * обработать списои имен и вернуть его
     *
     * @return
     */
    List<NameAndUrl> loadDataBase();

    /**
     * обработать информация об обном персонаже
     *
     * @param number    id персонажа
     * @return          возвращает информацию об одном персонаже
     */
    CharactersInfo loadCharacter(int number);
}
