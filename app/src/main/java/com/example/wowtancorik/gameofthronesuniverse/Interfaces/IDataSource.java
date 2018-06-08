package com.example.wowtancorik.gameofthronesuniverse.Interfaces;

import com.example.wowtancorik.gameofthronesuniverse.Data.CharactersInfo;
import com.example.wowtancorik.gameofthronesuniverse.Data.NameAndNumber;

import java.util.List;

/**
 * Интерфейс для реализации в DataManager
 *
 *  @author Alexandr Karpachev
 *         Created on 28.05.18
 */

public interface IDataSource {

    /**
     * загрузить список имен, обработать и вернуть
     *
     * @return
     */
    List<NameAndNumber> loadDataBase();

    /**
     * загрузить информацию о персонаже, обработать и вернуть
     *
     * @param number    id персонажа
     * @return          возвращает информацию об одном персонаже
     */
    CharactersInfo loadCharacter(int number);
}
