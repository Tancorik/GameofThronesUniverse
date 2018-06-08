package com.example.wowtancorik.gameofthronesuniverse.Data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Класс хранит имя персонажа и id номер
 *
 *  @author Alexandr Karpachev
 *         Created on 28.05.18
 */
public class NameAndNumber implements Parcelable{

    private String mName;
    private int mNumber;

    /**
     * конструктор
     *
     * @param name
     * @param number
     */
    public NameAndNumber(String name, int number) {
        mName = name;
        mNumber = number;
    }

    /**
     * вытаскивает значения из Парселя
     *
     * @param in
     */
    private NameAndNumber(Parcel in) {
        mName = in.readString();
        mNumber = in.readInt();
    }

    /**
     * вернуть имя персонажа
     *
     * @return
     */
    public String getName() {
        return mName;
    }

    /**
     * вернуть id номер персонажа
     *
     * @return
     */
    public int getNumber() {
        return mNumber;
    }

    /**
     * описывает контент
     *
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }


    /**
     * записать поля класса в Parcel
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mNumber);
    }

    /**
     *реализация CREATORа
     */
    public static final Parcelable.Creator<NameAndNumber> CREATOR  =
                                                        new Parcelable.Creator<NameAndNumber>() {
        @Override
        public NameAndNumber createFromParcel(Parcel source) {
            return new NameAndNumber(source);
        }

        @Override
        public NameAndNumber[] newArray(int size) {
            return new NameAndNumber[size];
        }
    };
}
