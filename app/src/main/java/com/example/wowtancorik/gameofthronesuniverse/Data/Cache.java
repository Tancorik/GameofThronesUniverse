package com.example.wowtancorik.gameofthronesuniverse.Data;


import java.util.ArrayList;
import java.util.List;

/**
 * Класс хранит список героев
 *
 * @author Alexandr Karpachev
 *         Created on 08.06.18
 */

public class Cache {

    private List<NameAndNumber> mList;
    volatile public static boolean mFillListFlag = false;

    /**
     * инициализация синглтона
     */
    private static class SingletonHolder {
        private static final Cache HOLDER_INSTANCE = new Cache();
    }

    public static Cache getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    /**
     * добавить к списку
     *
     * @param list  добавляемый список
     */
    public void addToList(List<NameAndNumber> list) {
        if (mList == null) {
            mList = new ArrayList<>();
            mList.addAll(list);
        }
        else {
            mList.addAll(list);
        }
    }

    /**
     * вернуть список имен
     *
     * @return      весь список имен
     */
    public List<NameAndNumber> getList() {
        return mList;
    }

}
